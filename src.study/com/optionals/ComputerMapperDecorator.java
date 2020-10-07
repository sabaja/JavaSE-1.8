package com.optionals;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Mapper(componentModel = "spring")
public class ComputerMapperDecorator implements ComputerMapper {

    @Autowired
    @Qualifier("delegate")
    private ComputerMapper delegate;

    @Override
    public List<String> prices(List<Integer> prices) {
        return delegate.prices(prices);
    }

    @Override
    public ComputerElementBin computerToBin(Computer computer) {
        ComputerElementBin computerElementBin = delegate.computerToBin(computer);
        List<ComputerType> elementTypes = new ArrayList<>(emptyIfNull(computer.getType()));
        computerElementBin.setComputerTypes(elementTypes);
        return computerElementBin;
    }
}
