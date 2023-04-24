package ru.nexign.cdrservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.cdrservice.dto.BillingRun;
import ru.nexign.cdrservice.service.ProducerService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CdrController {
    ProducerService producerService;

    @PostMapping("manager/billing")
    public ResponseEntity<Void> billing(@RequestBody BillingRun billingRun) {
        producerService.produceGetNumbers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
