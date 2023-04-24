package ru.nexign.hrsservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.hrsservice.dto.AbonentCallsResponseRO;
import ru.nexign.hrsservice.service.PayLoadService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HrsController {
    PayLoadService payLoadService;

    @GetMapping("/report/{numberPhone}")
    public ResponseEntity<AbonentCallsResponseRO> detailingCalls(@PathVariable("numberPhone") String numberPhone) {
       return ResponseEntity.ok(payLoadService.detailingCalls(numberPhone));
    }
}
