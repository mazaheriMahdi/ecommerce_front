package com.example.store_front.Service.User;

import com.example.store_front.Exception.LoginFailedException;
import com.example.store_front.Models.RequestModel.ProfilePatchRequestModel;
import com.example.store_front.Models.User;
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

import static com.example.store_front.Constant.*;

public class UserService {

    private static Boolean isLoggedIn;
    private static User currentUser;

    public static Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(Boolean isLoggedIn) {
        UserService.isLoggedIn = isLoggedIn;
    }

    private static List<UserLogInEvent> listener;
    private static List<ProfileUpdateEvent> profileUpdateEvents;
    private static List<StaffLoginEvent> staffLoginEvents;
    private static List<UserLogoutEvent> userLogoutEvents;
    private static List<OnLoginNeededSend> onLoginNeededSends;
    private static String authToken;


    static {
        onLoginNeededSends = new ArrayList<>();
        userLogoutEvents = new ArrayList<>();
        staffLoginEvents = new ArrayList<>();
        listener = new ArrayList<>();
        profileUpdateEvents = new ArrayList<>();
        isLoggedIn = false;
    }

    public static void addOnUserLoginListener(UserLogInEvent event) {
        listener.add(event);
    }

    public static void addOnProfileUpdateListener(ProfileUpdateEvent event) {
        profileUpdateEvents.add(event);
    }

    public static void addOnStaffLoginListener(StaffLoginEvent event) {
        staffLoginEvents.add(event);
    }
    public static void addOnUserLogoutListener(UserLogoutEvent event) {
        userLogoutEvents.add(event);
    }
    public static void addOnLoginNeededSend(OnLoginNeededSend event) {
        onLoginNeededSends.add(event);
    }

    private static void runOnLoginNeededSend() {
        for (OnLoginNeededSend event : onLoginNeededSends) {
            event.onLoginNeeded();
        }
    }
    private static void runEvents() {
        for (UserLogInEvent event : listener) {
            event.onUserLogIn();
        }
    }

    private static void runStaffLoginEvents() {
        for (StaffLoginEvent event : staffLoginEvents) {
            event.onStaffUserLogIn();
        }
    }


    public static String getAuthToken() {
        if (authToken == null) {
            if (checkDataFileExistence()) {
                readFromFile();

            } else {
                runOnLoginNeededSend();
            }
        }
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        UserService.authToken = authToken;
        saveToFile(authToken);
        setIsLoggedIn(true);
        setCurrentUser();
        try {
            if (checkIsStaff()) {
                System.out.println("Staff");
                runStaffLoginEvents();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        runEvents();
    }

    public static void logout() {
        File file = new File("data.txt");
        file.delete();
        setIsLoggedIn(false);
        authToken = null;
        for (UserLogoutEvent event : userLogoutEvents) {
            event.onUserLogOut();
        }
    }


    public static boolean checkDataFileExistence() {
        File file = new File("data.txt");
        return file.exists();
    }

    public static boolean checkIsStaff() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(AUTH_API_END_POINT + "/isStaff"))
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, Boolean> data = new Gson().fromJson(response.body(), new TypeToken<Map<String, Boolean>>() {
        }.getType());
        return data.get("isStaff");
    }

    @SneakyThrows
    public static void saveToFile(String token) {
        FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(token);
        fileOutputStream.close();
    }

    @SneakyThrows
    public static void readFromFile() {
        FileInputStream fileOutputStream = new FileInputStream("data.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream);
        setAuthToken((String) objectInputStream.readObject());
        setIsLoggedIn(true);
        fileOutputStream.close();
    }


    public static void login(String username, String password) throws IOException, InterruptedException {
        Map<String, String> requestData = Map.of("email", username, "password", password);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(requestData)))
                .uri(URI.create(LOGIN_API_END_POINT))
                .header("Content-Type", "application/json")
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Map<String, String> data = new Gson().fromJson(httpResponse.body(), new TypeToken<Map<String, String>>() {
        }.getType());
        if (httpResponse.statusCode() != 200) {
            throw new LoginFailedException();
        } else setAuthToken(data.get("token"));

    }

    public static void setCurrentUser() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/profile"))
                .header("Content-Type", "application/json")
                .header("Authorization", getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        currentUser = new Gson().fromJson(httpResponse.body(), User.class);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void updateUser(String name, String email) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(new ProfilePatchRequestModel(name, email), ProfilePatchRequestModel.class)))
                .uri(URI.create(PROFILE_API_END_POINT))
                .header("Content-Type", "application/json")
                .header("Authorization", getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        if (httpResponse.statusCode() == 200) {
            for (ProfileUpdateEvent event : profileUpdateEvents) {
                event.onProfileUpdated();
            }
        }
    }
}
