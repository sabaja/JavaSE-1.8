package com.optionals;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComputerMapper {

    @IterableMapping(numberFormat = "$#.00")
    List<String> prices(List<Integer> prices);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    default List<Computer> mapstructReturnEmptyList(List<Computer> computers) {
        return computers;
    }
}
