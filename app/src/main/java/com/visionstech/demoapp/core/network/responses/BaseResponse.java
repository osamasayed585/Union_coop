package com.visionstech.demoapp.core.network.responses;

import com.google.gson.annotations.SerializedName;
import com.visionstech.demoapp.core.network.beans.Fault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    /*  fault:
             faultstring: "Invalid ApiKey",

    */

    private Fault fault;

    private String status;

    private String copyright;

    @SerializedName("num_results")
    private String numResults;
}
