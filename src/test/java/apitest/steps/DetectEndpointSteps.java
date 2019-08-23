package apitest.steps;

import java.io.IOException;

import apitest.framework.utils.Properties;
import apitest.framework.utils.RetrofitUtils;
import apitest.application.webservices.DetectEndpoint;
import apitest.application.webservices.responses.DetectGetResponse;
import io.qameta.allure.Step;
import retrofit2.Response;

public class DetectEndpointSteps {
    private DetectEndpoint detectEndpoint = RetrofitUtils.createApiEndPoint(DetectEndpoint.class);

    @Step("Sending GET request to the /detect endpoint")
    public Response<DetectGetResponse> getDetections(String text) {
        try {
            return detectEndpoint.get(Properties.ACCESS_KEY, text, null, null, null).execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
