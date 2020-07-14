package com.optionals;

import lombok.Data;

import java.io.Serializable;

@Data
public final class SoundCard implements Serializable {
    private static final long serialVersionUID = 636023621315123543L;
    private String version;
    private USB usb;

    public SoundCard(String version, USB usb) {
        this.version = version;
        this.usb = usb;
    }
}