package ru.nexign.crmservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nexign.crmservice.dto.AbonentPayRequestRO;
import ru.nexign.crmservice.dto.AbonentPayResponseRO;
import ru.nexign.crmservice.dto.TariffRequestRO;
import ru.nexign.crmservice.dto.TariffResponseRO;
import ru.nexign.crmservice.dto.UserRequestRO;
import ru.nexign.crmservice.dto.UserResponseRO;


@FeignClient(name = "brtClient", url = "http://localhost:8787")
public interface BrtServiceClient {
    @PatchMapping(value = "/abonent/pay/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbonentPayResponseRO> replenishAccount(AbonentPayRequestRO requestRO);
    @PatchMapping(value = "/manager/abonent", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseRO> addNewAbonent(@RequestBody UserRequestRO requestRO);
    @PatchMapping(value = "/manager/changeTariff", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TariffResponseRO> changeTariff(@RequestBody TariffRequestRO request);

}
