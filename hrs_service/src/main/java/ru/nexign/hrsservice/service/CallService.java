package ru.nexign.hrsservice.service;

import ru.nexign.hrsservice.entity.Call;

public interface CallService {
    Call getCallByPhone(String phone);

    void createCall(Call call);
}
