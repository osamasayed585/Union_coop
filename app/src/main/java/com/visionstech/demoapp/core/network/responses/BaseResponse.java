package com.visionstech.demoapp.core.network.responses;

import com.google.gson.annotations.SerializedName;

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
    /*   copyright
    *  Copyright (c) 2022 The New York Times Company.  All Rights Reserved.
    *
    * */

    private String status;

    private String copyright;

    @SerializedName("num_results")
    private String numResults;
}
