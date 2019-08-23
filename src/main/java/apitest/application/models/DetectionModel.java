package apitest.application.models;

import com.google.gson.annotations.SerializedName;

public class DetectionModel {

    @SerializedName("language_code")
    private String languageCode;

    @SerializedName("language_name")
    private String languageName;

    @SerializedName("probability")
    private double probability;

    @SerializedName("percentage")
    private double percentage;

    @SerializedName("reliable_result")
    private boolean reliableResult;

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public double getProbability() {
        return probability;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isReliableResult() {
        return reliableResult;
    }
}
