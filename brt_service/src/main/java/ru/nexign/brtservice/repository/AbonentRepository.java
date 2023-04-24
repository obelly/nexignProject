package ru.nexign.brtservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.nexign.brtservice.entity.Abonent;

import java.util.Optional;

public interface AbonentRepository extends JpaRepository<Abonent, Long> {

    Optional<Abonent> getAbonentByNumberPhone(String numberPhone);

}
