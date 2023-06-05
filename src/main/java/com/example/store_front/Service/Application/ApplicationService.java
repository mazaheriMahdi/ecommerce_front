package com.example.store_front.Service.Application;

import com.example.store_front.Models.Application;
import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.store_front.Constant.APPLICATION_API_END_POINT;

public class ApplicationService {

    private static List<ApplicationRejectedEvent> applicationRejectedEventList;
    private static List<ApplicationAcceptedEvent> applicationAcceptedEventList;

    static {
        applicationAcceptedEventList = new ArrayList<>();
        applicationRejectedEventList = new ArrayList<>();
    }
    public static void addApplicationRejectedEvent(ApplicationRejectedEvent applicationRejectedEvent){
        applicationRejectedEventList.add(applicationRejectedEvent);
    }

    public static void addApplicationAcceptedEvent(ApplicationAcceptedEvent applicationAcceptedEvent){
        applicationAcceptedEventList.add(applicationAcceptedEvent);
    }
    public static List<Application> getAll() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(APPLICATION_API_END_POINT))
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response =httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
        List<Application> data = new Gson().fromJson(response.body() , new TypeToken<List<Application>>(){}.getType());
        return data;
    }

    public static void acceptApplication(Application application) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .uri(URI.create(APPLICATION_API_END_POINT + "/" + application.id() + "/accept"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request , HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        if (response.statusCode() == 200){
            applicationAcceptedEventList.forEach(applicationAcceptedEvent -> applicationAcceptedEvent.onApplicationAccepted());
        }
    }

    public static void rejectApplication(Application application) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .uri(URI.create(APPLICATION_API_END_POINT + "/" + application.id() + "/reject"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request , HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        if (response.statusCode() == 200){
            applicationRejectedEventList.forEach(applicationRejectedEvent -> applicationRejectedEvent.onApplicationRejected());
        }
    }
}
