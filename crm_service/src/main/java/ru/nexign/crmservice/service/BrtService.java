package ru.nexign.crmservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.crmservice.client.BrtServiceClient;
import ru.nexign.crmservice.dto.AbonentPayRequestRO;
import ru.nexign.crmservice.dto.AbonentPayResponseRO;
import ru.nexign.crmservice.dto.TariffRequestRO;
import ru.nexign.crmservice.dto.TariffResponseRO;
import ru.nexign.crmservice.dto.UserRequestRO;
import ru.nexign.crmservice.dto.UserResponseRO;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrtService {
    BrtServiceClient brtServiceClient;
    public ResponseEntity<AbonentPayResponseRO> replenishAccount(AbonentPayRequestRO requestRO) {
       return brtServiceClient.replenishAccount(requestRO);
    }

    public ResponseEntity<UserResponseRO> addNewAbonent(@RequestBody UserRequestRO requestRO) {
        return brtServiceClient.addNewAbonent(requestRO);
    }

    public ResponseEntity<TariffResponseRO> changeTariff(@RequestBody TariffRequestRO request) {
        return brtServiceClient.changeTariff(request);
    }
}
