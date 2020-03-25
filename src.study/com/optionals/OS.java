package com.optionals;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public final class OS {

    static {
        log.info("Installing OS");
    }
}
