package ru.nexign.hrsservice.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "calls")
@ToString
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String numberPhone;

    Double totalCost;

    String monetaryUnit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id")
    Tariff tariff;
}
