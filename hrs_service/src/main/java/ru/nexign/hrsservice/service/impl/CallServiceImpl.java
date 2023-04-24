package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.entity.Call;
import ru.nexign.hrsservice.repository.CallRepository;
import ru.nexign.hrsservice.service.CallService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CallServiceImpl implements CallService {

    CallRepository callRepository;

    @Override
    public Call getCallByPhone(String phone) {
        return callRepository.getCallByNumberPhone(phone).orElse(new Call(phone));
    }

    @Override
    public void createCall(Call call) {
        callRepository.save(call);
    }
}
