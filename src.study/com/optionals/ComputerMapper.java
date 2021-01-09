package com.optionals;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComputerMapper {

    @IterableMapping(numberFormat = "$#.00")
    List<String> prices(List<Integer> prices);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    default List<Computer> mapstructReturnEmptyList(List<Computer> computers) {
        return computers;
    }

    @Mapping(target = "computerTypes", ignore = true)
    ComputerElementBin computerToElementBin(Computer computer);


    default ComputerElementBin computerToBin(Computer computer) {
        ComputerElementBin computerElementBin = computerToElementBin(computer);
        List<ComputerType> elementTypes = new ArrayList<>(emptyIfNull(computer.getType()));
        computerElementBin.setComputerTypes(elementTypes);
        return computerElementBin;
    }
}

