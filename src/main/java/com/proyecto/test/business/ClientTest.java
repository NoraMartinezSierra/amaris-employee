package com.proyecto.test.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.test.dto.EmployeeReceived;
import com.proyecto.test.dto.ResponseReceived;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class ClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientTest.class);

    private static final String CONTENT_TYPE = "Content-Type";
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final String TIMEOUT = "timeout";
    private static final String STR_TYPE_GET = "get";
    private static final String STR_ERR_RESP = "Failed response -> message: {}, code: {}";
    private static final String STR_ERR_RESP_IO = "IOException in response -> error: {}, IOException: {}";
    private static final String STR_ERR_RESP_EMPTY = "Response is empty -> error: {}";
    private static final String STR_ERR_RESP_VALIDATION = "Failed response validation -> error: {}";
    private static final String MESSAGE = "error response message : {} , code {}";

    public ResponseReceived getEmployees() {

        String url = "http://dummy.restapiexample.com/api/v1/employees";
        ResponseReceived responseReceived = new ResponseReceived();
        try {
            responseReceived = executeRequest(url);
        } catch (Exception e) {
            LOGGER.error(STR_ERR_RESP_VALIDATION, e.getMessage());
        }
        return responseReceived;
    }

    public ResponseReceived getEmployeesById(Integer id) {

        String url = String.format("http://dummy.restapiexample.com/api/v1/employee/%s",id);
        ResponseReceived responseReceived = new ResponseReceived();
        try {
            responseReceived = executeRequest(url);
        } catch (Exception e) {
            LOGGER.error(STR_ERR_RESP_VALIDATION, e.getMessage());
        }
        return responseReceived;
    }

    private ResponseReceived executeRequest(String url) {
        OkHttpClient client;
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        client = builder.build();
        // Defines response's after-execution variables.
        String responseAnswer = "";
        String responseMessage = "";
        int responseCode = 0;
        // Creates request with params.
        Request requestGet = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .method("GET", null)
                .build();
        // Executes request, validates response's variables and returns answer.
        try (Response response = client.newCall(requestGet).execute()) {
            if (response.isSuccessful()) {
                responseAnswer = Objects.requireNonNull(response.body()).string();
                return JSON_MAPPER.readValue(responseAnswer, ResponseReceived.class);
            }else {
                LOGGER.error(MESSAGE, response);
                throw new BusinessException(response.message(), response.code());
            }
        } catch (NullPointerException e) {
            LOGGER.error(STR_ERR_RESP_EMPTY, e.getMessage());
            throw new BusinessException(responseMessage, responseCode);
            // If IOException is thrown.
        } catch (IOException e) {
            LOGGER.error(STR_ERR_RESP_IO, e.getMessage());
            if (e.getMessage().equals(TIMEOUT)) {
                throw new BusinessException(e.getMessage(), 504);
            }
            throw new BusinessException(responseMessage, responseCode);
            // If general Exception is thrown.
        } catch (Exception e) {
            LOGGER.error(STR_ERR_RESP, responseMessage, responseCode);
            throw new BusinessException(responseMessage, responseCode);
        }
    }
}
