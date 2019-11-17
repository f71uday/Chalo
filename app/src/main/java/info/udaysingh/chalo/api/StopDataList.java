package info.udaysingh.chalo.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class StopDataList implements Serializable {
    @SerializedName("stopId")
    @Expose
    private String stopId;
    @SerializedName("stopName")
    @Expose
    private String stopName;
    @SerializedName("sequence")
    @Expose
    private Integer sequence;
    @SerializedName("latitute")
    @Expose
    private Double latitute;
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Double getLatitute() {
        return latitute;
    }

    public void setLatitute(Double latitute) {
        this.latitute = latitute;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}

