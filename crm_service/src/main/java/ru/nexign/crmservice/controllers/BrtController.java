package ru.nexign.crmservice.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.crmservice.dto.AbonentPayRequest;
import ru.nexign.crmservice.dto.AbonentPayResponse;
import ru.nexign.crmservice.dto.BillingResponse;
import ru.nexign.crmservice.dto.TariffRequest;
import ru.nexign.crmservice.dto.TariffResponse;
import ru.nexign.crmservice.dto.UserRequest;
import ru.nexign.crmservice.dto.UserResponse;
import ru.nexign.crmservice.service.BrtService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("")
public class BrtController {
    BrtService brtService;

    @PatchMapping(value = "/abonent/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbonentPayResponse> replenishAccount(@RequestBody AbonentPayRequest request) {
        return brtService.replenishAccount(request);
    }

    @PostMapping(value = "/manager/abonent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> addNewAbonent(@RequestBody UserRequest request) {
        return brtService.addNewAbonent(request);
    }

    @PatchMapping(value = "/manager/changeTariff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TariffResponse> changeTariff(@RequestBody TariffRequest request) {
        return brtService.changeTariff(request);
    }

    @PatchMapping("manager/billing")
    public ResponseEntity<List<BillingResponse>> billing() {
        return brtService.runTariffication();
    }
}
