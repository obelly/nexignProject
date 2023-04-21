package ru.nexign.hrsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nexign.hrsservice.entity.Call;

import java.util.Optional;

@Repository
public interface CallRepository extends JpaRepository<Call,Long> {
    Optional<Call> getCallByNumberPhone(String phone);
}
