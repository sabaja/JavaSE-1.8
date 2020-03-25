package com.optionals;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class FullOptionalsAndStreamsAndLambda {

    //v.1 v.2 v.3 etc...
    private static final String VERSION_REGEX = "v\\d\\.";

    public static List<Computer> of() {
        final Computer computer1 = new Computer(new SoundCard("v1.0.1", new USB("v0.0.1")), "C1");
        final Computer computer2 = new Computer(new SoundCard(null, new USB("v0.3.1")), "C2");
        final Computer computer3 = new Computer(null, "C3");
        final Computer computer4 = new Computer(new SoundCard("v0.4.1", new USB("v4.0.1")), "C4");
        final Computer computer5 = new Computer(new SoundCard("V-a", new USB("AVANT")), "C5");
        final Computer computer6 = new Computer();
        final Computer computer7 = new Computer(new SoundCard("v0.4.1", null), "C7");
        final Computer computer8 = new Computer(new SoundCard("NOT-SUPPORTED", new USB("v3.3.1")), "C8");
        return ImmutableList.of(computer1, computer2, computer3, computer4, computer5, computer6, computer7, computer8);
    }

    public static void main(String[] args) {
        streamsAndOptions();
    }

    private static void streamsAndOptions() {
        List<Computer> computers = null;
        if (isEven()) {
            computers = of();
            String cs = computers.stream()
                    .filter(Objects::nonNull)
                    .map(Optional::of).findAny()
                    .flatMap(Function.identity())
                    .map(Computer::getSoundCard)
                    .map(SoundCard::getVersion).filter(s -> StringUtils.containsIgnoreCase(s, "V")).orElse(StringUtils.EMPTY);
            log.info(cs);
        }
        //stream da computers / map di SoundCard / filter nonNull / collect to Set
        if (Objects.nonNull(computers)) {
            Set<SoundCard> soundCards = retrieveSoundCards(computers);
            printSoundCards(soundCards);
            if (isSoundCardVersioned(computers)
            ) {
                log.info("USBs");
                computers.stream()
                        .filter(FullOptionalsAndStreamsAndLambda::isUSBVersioned)
                        .forEach(usb -> log.info("{}", usb.toString()));
                Map<String, String> soundCardsmap = computers.stream()
                        .filter(FullOptionalsAndStreamsAndLambda::isUSBVersioned)
                        .map(Computer::getSoundCard)
                        .collect(Collectors.toMap(SoundCard::getVersion, soundCard -> soundCard.getUsb().getVersion()));
                soundCardsmap.forEach((key, value) -> log.info("{} - {}", key, value));
            }
        }
    }

    private static void printSoundCards(Set<SoundCard> soundCards) {
        log.info("SondCards:");
        soundCards.forEach(sc -> log.info("{}", sc.toString()));
    }

    private static Set<SoundCard> retrieveSoundCards(List<Computer> computers) {
        return computers.stream()
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .filter(soundCard -> StringUtils.isNoneEmpty(soundCard.getVersion()))
                .collect(Collectors.toSet());
    }

    private static boolean isUSBVersioned(Computer computer) {
        return Optional.ofNullable(computer)
                .map(Computer::getSoundCard)
                .map(SoundCard::getUsb)
                .map(USB::getVersion)
                .isPresent();
    }

    private static boolean isSoundCardVersioned(List<Computer> computers) {
        return computers.stream()
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .map(SoundCard::getVersion)
                .filter(StringUtils::isNotEmpty)
                .anyMatch(FullOptionalsAndStreamsAndLambda::isVersioned);
    }

    private static boolean isEven() {
        int nextInt = RandomUtils.nextInt(0, 10);
        boolean even = nextInt % 2 == 0;
        log.info("{} è pari? {}}", nextInt, even);
        return even;
    }

    private static boolean isVersioned(String version) {
        Pattern pattern = Pattern.compile(VERSION_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(version);
        return matcher.find();
    }

}

@Data
@NoArgsConstructor
class Computer {
    private SoundCard soundCard;
    private String name;

    public Computer(SoundCard soundCard, String name) {
        this.soundCard = soundCard;
        this.name = name;
    }
}

@Data
final class SoundCard {
    private String version;
    private USB usb;

    public SoundCard(String version, USB usb) {
        this.version = version;
        this.usb = usb;
    }
}

@Data
final class USB {
    private String version;

    public USB(String version) {
        this.version = version;
    }
}
