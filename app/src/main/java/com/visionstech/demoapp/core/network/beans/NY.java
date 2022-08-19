package com.visionstech.demoapp.core.network.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NY {

    private String uri;
    private String url;
    private long id;
    @SerializedName("assetId")
    private long asset_id;
    private String source;
    @SerializedName("published_date")
    private String publishedDate;
    private String updated;
    private String section;
    private String subsection;
    private String nytdsection;
    @SerializedName("adx_keywords")
    private String adxKeywords;
    private int column;    // todo what is dataType for this var
    private String byline;
    private String type;
    private String title;
    @SerializedName("abstract")
    private String abstract_;
    @SerializedName("des_facet")
    private List<String> desFacet;
    @SerializedName("org_facet")
    private List<String> orgFacet;
    @SerializedName("per_facet")
    private List<String> perFacet;
    @SerializedName("geo_facet")
    private List<String> geoFacet;
    private List<Media> media;
    @SerializedName("eta_id")
    private int eta_id;

}
