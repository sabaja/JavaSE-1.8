package com.optionals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-20T12:00:25+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class ComputerMapperImpl implements ComputerMapper {

    @Override
    public List<String> prices(List<Integer> prices) {
        if ( prices == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( prices.size() );
        for ( Integer integer : prices ) {
            list.add( new DecimalFormat( "$#.00" ).format( integer ) );
        }

        return list;
    }
}
