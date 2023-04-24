package ru.nexign.hrsservice.service;

import ru.nexign.hrsservice.dto.AbonentCallsResponseRO;
import ru.nexign.hrsservice.entity.PayLoad;

public interface PayLoadService {

    void createPayLoad(PayLoad payLoad);

    AbonentCallsResponseRO detailingCalls(String numbersPhone);

}
