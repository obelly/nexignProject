package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.dto.AbonentCallsResponse;
import ru.nexign.hrsservice.dto.CallResponse;
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
    public AbonentCallsResponse detailingCalls(String numbersPhone) {
        var call = callRepository.getCallByNumberPhone(numbersPhone).orElse(null);
        var allPayLoadsByCall = payLoadRepository.getAllByCall(call);
        List<CallResponse> abonentCalls = new ArrayList<>();
        allPayLoadsByCall.forEach(payLoad -> {
            CallResponse callResponse = CallResponse.builder()
                    .callType(payLoad.getCallType())
                    .startTime(payLoad.getStartTime())
                    .endTime(payLoad.getEndTime())
                    .duration(payLoad.getDuration())
                    .cost(payLoad.getCost())
                    .build();
            abonentCalls.add(callResponse);
        });
        assert call != null;
        return AbonentCallsResponse.builder()
                .id(call.getId())
                .numberPhone(call.getNumberPhone())
                .tariffIndex(call.getTariff().name())
                .payload(abonentCalls)
                .totalCost(call.getTotalCost())
                .monetaryUnit(call.getMonetaryUnit())
                .build();
    }
}
