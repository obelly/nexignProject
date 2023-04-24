package ru.nexign.crmservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nexign.crmservice.client.CdrServiceClient;
import ru.nexign.crmservice.dto.BillingRun;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagerService {
    CdrServiceClient cdrServiceClient;

    public boolean runTariffication(BillingRun billingRun){
        ResponseEntity<Void> voidResponseEntity = cdrServiceClient.runTariffication(billingRun);
        return voidResponseEntity.getStatusCode().is2xxSuccessful();
    }
}
