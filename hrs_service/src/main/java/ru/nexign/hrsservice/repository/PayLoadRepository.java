package ru.nexign.hrsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nexign.hrsservice.entity.Call;
import ru.nexign.hrsservice.entity.PayLoad;

import java.util.List;

@Repository
public interface PayLoadRepository extends JpaRepository<PayLoad,Long> {

    List<PayLoad> getAllByCall (Call call);

}
