package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PayLoadServiceImpl implements PayLoadService {
    PayLoadRepository payLoadRepository;
    CallRepository callRepository;

    public void createPayLoad(PayLoad payLoad) {
        payLoadRepository.save(payLoad);
    }

    @Override
    public AbonentCallsResponse detailingCalls(String numberPhone) {
        var call = callRepository.getCallByNumberPhone(numberPhone).orElse(null);
        var allPayLoadsByCall = payLoadRepository.getAllByCall(call);
        List<CallResponse> abonentCalls = new ArrayList<>();
        allPayLoadsByCall.forEach(payLoad -> {
            var callResponse = new CallResponse(
                    payLoad.getCallType().getNumber(),
                    payLoad.getStartTime(),
                    payLoad.getEndTime(),
                    payLoad.getDuration(),
                    payLoad.getCost());
            abonentCalls.add(callResponse);
        });
        assert call != null;
        return new AbonentCallsResponse(
                call.getId(),
                call.getNumberPhone(),
                call.getTariff().getNumber(),
                abonentCalls,
                call.getTotalCost(),
                call.getMonetaryUnit());
    }
}
