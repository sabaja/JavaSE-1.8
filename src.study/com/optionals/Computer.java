package com.optionals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Computer implements Serializable {
    private static final long serialVersionUID = 995616213150427439L;
    private OS os;
    private SoundCard soundCard;
    private String name;
    private List<ElementDomain> type;
    private LocalDate createAt;

    public Computer(SoundCard soundCard, String name) {
        this.soundCard = soundCard;
        this.name = name;
    }
}