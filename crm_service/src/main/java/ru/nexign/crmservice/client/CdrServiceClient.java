package ru.nexign.crmservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.crmservice.dto.BillingRun;


@FeignClient(name = "cdrClient", url = "http://localhost:8888")
public interface CdrServiceClient {
    @PostMapping("/manager/billing")
    ResponseEntity<Void> runTariffication(@RequestBody BillingRun billingRun);
}
