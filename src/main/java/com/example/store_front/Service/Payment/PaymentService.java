package com.example.store_front.Service.Payment;


import com.example.store_front.Models.RequestModel.PaymentRequestModel;
import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.store_front.Constant.PAYMENT_API_END_POINT;

public class PaymentService {
    private static List<OnPaymentAddedEvent> onPaymentAddedEventList;
    static {
        onPaymentAddedEventList = new ArrayList<>();
    }
    public static void addOnPaymentAddedEvent(OnPaymentAddedEvent onPaymentAddedEvent){
        onPaymentAddedEventList.add(onPaymentAddedEvent);
    }
    public static void runEvents(){
        for (OnPaymentAddedEvent onPaymentAddedEvent : onPaymentAddedEventList) {
            onPaymentAddedEvent.onPaymentAdded();
        }
    }
    public static void createPayment(PaymentRequestModel paymentRequestModel) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(paymentRequestModel , PaymentRequestModel.class)))
                .uri(URI.create(PAYMENT_API_END_POINT))
                .header("Content-Type" , "application/json")
                .header("Authorization" ,  UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
        if (httpResponse.statusCode() == 200 )runEvents();
    }

}
