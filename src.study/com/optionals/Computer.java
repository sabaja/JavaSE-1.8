package com.optionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Computer {
    private OS os;
    private SoundCard soundCard;
    private String name;

    public Computer(SoundCard soundCard, String name) {
        this.soundCard = soundCard;
        this.name = name;
    }
}