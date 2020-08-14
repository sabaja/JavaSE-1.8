package com.optionals;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

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
@SpringBootApplication
public class FullOptionalsAndStreamsAndLambdaApplication {
    public static final String SOUNDCARD_VERSION = "v1.0.1";

    //v.1 v.2 v.3 etc...
    private static final String VERSION_REGEX = "v\\d\\.";
    private static final String USB_VERSION = "v.1.0.2";
    private static final String USB_VERSION_2 = "v.3.3.2";
    private static final String SOUNDCARD_VERSION_2 = "v0.4.1";

    @Autowired
    private ComputerMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(FullOptionalsAndStreamsAndLambdaApplication.class, args);
    }

    public List<Computer> of() {
        final Computer computer1 = createPC(SOUNDCARD_VERSION, USB_VERSION, 1_000_000, "C1", Collections.singletonList(DomainElement.MAIN_FRAME), LocalDate.of(2018, 1, 25));
        final Computer computer2 = createPC(null, "v.5.q.2", 1_000, "C2", Arrays.asList(DomainElement.PERSONAL_COMPUTER, DomainElement.WORKSTATION), LocalDate.of(2008, 4, 2));
        final Computer computer3 = new Computer(null, "C3");
        final Computer computer4 = createPC(SOUNDCARD_VERSION_2, "v.2.1.0", 11_11, "C4", Collections.singletonList(DomainElement.TABLET), LocalDate.of(2020, 6, 30));
        final Computer computer5 = new Computer(new SoundCard("V-a", createUsb("AVANT", null)), "C5");
        final Computer computer6 = null;
        final Computer computer7 = new Computer(new SoundCard(SOUNDCARD_VERSION_2, null), "C7");
        final Computer computer8 = createPC("NOT-SUPPORTED", USB_VERSION_2, 1_2, "C8", Collections.singletonList(DomainElement.NOT_DEFINED), LocalDate.of(9999, 12, 31));
        final Computer computer9 = createPC(SOUNDCARD_VERSION, USB_VERSION, 999_111, "C2", Arrays.asList(DomainElement.PERSONAL_COMPUTER, DomainElement.WORKSTATION, DomainElement.SUPER_COMPUTER), LocalDate.of(2017, 3, 13));
        final Computer computer10 = createPC(SOUNDCARD_VERSION, USB_VERSION, 2011, "C2", Arrays.asList(DomainElement.MAIN_FRAME, DomainElement.SUPER_COMPUTER), LocalDate.of(2018, 1, 25));
        return Arrays.asList(computer1, computer2, null, computer3, computer4, computer5, null, computer6, computer7, computer8, computer9, null, computer10);
    }

    private Computer createPC(String soundCardVersion, String usbVersion, int usbId, String computerName, List<DomainElement> domainElement, LocalDate createAt) {
        return new Computer(null, new SoundCard(soundCardVersion, createUsb(usbVersion, BigInteger.valueOf(usbId))), computerName, domainElement, createAt);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            if (false) {
                streamsAndOptions();
                distinctByProperty();
                distinctByProperty2();
                printPyramid(6);
                defaultInLambda();
                printNumberOfUsbRate();
                printDeMorganLaw();
                log.info("Max date is {}", computeMaxLocalDate(of()));
                final Stream<Computer> mutableBuilderOfStream = createStreamBuilder(of());
                mutableBuilderOfStream.forEach(e -> log.info("{}", e));
                createByGenerateRandomStreamInt().forEach(e -> log.info("{}", e));
                useOfFlatMap(of()).forEach(e -> log.info("{}", e));
            }
            log.info("{}", mapstructReturnEmptyList());
            log.info("{}", returnEmptyList());
        };
    }

    private void printDeMorganLaw() {
        int x = 4, y = 3;
        if ((!(x < 3 || y > 2)) == (x >= 3 && y <= 2)) {
            log.info("first case");
        } else {
            log.info("second case");
        }
        int a = 10;
        int b = 5;
        int c = 20;
        log.info("{}", a < c && b > c);
        log.info((a < c || b > c) + "\n");

    }

    private void printNumberOfUsbRate() {
        final List<Computer> computers = of();

        final BigInteger totalRatePC = retrieveMainFrameComputer(computers).stream()
                .map(computer -> Optional.ofNullable(computer.getSoundCard())
                        .map(SoundCard::getUsb)
                        .map(USB::getId)
                        .orElse(BigInteger.ZERO))
                .reduce(BigInteger.ONE, BigInteger::add);
        log.info("TOTAL of usb id: " + totalRatePC);
    }

    private List<Computer> retrieveMainFrameComputer(List<Computer> computers) {
        FullOptionalsAndStreamsAndLambdaApplication streamsAndLambda = new FullOptionalsAndStreamsAndLambdaApplication();
        return computers.stream()
                .filter(streamsAndLambda::isMainFrame)
                .collect(Collectors.toList());
    }

    private boolean isMainFrame(final Computer computer) {
        return Optional.ofNullable(computer).map(Computer::getType).isPresent() &&
                Optional.ofNullable(computer.getType())
                        .map(t -> t.contains(DomainElement.MAIN_FRAME))
                        .orElse(false);
    }

    private USB createUsb(final String version, final BigInteger id) {
        return USB.builder()
                .id(id)
                .version(version)
                .build();
    }

    /**
     * https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
     */
    private void distinctByProperty() {
        List<Computer> computers = of();
        Set<String> distComputers = new HashSet<>(computers.size());
        computers
                .stream()
                .filter(Objects::nonNull)
                .filter(c -> distComputers.add(c.getName()))
                .collect(Collectors.toList())
                .forEach(e -> log.info("{}", e));

    }

    private void distinctByProperty2() {
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
                .forEach(e -> log.info("{}", e));

    }

    private void printPyramid(final int num) {
        for (int i = 0; i < num; i++) {
            for (int y = 0; y <= i; y++) {
                log.info("{}", y < i ? "*" : "*\n");
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    // Structured Blocked Comment
    ///////////////////////////////////////////////////////////////////////////

    private void defaultInLambda() {
        List<Computer> computers = null;
        final long COUNT = Optional.ofNullable(computers)
                .orElse(of())
                .stream()
                .filter(Objects::nonNull)
                .count();
        log.info("FullOptionalsAndStreamsAndLambda.defaultInLambda " + COUNT);
    }

    private void streamsAndOptions() {
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
            retrieveSoundCards(computers);
            printSoundCards(uniqueSoundCards);

            if (isSoundCardVersioned(computers)
            ) {
                log.info("USBs");
                FullOptionalsAndStreamsAndLambdaApplication streamsAndLambda = new FullOptionalsAndStreamsAndLambdaApplication();
                computers.stream()
                        .filter(streamsAndLambda::isUSBVersioned)
                        .forEach(usb -> log.info("{}", usb.toString()));
                Map<String, String> soundCardsmap = computers.stream()
                        .filter(streamsAndLambda::isUSBVersioned)
                        .map(Computer::getSoundCard)
                        .collect(Collectors.toMap(SoundCard::getVersion, soundCard -> soundCard.getUsb().getVersion()));
                soundCardsmap.forEach((key, value) -> log.info("{} - {}", key, value));
            }
        }
    }

    private void checkIfEmpty(List<Computer> computers) {
        if (CollectionUtils
                .emptyIfNull(computers)
                .stream()
                .allMatch(Objects::isNull)) {
            log.info("VUOTO");
        }
        if (Optional.ofNullable(computers)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .allMatch(Objects::isNull)) {
            log.info("VUOTO 2");
        }
    }

    private int createRandomInt() {
        return new Random().ints(0, 3).findFirst().getAsInt();
    }

    private void printSoundCards(Set<SoundCard> soundCards) {
        log.info("SondCards:");
        soundCards.forEach(sc -> log.info("{}", sc.toString()));
    }

    private Set<SoundCard> retrieveUniqueSoundCards(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .filter(soundCard -> StringUtils.isNoneEmpty(soundCard.getVersion()))
                .collect(Collectors.toSet());
    }

    private List<SoundCard> retrieveSoundCards(List<Computer> computers) {
        return nonNull(computers) ? retrieveNonNullSoundCard(computers) : null;

    }

    private List<SoundCard> retrieveNonNullSoundCard(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .filter(soundCard -> StringUtils.isNoneEmpty(soundCard.getVersion()))
                .collect(Collectors.toList());
    }

    private boolean isUSBVersioned(Computer computer) {
        return Optional.ofNullable(computer)
                .map(Computer::getSoundCard)
                .map(SoundCard::getUsb)
                .map(USB::getVersion)
                .isPresent();
    }

    private boolean isSoundCardVersioned(List<Computer> computers) {
        FullOptionalsAndStreamsAndLambdaApplication streamsAndLambda = new FullOptionalsAndStreamsAndLambdaApplication();
        return computers.stream()
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .map(SoundCard::getVersion)
                .filter(StringUtils::isNotEmpty)
                .anyMatch(streamsAndLambda::isVersioned);
    }

    private boolean isEven() {
        int nextInt = RandomUtils.nextInt(0, 10);
        boolean even = nextInt % 2 == 0;
        log.info("{} è pari? {}}", nextInt, even);
        return even;
    }

    private boolean isVersioned(String version) {
        Pattern pattern = Pattern.compile(VERSION_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(version);
        return matcher.find();
    }

    private List<LocalDate> createdistinctLocalDates(List<Computer> computers) {
        final List<LocalDate> localDates = computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getCreateAt)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        log.info("{}", localDates);
        return localDates;
    }

    private LocalDate computeMaxLocalDate(List<Computer> computers) {
        return createdistinctLocalDates(computers)
                .stream()
                .max(LocalDate::compareTo)
                .orElse(null);
    }

    private Stream<Computer> createStreamBuilder(List<Computer> computers) {
        Stream.Builder<Computer> computerBuilder = Stream.builder();
        computers.forEach(computerBuilder);
        return computerBuilder.build();
    }

    private Stream<Integer> createByGenerateRandomStreamInt() {
        return Stream.generate(new java.util.Random()::nextInt).limit(10);
    }

    private List<DomainElement> useOfFlatMap(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .flatMap(computer -> CollectionUtils.emptyIfNull(computer.getType()).stream())
                .collect(Collectors.toList());
    }

    private List<Computer> mapstructReturnEmptyList() {
        List<Computer> list = new ArrayList<>();
        return mapper.mapstructReturnEmptyList(list);
    }

    private List<Computer> returnEmptyList() {
        List<Computer> list = null;
        return ListUtils.emptyIfNull(list);
    }
}