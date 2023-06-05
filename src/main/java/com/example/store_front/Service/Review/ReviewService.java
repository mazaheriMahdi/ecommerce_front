package com.example.store_front.Service.Review;

import com.example.store_front.Models.Product;
import com.example.store_front.Models.Review;
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
import java.util.Map;

import static com.example.store_front.Constant.PRODUCT_API_END_POINT;

public class ReviewService {

    private  static List<ReviewSendEvent> listeners ;
    static {
        listeners = new ArrayList<>();
    }


    private static void runEvents(String s){
        for (ReviewSendEvent listener : listeners) {
            listener.onReviewSend();
        }
    }

    public static void addListener(ReviewSendEvent listener){
        listeners.add(listener);
    }

    public static void  sendReview(String content , Product product){
        content = content.replaceAll(" " , "%20");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(PRODUCT_API_END_POINT + "/" + product.getId() + "/review" + "?" + "content=" + content))
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .GET()
                .build();

        HttpResponse response;
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
        runEvents(content);
    }


    public static List<Review> parsReview(String json) {
        List<Review> reviews = new ArrayList<>();
        if (json.equals("[]"))
            return reviews;
        List<Map<String, Object>> data = new Gson().fromJson(json, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
        for (Map<String, Object> reviewMap : data) {
            Review review = new Review();
            review.setContent(reviewMap.get("content").toString());
            review.setUserName(reviewMap.get("userName").toString());
            reviews.add(review);

        }
        return reviews;


    }

    public static List<Review> getReviews(Long productId) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(PRODUCT_API_END_POINT + "/" + productId + "/review"))
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return parsReview(response.body());
    }
}
