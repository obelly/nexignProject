package ru.nexign.brtservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.entity.Abonent;
import ru.nexign.brtservice.repository.AbonentRepository;
import ru.nexign.brtservice.ro.ChangeBalance;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AbonentService {
    AbonentRepository abonentRepository;

    public Abonent getAbonentByPhone(String phone) {
        return abonentRepository.getAbonentByPhone(phone).get();
    }

    public List<String> getAllAbonentsPhone() {
        List<Abonent> abonents = abonentRepository.findAll();
        List<String> abonentPhones = new ArrayList<>();
        abonents.forEach(abonent -> abonentPhones.add(abonent.getPhone()));
        return abonentPhones;
    }

    public void changeBalance(ChangeBalance response) {
        var abonent = abonentRepository.getAbonentByPhone(response.getPhoneNumber()).get();
        abonent.setBalance(abonent.getBalance() - response.getCost());
        abonentRepository.save(abonent);
    }
}
