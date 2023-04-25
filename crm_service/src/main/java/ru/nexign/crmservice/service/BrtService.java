package ru.nexign.crmservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.crmservice.client.BrtServiceClient;
import ru.nexign.crmservice.dto.AbonentPayRequest;
import ru.nexign.crmservice.dto.AbonentPayResponse;
import ru.nexign.crmservice.dto.BillingResponse;
import ru.nexign.crmservice.dto.TariffRequest;
import ru.nexign.crmservice.dto.TariffResponse;
import ru.nexign.crmservice.dto.UserRequest;
import ru.nexign.crmservice.dto.UserResponse;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrtService {
    BrtServiceClient brtServiceClient;


    public ResponseEntity<AbonentPayResponse> replenishAccount(AbonentPayRequest request) {
        return brtServiceClient.replenishAccount(request);
    }

    public ResponseEntity<UserResponse> addNewAbonent(@RequestBody UserRequest requestRO) {
        return brtServiceClient.addNewAbonent(requestRO);
    }

    public ResponseEntity<TariffResponse> changeTariff(TariffRequest request) {
        return brtServiceClient.changeTariff(request);
    }

    public ResponseEntity<List<BillingResponse>> runTariffication() {
        return brtServiceClient.runTariffication();
    }
}
