package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.dto.AbonentCallsResponseRO;
import ru.nexign.hrsservice.dto.CallResponseRO;
import ru.nexign.hrsservice.entity.PayLoad;
import ru.nexign.hrsservice.repository.CallRepository;
import ru.nexign.hrsservice.repository.PayLoadRepository;
import ru.nexign.hrsservice.service.PayLoadService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayLoadServiceImpl implements PayLoadService {
    PayLoadRepository payLoadRepository;
    CallRepository callRepository;

    public void createPayLoad(PayLoad payLoad) {
        payLoadRepository.save(payLoad);
    }

    @Override
    public AbonentCallsResponseRO detailingCalls(String numbersPhone) {
        var call = callRepository.getCallByNumberPhone(numbersPhone).orElse(null);
        var allPayLoadsByCall = payLoadRepository.getAllByCall(call);
        List<CallResponseRO> abonentCalls = new ArrayList<>();
        allPayLoadsByCall.forEach(payLoad -> {
            CallResponseRO callResponseRO = CallResponseRO.builder()
                    .callType(payLoad.getCallType())
                    .startTime(payLoad.getStartTime())
                    .endTime(payLoad.getEndTime())
                    .duration(payLoad.getDuration())
                    .cost(payLoad.getCost())
                    .build();
            abonentCalls.add(callResponseRO);
        });
        assert call != null;
        return AbonentCallsResponseRO.builder()
                .id(call.getId())
                .numberPhone(call.getNumberPhone())
                .tariffIndex(call.getTariff())
                .payload(abonentCalls)
                .totalCost(call.getTotalCost())
                .monetaryUnit(call.getMonetaryUnit())
                .build();
    }
}
