package ru.nexign.cdrservice.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CdrController {

//    @PatchMapping("/manager/billing")
//    public Numbers billing(){
//
//        List<Client> clients = userService.getAll();
//        Numbers numberList = new Numbers();
//        clients.forEach(client -> {
//            BillingResponseRO responseRO = BillingResponseRO.builder()
//                    .phoneNumber(client.getPhoneNumber())
//                    .balance(client.getBalance())
//                    .build();
//            numberList.getNumbers().add(responseRO);
//        });
//        return numberList;
//    }
}
