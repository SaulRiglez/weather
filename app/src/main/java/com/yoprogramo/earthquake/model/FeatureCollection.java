
package com.yoprogramo.earthquake.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FeatureCollection implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;
    @SerializedName("bbox")
    @Expose
    private List<Double> bbox = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

}
