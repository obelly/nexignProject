package ru.nexign.hrsservice.service;

import ru.nexign.hrsservice.dto.AbonentCallsResponse;
import ru.nexign.hrsservice.entity.PayLoad;

public interface PayLoadService {

    void createPayLoad(PayLoad payLoad);

    AbonentCallsResponse detailingCalls(String numbersPhone);

}
