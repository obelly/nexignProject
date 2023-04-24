package ru.nexign.crmservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nexign.crmservice.dto.BillingRun;
import ru.nexign.crmservice.service.ManagerService;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagerController {
    ManagerService managerService;

    @PatchMapping("manager/billing")
    public ResponseEntity<Void> billing(@RequestBody BillingRun billingRun){
        if (managerService.runTariffication(billingRun)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
