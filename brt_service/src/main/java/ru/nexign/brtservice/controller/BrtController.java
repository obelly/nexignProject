package ru.nexign.brtservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.brtservice.dto.AbonentPayRequest;
import ru.nexign.brtservice.dto.AbonentPayResponse;
import ru.nexign.brtservice.dto.BillingResponse;
import ru.nexign.brtservice.dto.TariffRequest;
import ru.nexign.brtservice.dto.TariffResponse;
import ru.nexign.brtservice.dto.UserRequest;
import ru.nexign.brtservice.dto.UserResponse;
import ru.nexign.brtservice.service.AbonentService;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrtController {

    AbonentService abonentService;

    @PatchMapping(value = "/abonent/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbonentPayResponse> replenishAccount(@RequestBody AbonentPayRequest request) {
        return abonentService.replenishAccount(request);
    }

    @PostMapping(value = "/manager/abonent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> addNewAbonent(@RequestBody UserRequest request) {
        return abonentService.addNewAbonent(request);
    }

    @PatchMapping(value = "/manager/changeTariff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TariffResponse> changeTariff(@RequestBody TariffRequest request) {
        return abonentService.changeTariff(request);
    }

    @PatchMapping("/manager/billing")
    public ResponseEntity<List<BillingResponse>> billing() {
        abonentService.getAllAbonentsPhoneAndSend();
        return abonentService.getChangedAbonents();
    }

}
