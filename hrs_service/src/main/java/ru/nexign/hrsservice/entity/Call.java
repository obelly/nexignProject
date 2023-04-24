package ru.nexign.hrsservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.nexign.hrsservice.enums.TariffTypeEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "calls")

public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String numberPhone;
    Double totalCost;
    String monetaryUnit;
    @Enumerated(EnumType.STRING)
    TariffTypeEnum tariff;
    @Column
    @OneToMany(mappedBy = "call",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<PayLoad> payLoads;

    public Call(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
