package apitest.framework.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class LanguageFileModel {

    @SerializedName("sentences")
    private List<SentenceModel> sentences;
    
    public List<SentenceModel> getSentences() {
        return sentences;
    }

}
