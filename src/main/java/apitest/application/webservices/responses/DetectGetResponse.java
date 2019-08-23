package apitest.application.webservices.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import apitest.application.models.DetectionModel;

public class DetectGetResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("results")
    private List<DetectionModel> detections;

    public boolean isSuccess() {
        return success;
    }

    public List<DetectionModel> getDetections() {
        return detections;
    }
}
