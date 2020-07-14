package com.optionals;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class USB implements Serializable {
    private static final long serialVersionUID = 6360236213150427439L;
    private String version;
    private BigInteger id;

}
