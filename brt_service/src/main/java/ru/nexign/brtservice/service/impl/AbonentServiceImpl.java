package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nexign.brtservice.dto.AbonentPayRequest;
import ru.nexign.brtservice.dto.AbonentPayResponse;
import ru.nexign.brtservice.dto.BillingResponse;
import ru.nexign.brtservice.dto.ChangeBalance;
import ru.nexign.brtservice.dto.TariffRequest;
import ru.nexign.brtservice.dto.TariffResponse;
import ru.nexign.brtservice.dto.UserRequest;
import ru.nexign.brtservice.dto.UserResponse;
import ru.nexign.brtservice.entity.Abonent;
import ru.nexign.brtservice.entity.Tariff;
import ru.nexign.brtservice.repository.AbonentRepository;
import ru.nexign.brtservice.repository.TariffRepository;
import ru.nexign.brtservice.service.AbonentService;
import ru.nexign.brtservice.service.ProducerService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AbonentServiceImpl implements AbonentService {
    AbonentRepository abonentRepository;
    TariffRepository tariffRepository;
    ProducerService producerService;

    public Abonent getAbonentByPhone(String phone) {
        return abonentRepository.getAbonentByNumberPhone(phone).orElse(null);
    }

    public void getAllAbonentsPhoneAndSend() {
        var abonentPhones = abonentRepository.findAll().stream()
                .map(Abonent::getNumberPhone)
                .toList();
        producerService.producePhoneNumbers(abonentPhones);
        log.info("Список номеров отправлен");
    }

    public ResponseEntity<List<BillingResponse>> getAllAbonents() {
        List<BillingResponse> billingResponseList = new ArrayList<>();
        var abonents = abonentRepository.findAll();
        abonents.forEach(abonent -> {
            var billingResponse = BillingResponse.builder()
                    .phoneNumber(abonent.getNumberPhone())
                    .balance(abonent.getBalance()).build();
            billingResponseList.add(billingResponse);
        });

        return new ResponseEntity<>(billingResponseList, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<AbonentPayResponse> replenishAccount(AbonentPayRequest request) {
        var abonent = abonentRepository.getAbonentByNumberPhone(request.getNumberPhone()).orElse(null);
        if (abonent != null) {
            var sum = abonent.getBalance() + request.getMoney();
            abonent.setBalance(sum);
            abonentRepository.save(abonent);

            return new ResponseEntity<>(AbonentPayResponse.builder()
                    .id(abonent.getId())
                    .numberPhone(abonent.getNumberPhone())
                    .money(request.getMoney())
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @Override
    public ResponseEntity<UserResponse> addNewAbonent(UserRequest request) {
        var abonentsPhoneNumber = abonentRepository.findAll().stream()
                .map(Abonent::getNumberPhone)
                .toList();
        Tariff tariff = tariffRepository.getTariffByTariffNumber(request.getTariff()).orElse(null);

        if (!abonentsPhoneNumber.contains(request.getNumberPhone()) && tariff != null) {
            var abonent = new Abonent();
            abonent.setNumberPhone(request.getNumberPhone());
            abonent.setTariff(tariff);
            abonent.setBalance(request.getBalance());
            abonentRepository.save(abonent);

            return new ResponseEntity<>(UserResponse.builder()
                    .numberPhone(abonent.getNumberPhone())
                    .balance(abonent.getBalance())
                    .tariff(abonent.getTariff().getTariffNumber())
                    .build(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<TariffResponse> changeTariff(TariffRequest request) {
        var abonent = abonentRepository.getAbonentByNumberPhone(request.getNumberPhone()).orElse(null);
        Tariff tariff = tariffRepository.getTariffByTariffNumber(request.getTariff()).orElse(null);
        if (abonent != null && tariff != null) {
            abonent.setTariff(tariff);
            abonentRepository.save(abonent);
            return new ResponseEntity<>(TariffResponse.builder()
                    .id(abonent.getId())
                    .numberPhone(abonent.getNumberPhone())
                    .tariff(tariff.getTariffNumber())
                    .build(), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public void changeBalance(ChangeBalance response) {
        abonentRepository.getAbonentByNumberPhone(response.getNumberPhone())
                .ifPresent(abonent -> {
                    abonent.setBalance(abonent.getBalance() - response.getCost());
                    abonentRepository.save(abonent);
                });
    }
}
