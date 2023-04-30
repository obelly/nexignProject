package ru.nexign.crmservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.crmservice.dto.AbonentPayRequest;
import ru.nexign.crmservice.dto.AbonentPayResponse;
import ru.nexign.crmservice.dto.BillingResponse;
import ru.nexign.crmservice.dto.TariffRequest;
import ru.nexign.crmservice.dto.TariffResponse;
import ru.nexign.crmservice.dto.UserRequest;
import ru.nexign.crmservice.dto.UserResponse;

import java.util.List;


@FeignClient(name = "brtClient", url = "http://localhost:8787/")
public interface BrtServiceClient {
    @PatchMapping(value = "/abonent/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbonentPayResponse> replenishAccount(@RequestBody AbonentPayRequest request);

    @PostMapping(value = "/manager/abonent", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> addNewAbonent(@RequestBody UserRequest request);

    @PatchMapping(value = "/manager/changeTariff", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TariffResponse> changeTariff(@RequestBody TariffRequest request);

    @PatchMapping("/manager/billing")
    ResponseEntity<List<BillingResponse>> runTariffication();

}
