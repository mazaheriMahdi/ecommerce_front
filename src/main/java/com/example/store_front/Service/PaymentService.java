package com.example.store_front.Service;


import com.example.store_front.Models.RequestModel.PaymentRequestModel;
import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.example.store_front.Constant.PAYMENT_API_END_POINT;

public class PaymentService {

    public static void createPayment(PaymentRequestModel paymentRequestModel) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(paymentRequestModel , PaymentRequestModel.class)))
                .uri(URI.create(PAYMENT_API_END_POINT))
                .header("Content-Type" , "application/json")
                .header("Authorization" ,  UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }

}
