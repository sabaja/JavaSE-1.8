package com.optionals;

import lombok.Data;

@Data
public final class SoundCard {
    private String version;
    private USB usb;

    public SoundCard(String version, USB usb) {
        this.version = version;
        this.usb = usb;
    }
}