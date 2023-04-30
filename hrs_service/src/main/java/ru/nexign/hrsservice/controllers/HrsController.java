package ru.nexign.hrsservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.hrsservice.dto.AbonentCallsResponse;
import ru.nexign.hrsservice.service.PayLoadService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HrsController {
    PayLoadService payLoadService;

    @GetMapping("/abonent/report/{numberPhone}")
    public ResponseEntity<AbonentCallsResponse> detailingCalls(@PathVariable(value = "numberPhone") String numberPhone) {
        return ResponseEntity.ok(payLoadService.detailingCalls(numberPhone));
    }
}
