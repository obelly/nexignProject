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
    ResponseEntity<List<BillingResponse>> getChangedAbonents();
    void getAllAbonentsPhoneAndSend();
    void changeBalance(ChangeBalance response);
    ResponseEntity<AbonentPayResponse> replenishAccount (@RequestBody AbonentPayRequest request);

    ResponseEntity<UserResponse> addNewAbonent(@RequestBody UserRequest requestRO);
    ResponseEntity<TariffResponse> changeTariff(@RequestBody TariffRequest request);
    void setUpdated();
}
