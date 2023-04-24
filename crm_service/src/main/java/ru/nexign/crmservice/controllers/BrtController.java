package ru.nexign.crmservice.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.crmservice.dto.AbonentPayRequestRO;
import ru.nexign.crmservice.dto.AbonentPayResponseRO;
import ru.nexign.crmservice.dto.TariffRequestRO;
import ru.nexign.crmservice.dto.TariffResponseRO;
import ru.nexign.crmservice.dto.UserRequestRO;
import ru.nexign.crmservice.dto.UserResponseRO;
import ru.nexign.crmservice.service.BrtService;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("")
public class BrtController {
    BrtService brtService;

    @PatchMapping(value = "/abonent/pay/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbonentPayResponseRO> replenishAccount(@RequestBody AbonentPayRequestRO requestRO) {
        return brtService.replenishAccount(requestRO);
    }
    @PatchMapping(value = "/manager/abonent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseRO> addNewAbonent(@RequestBody UserRequestRO requestRO) {
        return brtService.addNewAbonent(requestRO);
    }

    @PatchMapping(value = "/manager/changeTariff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TariffResponseRO> changeTariff(@RequestBody TariffRequestRO request) {
        return brtService.changeTariff(request);
    }
}
