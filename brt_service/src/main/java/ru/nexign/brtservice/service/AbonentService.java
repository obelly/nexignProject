package ru.nexign.brtservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.brtservice.dto.AbonentPayRequest;
import ru.nexign.brtservice.dto.AbonentPayResponse;
import ru.nexign.brtservice.dto.BillingResponse;
import ru.nexign.brtservice.dto.ChangeBalance;
import ru.nexign.brtservice.dto.TariffRequest;
import ru.nexign.brtservice.dto.TariffResponse;
import ru.nexign.brtservice.dto.UserRequest;
import ru.nexign.brtservice.dto.UserResponse;
import ru.nexign.brtservice.entity.Abonent;

import java.util.List;


public interface AbonentService {
    Abonent getAbonentByPhone(String phone);
    List<BillingResponse> getChangedAbonents();
    void getAllAbonentsPhoneAndSend();
    void changeBalance(ChangeBalance response);
    AbonentPayResponse replenishAccount (@RequestBody AbonentPayRequest request);
    UserResponse addNewAbonent(@RequestBody UserRequest requestRO);
    TariffResponse changeTariff(@RequestBody TariffRequest request);
    void setUpdated();
}
