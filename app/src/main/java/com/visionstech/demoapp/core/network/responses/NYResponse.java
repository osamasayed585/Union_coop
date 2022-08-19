package com.visionstech.demoapp.core.network.responses;

import com.visionstech.demoapp.core.network.beans.NY;

import java.util.List;

import lombok.Getter;

@Getter
public class NYResponse extends BaseResponse{

    private List<NY> results;
}
