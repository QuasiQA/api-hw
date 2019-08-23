package apitest.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import apitest.framework.dto.LanguageFileModel;
import apitest.framework.dto.SentenceModel;
import apitest.framework.utils.FileUtils;
import apitest.framework.utils.HttpStatus;
import apitest.framework.utils.Properties;
import apitest.application.webservices.responses.DetectGetResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import retrofit2.Response;
import apitest.steps.DetectEndpointSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class DetectEndpointTest {

    private DetectEndpointSteps detectEndpointSteps;

    @DataProvider
    private Object[][] positiveTestData() {
        String deutsch = "src/test/resources/sentences/correct/deutsch.json";
        String english = "src/test/resources/sentences/correct/english.json";
        List<SentenceModel> positiveScenarios = new ArrayList<>();
        List<SentenceModel> englishSentences = FileUtils.getJsonFromFile(english, LanguageFileModel.class).getSentences();
        List<SentenceModel> deutschSentences = FileUtils.getJsonFromFile(deutsch, LanguageFileModel.class).getSentences();

        positiveScenarios.addAll(englishSentences);
        positiveScenarios.addAll(deutschSentences);

        Object[][] testData = new Object[positiveScenarios.size()][2];
        for (int i = 0; i < positiveScenarios.size(); i++) {
            testData[i][0] = positiveScenarios.get(i).getSentence();
            testData[i][1] = positiveScenarios.get(i).getLanguageCode();
        }
        return testData;
    }

    @DataProvider
    private Object[][] negativeTestData() {
        String multilanguage = "src/test/resources/sentences/incorrect/multilanguage.json";
        List<SentenceModel> multilanguageSentences = FileUtils.getJsonFromFile(multilanguage, LanguageFileModel.class).getSentences();

        Object[][] testData = new Object[multilanguageSentences.size()][1];
        for (int i = 0; i < multilanguageSentences.size(); i++) {
            testData[i][0] = multilanguageSentences.get(i).getSentence();
        }
        return testData;
    }

    @DataProvider
    private Object[][] brokenTestData() {
        String multilanguage = "src/test/resources/sentences/incorrect/broken.json";
        List<SentenceModel> notSentences = FileUtils.getJsonFromFile(multilanguage, LanguageFileModel.class).getSentences();

        Object[][] testData = new Object[notSentences.size()][1];
        for (int i = 0; i < notSentences.size(); i++) {
            testData[i][0] = notSentences.get(i).getSentence();
        }
        return testData;
    }

    @BeforeClass
    public void setUpClass() {
        detectEndpointSteps = new DetectEndpointSteps();
    }

    @Test(dataProvider = "positiveTestData", groups = {"regression", "positive"})
    @Description("Positive test for successfully detected language")
    @TmsLink("TC-1")
    public void successfulLanguageDetectionTest(String sentence, String expectedLanguage) {
        Response<DetectGetResponse> response = detectEndpointSteps.getDetections(sentence);
        assertThat(response.code())
                .as("Check status code is %s", HttpStatus.OK)
                .isEqualTo(HttpStatus.OK);
        assertThat(response.body().isSuccess())
                .as("Check returned status is successful")
                .isTrue();
        assertThat(response.body().getDetections())
                .as("Check that response contains entry of correct language: %s. And correctness satisfies threshold", expectedLanguage)
                .hasOnlyOneElementSatisfying(detection -> {
                    assertThat(detection.getLanguageCode()).isEqualTo(expectedLanguage);
                    assertThat(detection.getPercentage()).isGreaterThanOrEqualTo(Properties.SUCCESS_THRESHOLD);
                });
    }

    @Test(dataProvider = "negativeTestData", groups = {"regression", "negative"})
    @Description("Test for multiple languages in one sentence")
    @TmsLink("TC-2")
    public void multipleLanguageDetectionTest(String sentence) {
        Response<DetectGetResponse> response = detectEndpointSteps.getDetections(sentence);
        assertThat(response.code())
                .as("Check status code is %s", HttpStatus.OK)
                .isEqualTo(HttpStatus.OK);
        assertThat(response.body().isSuccess())
                .as("Check returned status is successful")
                .isTrue();
        assertThat(response.body().getDetections())
                .as("Detection percentage for multiple languages must be under the threshold for all entries")
                .allSatisfy(detection -> assertThat(detection.getPercentage()).isLessThan(Properties.SUCCESS_THRESHOLD));
    }

    @Test(dataProvider = "brokenTestData", groups = {"negative"})
    @Description("Test for multiple languages in one sentence")
    @TmsLink("TC-3")
    @Issue("BUG-123")
    public void brokenLanguageDetectionTest(String sentence) {
        Response<DetectGetResponse> response = detectEndpointSteps.getDetections(sentence);
        assertThat(response.code())
                .as("Check status code is %s", HttpStatus.OK)
                .isEqualTo(HttpStatus.OK);
        assertThat(response.body().isSuccess())
                .as("Check returned status is NOT successful")
                .isFalse();
    }
}
