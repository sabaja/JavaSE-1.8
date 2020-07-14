package com.optionals;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public final class OS implements Serializable {
    private static final long serialVersionUID = -14389162131589141L;

    static {
        log.info("Installing OS");
    }
}
