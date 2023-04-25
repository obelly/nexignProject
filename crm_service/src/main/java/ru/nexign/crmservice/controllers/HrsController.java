package ru.nexign.crmservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.crmservice.dto.AbonentCallsResponse;
import ru.nexign.crmservice.service.HrsService;


@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/abonent/")
public class HrsController {
    HrsService hrsService;

    @GetMapping("/report/{numberPhone}")
    public ResponseEntity<AbonentCallsResponse> detailingCalls(@PathVariable("numberPhone") String numberPhone) {
        return hrsService.detailingCalls(numberPhone);
    }
}
