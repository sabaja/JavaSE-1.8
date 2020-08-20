package com.optionals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Computer implements Serializable {
    private static final long serialVersionUID = 995616213150427439L;
    private BigInteger id;
    private OS os;
    private SoundCard soundCard;
    private String name;
    private List<DomainElement> type;
    private LocalDate createAt;

    public Computer(BigInteger id, SoundCard soundCard, String name) {
        this.id = id;
        this.soundCard = soundCard;
        this.name = name;
    }
}