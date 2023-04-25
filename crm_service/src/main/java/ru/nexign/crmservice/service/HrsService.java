package ru.nexign.crmservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nexign.crmservice.client.HrsServiceClient;
import ru.nexign.crmservice.dto.AbonentCallsResponse;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HrsService {
    HrsServiceClient hrsServiceClient;

    public ResponseEntity<AbonentCallsResponse> detailingCalls(String numberPhone){
        return hrsServiceClient.detailingCalls(numberPhone);
    }
}
