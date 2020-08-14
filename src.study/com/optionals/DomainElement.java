package com.optionals;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum DomainElement {
    MAIN_FRAME("MAIN_FRAME"),
    PERSONAL_COMPUTER("PERSONAL_COMPUTER"),
    WORKSTATION("WORKSTATION"),
    TABLET("TABLET"),
    SUPER_COMPUTER("SUPER_COMPUTE"),
    NOT_DEFINED("-");

    private static final Map<String, DomainElement> mappedValue = Stream.of(values()).collect(Collectors.toMap(DomainElement::getValue, Function.identity()));

    private final String value;


    DomainElement(String value) {
        this.value = value;
    }

    public static DomainElement fromValue(String value) {
        return mappedValue.get(value);
    }

    public String getValue() {
        return value;
    }
}
