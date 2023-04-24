package ru.nexign.brtservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.brtservice.dto.AbonentPayRequestRO;
import ru.nexign.brtservice.dto.AbonentPayResponseRO;
import ru.nexign.brtservice.dto.TariffRequestRO;
import ru.nexign.brtservice.dto.TariffResponseRO;
import ru.nexign.brtservice.dto.UserRequestRO;
import ru.nexign.brtservice.dto.UserResponseRO;
import ru.nexign.brtservice.service.AbonentService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrtController {

    AbonentService abonentService;

    @PatchMapping(value = "abonent/pay/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbonentPayResponseRO> replenishAccount (@RequestBody AbonentPayRequestRO requestRO){
        return abonentService.replenishAccount(requestRO);
    }

    @PatchMapping(value = "manager/abonent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseRO> addNewAbonent(@RequestBody UserRequestRO requestRO) {
        return abonentService.addNewAbonent(requestRO);
    }

    @PatchMapping(value = "manager/changeTariff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TariffResponseRO> changeTariff(@RequestBody TariffRequestRO request) {
        return abonentService.changeTariff(request);
    }

}
