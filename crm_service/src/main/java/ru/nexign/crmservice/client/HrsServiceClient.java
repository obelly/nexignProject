package ru.nexign.crmservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nexign.crmservice.dto.AbonentCallsResponseRO;


@FeignClient(name = "hrsClient", url = "http://localhost:8989")
public interface HrsServiceClient {
    @GetMapping("/abonent/report/{numberPhone}")
    ResponseEntity<AbonentCallsResponseRO> detailingCalls(@PathVariable("numberPhone") String numberPhone);
}
