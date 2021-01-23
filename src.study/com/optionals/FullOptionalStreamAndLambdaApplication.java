package com.optionals;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.optionals")
public class FullOptionalStreamAndLambdaApplication {
    public static final String SOUNDCARD_VERSION = "v1.0.1";

    //v.1 v.2 v.3 etc...
    private static final String VERSION_REGEX = "v\\d\\.";
    private static final String USB_VERSION = "v.1.0.2";
    private static final String USB_VERSION_2 = "v.3.3.2";
    private static final String SOUNDCARD_VERSION_2 = "v0.4.1";

    @Autowired
//    @Qualifier("delegate")
    private ComputerMapper mapper;


    public static void main(String[] args) {
        String aa = "11220220";
        System.out.println(StringUtils.stripStart(aa, "0"));

        final ConfigurableApplicationContext context = SpringApplication.run(FullOptionalStreamAndLambdaApplication.class, args);
        log.info("Spring Boot is shutting down");
        context.close();
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            final List<Computer> computers = of();
            if (false) {
                streamsAndOptions();
                distinctByProperty();
                distinctByProperty2();
                printPyramid(6);
                defaultInLambda();
                printNumberOfUsbRate();
                printDeMorganLaw();
                log.info("Max date is {}", computeMaxLocalDate(computers));
                final Stream<Computer> mutableBuilderOfStream = createStreamBuilder(computers);
                mutableBuilderOfStream.forEach(e -> log.info("{}", e));
                createByGenerateRandomStreamInt().forEach(e -> log.info("{}", e));
                log.info("Start@{}", startUpTime(ctx));
                useOfFlatMap(computers).forEach(e -> log.info("{}", e));
                retrieveComputerElements(computers).forEach(e -> log.info("{}", e));
                log.info("{}", mapstructReturnEmptyList());
                log.info("{}", returnEmptyList());
            }

            retrieveComputerWithIdGreaterThanAverage();
        };
    }

    public List<Computer> of() {
        final Computer computer1 = createPC(BigInteger.valueOf(11001), SOUNDCARD_VERSION, USB_VERSION, 1_000_000, "C1", Collections.singletonList(ComputerType.MAIN_FRAME), LocalDate.of(2018, 1, 25));
        final Computer computer2 = createPC(BigInteger.valueOf(1202), null, "v.5.q.2", 1_000, "C2", Arrays.asList(ComputerType.PERSONAL_COMPUTER, ComputerType.WORKSTATION), LocalDate.of(2008, 4, 2));
        final Computer computer3 = new Computer(BigInteger.valueOf(1043), null, "C3");
        final Computer computer4 = createPC(BigInteger.valueOf(101004), SOUNDCARD_VERSION_2, "v.2.1.0", 11_11, "C4", Collections.singletonList(ComputerType.TABLET), LocalDate.of(2020, 6, 30));
        final Computer computer5 = new Computer(BigInteger.valueOf(999995), new SoundCard("V-a", createUsb("AVANT", null)), "C5");
        final Computer computer6 = null;
        final Computer computer7 = new Computer(BigInteger.valueOf(90909106), new SoundCard(SOUNDCARD_VERSION_2, null), "C7");
        final Computer computer8 = createPC(BigInteger.valueOf(107), "NOT-SUPPORTED", USB_VERSION_2, 1_2, "C8", Collections.singletonList(ComputerType.NOT_DEFINED), LocalDate.of(9999, 12, 31));
        final Computer computer9 = createPC(BigInteger.valueOf(8), SOUNDCARD_VERSION, USB_VERSION, 999_111, "C2", Arrays.asList(ComputerType.PERSONAL_COMPUTER, ComputerType.WORKSTATION, ComputerType.SUPER_COMPUTER), LocalDate.of(2017, 3, 13));
        final Computer computer10 = createPC(BigInteger.valueOf(7609), SOUNDCARD_VERSION, USB_VERSION, 2011, "C2", Arrays.asList(ComputerType.MAIN_FRAME, ComputerType.SUPER_COMPUTER), LocalDate.of(2018, 1, 25));
        return Arrays.asList(computer1, computer2, null, computer3, computer4, computer5, null, Objects.nonNull(computer6) ? computer6 : null, computer7, computer8, computer9, null, computer10);
    }

    private double computeAverageOfId(List<Computer> computers) {
        return Optional.of(computers.stream()
                .filter(Objects::nonNull)
                .map(Computer::getId)
                .filter(Objects::nonNull)
                .mapToInt(BigInteger::intValue)
                .summaryStatistics().getAverage()).orElse(null);
    }

    private void retrieveComputerWithIdGreaterThanAverage() {
        final List<Computer> computers = of();
        final BigDecimal avgBigDecimal = BigDecimal.valueOf(computeAverageOfId(computers));
        if (Objects.nonNull(avgBigDecimal)) {
            final Map<Boolean, Set<BigDecimal>> allIdsGreaterThanAvgMap = computers.stream()
                    .filter(Objects::nonNull)
                    .map(Computer::getId)
                    .filter(Objects::nonNull)
                    .map(BigDecimal::new)
                    .collect(groupingBy(id -> avgBigDecimal.compareTo(id) < 0, toSet()));

            if (MapUtils.isNotEmpty(allIdsGreaterThanAvgMap)) {
                allIdsGreaterThanAvgMap.entrySet()
                        .stream()
//                        .filter(e -> BooleanUtils.isTrue(e.getKey()))
                        .forEach(v -> log.info("{}", v));
            }
        }
    }

    private LocalDateTime startUpTime(ApplicationContext ctx) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ctx.getStartupDate()),
                TimeZone.getDefault().toZoneId());
    }

    private Computer createPC(BigInteger id, String soundCardVersion, String usbVersion, int usbId, String computerName, List<ComputerType> computerType, LocalDate createAt) {
        return new Computer(id, null, new SoundCard(soundCardVersion, createUsb(usbVersion, BigInteger.valueOf(usbId))), computerName, computerType, createAt);
    }

    private void printDeMorganLaw() {
        int x = 4;
        int y = 3;
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
        FullOptionalStreamAndLambdaApplication streamsAndLambda = new FullOptionalStreamAndLambdaApplication();
        return computers.stream()
                .filter(streamsAndLambda::isMainFrame)
                .collect(Collectors.toList());
    }

    private boolean isMainFrame(final Computer computer) {
        return Optional.ofNullable(computer).map(Computer::getType).isPresent() &&
                Optional.ofNullable(computer.getType())
                        .map(t -> t.contains(ComputerType.MAIN_FRAME))
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
            retrieveNullableSoundCards(computers);
            printSoundCards(uniqueSoundCards);

            if (isSoundCardVersioned(computers)
            ) {
                log.info("USBs");
                FullOptionalStreamAndLambdaApplication streamsAndLambda = new FullOptionalStreamAndLambdaApplication();
                computers.stream()
                        .filter(streamsAndLambda::isUSBVersioned)
                        .forEach(usb -> log.info("{}", usb.toString()));
                Map<String, String> soundCardsmap = computers.stream()
                        .filter(streamsAndLambda::isUSBVersioned)
                        .map(Computer::getSoundCard)
                        .collect(Collectors.toMap(SoundCard::getVersion,
                                soundCard -> soundCard.getUsb().getVersion()));
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

    private List<SoundCard> retrieveNullableSoundCards(List<Computer> computers) {
        return nonNull(computers) ? retrieveSoundCards(computers) : new ArrayList<>();

    }

    private List<SoundCard> retrieveSoundCards(List<Computer> computers) {
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
        FullOptionalStreamAndLambdaApplication streamsAndLambda = new FullOptionalStreamAndLambdaApplication();
        return computers.stream()
                .map(Computer::getSoundCard)
                .filter(Objects::nonNull)
                .map(SoundCard::getVersion)
                .filter(StringUtils::isNotEmpty)
                .anyMatch(streamsAndLambda::isVersioned);
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

    private Set<ComputerType> useOfFlatMap(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .flatMap(computer -> CollectionUtils.emptyIfNull(computer.getType()).stream())
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(ComputerType::getValue))
                ));
    }

    private List<ComputerElementBin> retrieveComputerElements(List<Computer> computers) {
        return computers.stream()
                .filter(Objects::nonNull)
                .map(mapper::computerToBin)
                .filter(e -> CollectionUtils.isNotEmpty(e.getComputerTypes()))
                .collect(Collectors.toList());
    }

    private List<Computer> mapstructReturnEmptyList() {
        List<Computer> list = new ArrayList<>();
        return this.mapper.mapstructReturnEmptyList(list);
    }

    private List<Computer> returnEmptyList() {
        List<Computer> list = null;
        return ListUtils.emptyIfNull(list);
    }
}