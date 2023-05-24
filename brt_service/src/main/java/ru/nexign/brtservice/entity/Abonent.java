package ru.nexign.brtservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "abonents")
public class Abonent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String numberPhone;

    Double balance;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id")
    Tariff tariff;

    Boolean isUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var client = (Abonent) o;
        return Objects.equals(numberPhone, client.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberPhone);
    }
}
