package com.example.store_front.Service.User;

import com.example.store_front.Exception.LoginFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.store_front.Constant.LOGIN_API_END_POINT;

public class UserService {

    private static Boolean isLoggedIn;

    static {
        isLoggedIn = false;
    }

    public static Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(Boolean isLoggedIn) {
        UserService.isLoggedIn = isLoggedIn;
    }

    private static List<UserLogInEvent> listener;

    private static void runEvents() {
        for (UserLogInEvent event : listener) {
            event.onUserLogIn();
        }
    }

    private static String authToken;

    static {
        listener = new ArrayList<>();
    }

    public static String getAuthToken() {
        if (authToken == null) {
            throw new LoginFailedException();
        }
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        UserService.authToken = authToken;
        saveToFile(authToken);
        setIsLoggedIn(true);
        runEvents();
    }

    @SneakyThrows
    public static void saveToFile(String token) {
        FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(token);
    }

    @SneakyThrows
    public static void readFromFile() {
        FileInputStream fileOutputStream = new FileInputStream("data.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream);
        setAuthToken((String) objectInputStream.readObject());
    }


    public static void login(String username, String password) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{ \"email\":" + username + ", \"password\":" + password + "}"))
                .uri(URI.create(LOGIN_API_END_POINT))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, String> data = new Gson().fromJson(httpResponse.body(), new TypeToken<Map<String, String>>() {
        }.getType());
        if (httpResponse.statusCode() != 200) {
            throw new LoginFailedException();
        } else setAuthToken(data.get("token"));
    }
}
