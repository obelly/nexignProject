package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nexign.brtservice.dto.AbonentPayRequestRO;
import ru.nexign.brtservice.dto.AbonentPayResponseRO;
import ru.nexign.brtservice.dto.ChangeBalance;
import ru.nexign.brtservice.dto.TariffRequestRO;
import ru.nexign.brtservice.dto.TariffResponseRO;
import ru.nexign.brtservice.dto.UserRequestRO;
import ru.nexign.brtservice.dto.UserResponseRO;
import ru.nexign.brtservice.entity.Abonent;
import ru.nexign.brtservice.entity.Tariff;
import ru.nexign.brtservice.repository.AbonentRepository;
import ru.nexign.brtservice.repository.TariffRepository;
import ru.nexign.brtservice.service.AbonentService;
import ru.nexign.brtservice.service.ProducerService;

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

    public void getAllAbonentsPhone() {
        var abonentPhones = abonentRepository.findAll().stream()
                .map(Abonent::getNumberPhone)
                .toList();

        producerService.producePhoneNumbers(abonentPhones);

        log.info("Список номеров отправлен");
    }

    @Transactional
    @Override
    public ResponseEntity<AbonentPayResponseRO> replenishAccount(AbonentPayRequestRO request) {
        var abonent = abonentRepository.getAbonentByNumberPhone(request.getNumberPhone()).orElse(null);
        if (abonent != null) {
            var sum = abonent.getBalance() + request.getMoney();
            abonent.setBalance(sum);
            abonentRepository.save(abonent);

            return new ResponseEntity<>(AbonentPayResponseRO.builder()
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
    public ResponseEntity<UserResponseRO> addNewAbonent(UserRequestRO request) {
        var abonentsPhoneNumber = abonentRepository.findAll().stream()
                .map(Abonent::getNumberPhone)
                .toList();
        Tariff tariff = tariffRepository.getTariffByName(request.getTariff().name()).orElse(null);

        if (!abonentsPhoneNumber.contains(request.getNumberPhone()) && tariff != null) {
            var abonent = new Abonent();
            abonent.setNumberPhone(request.getNumberPhone());
            abonent.setTariff(tariff);
            abonent.setBalance(request.getBalance());
            abonentRepository.save(abonent);

            return new ResponseEntity<>(UserResponseRO.builder()
                    .numberPhone(abonent.getNumberPhone())
                    .balance(abonent.getBalance())
                    .tariff(abonent.getTariff().getName())
                    .build(), HttpStatus.CREATED);
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<TariffResponseRO> changeTariff(TariffRequestRO request) {
        var abonent = abonentRepository.getAbonentByNumberPhone(request.getNumberPhone()).orElse(null);
        Tariff tariff = tariffRepository.getTariffByName(request.getTariff()).orElse(null);
        if (abonent != null && tariff != null) {
            abonent.setTariff(tariff);
            abonentRepository.save(abonent);
            return new ResponseEntity<>(TariffResponseRO.builder()
                    .id(abonent.getId())
                    .numberPhone(abonent.getNumberPhone())
                    .tariff(request.getTariff())
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
