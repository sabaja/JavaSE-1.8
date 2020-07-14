package com.optionals;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum ElementDomain {
    MAIN_FRAME("MAIN_FRAME"),
    PERSONAL_COMPUTER("PERSONAL_COMPUTER"),
    WORKSTATION("WORKSTATION"),
    TABLET("TABLET"),
    SUPER_COMPUTER("SUPER_COMPUTE"),
    NOT_DEFINED("-");

    private static final Map<String, ElementDomain> mappedValue = Stream.of(values()).collect(Collectors.toMap(ElementDomain::getValue, Function.identity()));

    private final String value;


    ElementDomain(String value) {
        this.value = value;
    }

    public static ElementDomain fromValue(String value) {
        return mappedValue.get(value);
    }

    public String getValue() {
        return value;
    }
}
