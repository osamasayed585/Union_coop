package com.visionstech.demoapp.core.network.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Media {

    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    @SerializedName("approved_for_syndication")
    private String approvedForSyndication;
    @SerializedName("media-metadata")
    private List<MediaMetadata> mediaMetadata;
}
