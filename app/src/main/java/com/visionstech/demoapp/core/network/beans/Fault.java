package com.visionstech.demoapp.core.network.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fault {

    private String faultstring;
    private Detail detail;


    @Getter
    class Detail {
        private String errorcode;
    }
}
