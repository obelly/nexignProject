package ru.nexign.hrsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tariffs")
@ToString
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String tariffIndex;

    String name;

    Double priceRubMin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var tariff = (Tariff) o;
        return Objects.equals(tariffIndex, tariff.tariffIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffIndex);
    }
}
