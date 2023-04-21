package ru.nexign.brtservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nexign.brtservice.entity.Abonent;


import java.util.List;
import java.util.Optional;

public interface AbonentRepository extends JpaRepository<Abonent, Long> {

//    @Query(value = "SELECT new "
//            + "ru.nexign.brtservice.entity.Tariff(t.id, t.tariffIndex, t.name, t.priceRubMin) " +
//            "from Abonent a join Tariff t "
//            + " where a.phone = :phone")
//    Tariff getTariffByPhone(Long phone);

    Optional<Abonent> getAbonentByPhone(String phone);

}
