package ru.nexign.brtservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.brtservice.dto.AbonentPayRequestRO;
import ru.nexign.brtservice.dto.AbonentPayResponseRO;
import ru.nexign.brtservice.dto.ChangeBalance;
import ru.nexign.brtservice.dto.TariffRequestRO;
import ru.nexign.brtservice.dto.TariffResponseRO;
import ru.nexign.brtservice.dto.UserRequestRO;
import ru.nexign.brtservice.dto.UserResponseRO;
import ru.nexign.brtservice.entity.Abonent;


public interface AbonentService {
    Abonent getAbonentByPhone(String phone);
    void getAllAbonentsPhone();
    void changeBalance(ChangeBalance response);
    ResponseEntity<AbonentPayResponseRO> replenishAccount (@RequestBody AbonentPayRequestRO request);

    ResponseEntity<UserResponseRO> addNewAbonent(@RequestBody UserRequestRO requestRO);
    ResponseEntity<TariffResponseRO> changeTariff(@RequestBody TariffRequestRO request);
}
