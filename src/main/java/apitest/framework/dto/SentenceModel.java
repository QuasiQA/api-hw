package apitest.framework.dto;

import com.google.gson.annotations.SerializedName;

public class SentenceModel {

    @SerializedName("sentence")
    private String sentence;

    @SerializedName("languageCode")
    private String languageCode;

    public String getSentence() {
        return sentence;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
