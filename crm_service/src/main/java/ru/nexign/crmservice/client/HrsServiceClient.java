package ru.nexign.crmservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nexign.crmservice.dto.AbonentCallsResponse;


@FeignClient(name = "hrsClient", url = "http://localhost:9090/")
public interface HrsServiceClient {
    @GetMapping("/abonent/report/{numberPhone}")
    ResponseEntity<AbonentCallsResponse> detailingCalls(@PathVariable("numberPhone") String numberPhone);
}
