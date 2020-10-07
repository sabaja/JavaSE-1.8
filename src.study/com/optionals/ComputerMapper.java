package com.optionals;

import org.mapstruct.*;

import java.util.List;

@DecoratedWith(ComputerMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface ComputerMapper {

    @IterableMapping(numberFormat = "$#.00")
    List<String> prices(List<Integer> prices);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    default List<Computer> mapstructReturnEmptyList(List<Computer> computers) {
        return computers;
    }

    @Mapping(target = "computerTypes", ignore = true)
    ComputerElementBin computerToBin(Computer computer);
}
