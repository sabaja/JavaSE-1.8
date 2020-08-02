package com.optionals;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@Slf4j
public class FullOptionalsAndStreamsAndLambda {

    public static final String SOUNDCARD_VERSION = "v1.0.1";
    //v.1 v.2 v.3 etc...
    private static final String VERSION_REGEX = "v\\d\\.";
    private static final String USB_VERSION = "v.1.0.2";
    private static final String USB_VERSION_2 = "v.3.3.2";
    private static final String SOUNDCARD_VERSION_2 = "v0.4.1";

    public static List<Computer> of() {
        final Computer computer1 = createPC(SOUNDCARD_VERSION, USB_VERSION, 1_000_000, "C1", Collections.singletonList(ElementDomain.MAIN_FRAME), LocalDate.of(2018, 1, 25));
        final Computer computer2 = createPC(null, "v.5.q.2", 1_000, "C2", Arrays.asList(ElementDomain.PERSONAL_COMPUTER, ElementDomain.WORKSTATION), LocalDate.of(2008, 4, 2));
        final Computer computer3 = new Computer(null, "C3");
        final Computer computer4 = createPC(SOUNDCARD_VERSION_2, "v.2.1.0", 11_11, "C4", Collections.singletonList(ElementDomain.TABLET), LocalDate.of(2020, 6, 31));
        final Computer computer5 = new Computer(new SoundCard("V-a", createUsb("AVANT", null)), "C5");
        final Computer computer6 = null;
        final Computer computer7 = new Computer(new SoundCard(SOUNDCARD_VERSION_2, null), "C7");
        final Computer computer8 = createPC("NOT-SUPPORTED", USB_VERSION_2, 1_2, "C8", Collections.singletonList(ElementDomain.NOT_DEFINED), LocalDate.of(9999, 12, 31));
        final Computer computer9 = createPC(SOUNDCARD_VERSION, USB_VERSION, 999_111, "C2", Arrays.asList(ElementDomain.PERSONAL_COMPUTER, ElementDomain.WORKSTATION, ElementDomain.SUPER_COMPUTER), LocalDate.of(2017, 3, 13));
        final Computer computer10 = createPC(SOUNDCARD_VERSION, USB_VERSION, 2011, "C2", Arrays.asList(ElementDomain.MAIN_FRAME, ElementDomain.SUPER_COMPUTER), LocalDate.of(2018, 1, 25));
        return Arrays.asList(computer1, computer2, null, computer3, computer4, computer5, null, computer6, computer7, computer8, computer9, null, computer10);
    }

    private static Computer createPC(String soundCardVersion, String usbVersion, int usbId, String computerName, List<ElementDomain> elementDomain, LocalDate createAt) {
        return new Computer(null, new SoundCard(soundCardVersion, createUsb(usbVersion, BigInteger.valueOf(usbId))), computerName, elementDomain, createAt);
    }

    public static void main(String[] args) {
        if (false) {
            streamsAndOptions();
            distinctByProperty();
            System.out.println();
            distinctByProperty2();
            printPyramid(6);
            defaultInLambda();
            printNumberOfUsbRate();
        }

        printDeMorganLaw();
    }

    private static void printDeMorganLaw() {
        int x = 4, y = 3;
        if ((!(x < 3 || y > 2)) == (x >= 3 && y <= 2)) {
            System.out.println("first case");
        } else {
            System.out.println("second case");
        }
        int a = 10;
        int b = 5;
        int c = 20;
        System.out.println(a < c && b > c);
        System.out.println((a < c || b > c) + "\n");

    }

    private static void printNumberOfUsbRate() {
        final List<Computer> computers = of();

        final BigInteger totalRatePC = retrieveMainFrameComputer(computers).stream()
                .map(computer -> Optional.ofNullable(computer.getSoundCard())
                        .map(SoundCard::getUsb)
                        .map(USB::getId)
                        .orElse(BigInteger.ZERO))
                .reduce(BigInteger.ONE, BigInteger::add);
        System.out.println("TOTAL of usb id: " + totalRatePC);
    }


    private static List<Computer> retrieveMainFrameComputer(List<Computer> computers) {
        return computers.stream()
                .filter(FullOptionalsAndStreamsAndLambda::isMainFrame)
                .collect(Collectors.toList());
    }

    private static boolean isMainFrame(final Computer computer) {
        return Optional.ofNullable(computer).map(Computer::getType).isPresent() &&
                Optional.ofNullable(computer.getType())
                        .map(t -> t.contains(ElementDomain.MAIN_FRAME))
                        .orElse(false);
    }

    private static USB createUsb(final String version, final BigInteger id) {
        return USB.builder()
                .id(id)
                .version(version)
                .build();
    }

    /**
     * https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
     */
    private static void distinctByProperty() {
        List<Computer> computers = of();
        Set<String> distComputers = new HashSet<>(computers.size());
        computers
                .stream()
                .filter(Objects::nonNull)
                .filter(c -> distComputers.add(c.getName())).collect(Collectors.toList()).forEach(System.out::println);

    }

    private static void distinctByProperty2() {
        List<Computer> computers = of();
        computers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Computer::getName,
                        obj -> obj,
                        (first, second) -> first
                        // pick the first if multiple values have the same key
                )).values()
                .stream()
                .filter(computer -> nonNull(computer.getName()))
                .sorted(Comparator.comparing(Computer::getName))
                .forEach(System.out::println);

    }


    private static void printPyramid(final int NUM) {
        for (int i = 0; i < NUM; i++) {
            for (int y = 0; y <= i; y++) {
                System.out.printf("%s", y < i ? "*" : "*\n");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Structured Blocked Comment
    ///////////////////////////////////////////////////////////////////////////
    private static void defaultInLambda() {
        List<Computer> computers = null;
        final long COUNT = Optional.ofNullable(computers)
                .orElse(of())
                .stream()
                .filter(Objects::nonNull)
                .count();
        System.out.println("FullOptionalsAndStreamsAndLambda.defaultInLambda " + COUNT);
    }

    private static void streamsAndOptions() {
        List<Computer> computers = null;
        checkIfEmpty(computers);
        final boolean b = BooleanUtils.toBoolean(createRandomInt());
        if (b) {
            computers = of();
            String computerStream = computers.stream()
                    .filter(Objects::nonNull)
                    .map(Optional::of).findAny()
                    .flatMap(Function.identity())
                    .map(Computer::getSoundCard)
                    .map(SoundCard::getVersion).filter(s -> StringUtils.containsIgnoreCase(s, "V")).orElse(StringUtils.EMPTY);
            log.info(computerStream);
        }
        //stream da computers / map di SoundCard / filter nonNull / collect to Set
        if (CollectionUtils.isNotEmpty(computers)) {
            Set<SoundCard> uniqueSoundCards = retrieveUniqueSoundCards(computers);
            List<SoundCard> soundCards = retrieveSoundCards(computers);
            printSoundCards(uniqueSoundCards);

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

    private static void checkIfEmpty(List<Computer> computers) {
        if (CollectionUtils
                .emptyIfNull(computers)
                .stream()
                .allMatch(Objects::isNull)) {
            System.out.println("VUOTO");
        }
        if (Optional.ofNullable(computers)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .allMatch(Objects::isNull)) {
            System.out.println("VUOTO 2");
        }
    }

    private static int createRandomInt() {
        return new Random().ints(0, 3).findFirst().getAsInt();
    }

    private static void printSoundCards(Set<SoundCard> soundCards) {
        log.info("SondCards:");
        soundCards.forEach(sc -> log.info("{}", sc.toString()));
    }

    private static Set<SoundCard> retrieveUniqueSoundCards(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .filter(soundCard -> StringUtils.isNoneEmpty(soundCard.getVersion()))
                .collect(Collectors.toSet());
    }

    private static List<SoundCard> retrieveSoundCards(List<Computer> computers) {
        return nonNull(computers) ? retrieveNonNullSoundCard(computers) : null;

    }

    private static List<SoundCard> retrieveNonNullSoundCard(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .filter(soundCard -> StringUtils.isNoneEmpty(soundCard.getVersion()))
                .collect(Collectors.toList());
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

    private static List<LocalDate> createLocalDates(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getCreateAt)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static LocalDate computeMaxLocalDate(List<Computer> computers) {
        return createLocalDates(computers)
                .stream()
                .max(LocalDate::compareTo)
                .orElse(null);
    }
}