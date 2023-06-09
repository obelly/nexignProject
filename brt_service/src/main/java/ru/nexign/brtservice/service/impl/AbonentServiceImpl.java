package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public List<BillingResponse> getChangedAbonents() {
        var df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        List<BillingResponse> billingResponseList = new ArrayList<>();
        List<Abonent> abonentsByIsUpdatedTrue = abonentRepository.getAbonentsByIsUpdatedTrue();
        abonentsByIsUpdatedTrue
                .forEach(abonent -> {
                    if (abonent.getBalance() > 0) {
                        var billingResponse = new BillingResponse(
                                abonent.getNumberPhone(),
                                df.format(abonent.getBalance()));
                        billingResponseList.add(billingResponse);
                    }
                });
        return billingResponseList;
    }

    @Transactional
    @Override
    public AbonentPayResponse replenishAccount(AbonentPayRequest request) {
        var abonent = abonentRepository.getAbonentByNumberPhone(request.getNumberPhone()).orElse(null);
        if (abonent != null) {
            var sum = abonent.getBalance() + request.getMoney();
            abonent.setBalance(sum);
            abonentRepository.save(abonent);

            return new AbonentPayResponse(abonent.getId(),
                    abonent.getNumberPhone(),
                    sum);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public UserResponse addNewAbonent(UserRequest request) {
        var abonentsPhoneNumber = abonentRepository.findAll().stream()
                .map(Abonent::getNumberPhone)
                .toList();
        Tariff tariff = tariffRepository.getTariffByTariffNumber(request.getTariffId()).orElse(null);

        if (!abonentsPhoneNumber.contains(request.getNumberPhone()) && tariff != null) {
            var abonent = new Abonent();
            abonent.setNumberPhone(request.getNumberPhone());
            abonent.setTariff(tariff);
            abonent.setBalance(request.getBalance());
            abonentRepository.save(abonent);

            return new UserResponse(
                    abonent.getNumberPhone(),
                    abonent.getTariff().getTariffNumber(),
                    abonent.getBalance());
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public TariffResponse changeTariff(TariffRequest request) {
        var abonent = abonentRepository.getAbonentByNumberPhone(request.getNumberPhone()).orElse(null);
        Tariff tariff = tariffRepository.getTariffByTariffNumber(request.getTariffId()).orElse(null);
        if (abonent != null && tariff != null) {
            abonent.setTariff(tariff);
            abonentRepository.save(abonent);
            return new TariffResponse(
                    abonent.getId(),
                    abonent.getNumberPhone(),
                    tariff.getTariffNumber());
        } else {
            return null;
        }

    }

    @Transactional
    public void changeBalance(ChangeBalance response) {
        abonentRepository.getAbonentByNumberPhone(response.getNumberPhone())
                .ifPresent(abonent -> {
                    abonent.setBalance(abonent.getBalance() - response.getCost());
                    abonent.setIsUpdated(true);
                    abonentRepository.save(abonent);
                });
    }

    @Transactional
    public void setUpdated() {
        abonentRepository.findAll().forEach(abonent -> {
            abonent.setIsUpdated(false);
            abonentRepository.save(abonent);
        });
    }
}
