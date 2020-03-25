package com.optionals;


import lombok.Data;

@Data
public final class USB {
    private String version;

    public USB(String version) {
        this.version = version;
    }
}
