package com.example.store_front.Service.Product;

import com.example.store_front.Models.Category;
import com.example.store_front.Models.Product;
import com.example.store_front.Models.ResponseModel.PagesResponseModel;
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

public class ProductService {

    static List<PageChangeEvent> listener;

    static {
        listener = new ArrayList<>();
    }

    public static void runEvents() {
        for (PageChangeEvent event : listener) {
            event.onPageChange();
        }
    }

    static public List<Product> parsProducts(String response) {
        Map<String, Object> data = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {
        }.getType());
        Map<String, Map<String, Object>> links = new Gson().fromJson(new Gson().toJson(data.get("_links")), new TypeToken<Map<String, Map<String, Object>>>() {
        }.getType());


        PagesResponseModel.First.href = links.get("first").get("href").toString();
        PagesResponseModel.Last.href = links.get("last").get("href").toString();
        try {
            PagesResponseModel.Next.href = links.get("next").get("href").toString();
            PagesResponseModel.Prev.href = links.get("prev").get("href").toString();
        } catch (Exception ignored) {

        }

        Map<String, Object> fullProducts = new Gson().fromJson(new Gson().toJson(data.get("_embedded")), new TypeToken<Map<String, Object>>() {
        }.getType());

        List<Map<String, Object>> productsMap = new Gson().fromJson(new Gson().toJson(fullProducts.get("fullProducts")), new TypeToken<List<Map<String, Object>>>() {
        }.getType());
        List<Product> products = new ArrayList<>();

        for (Map<String, Object> productMap : productsMap) {
            Product product = new Product();
            Double doubleId = Double.parseDouble(productMap.get("id").toString());
            Long id = doubleId.longValue();
            product.setId(id);
            product.setName(productMap.get("name").toString());
            product.setCount(Double.parseDouble(productMap.get("count").toString()));
            product.setPrice(Double.parseDouble(productMap.get("price").toString()));
            product.setAveragePoint(Double.parseDouble(productMap.get("averagePoint").toString()));
            product.setImage(productMap.get("image").toString());
            product.setCategory(new Category(((productMap.get("category")).toString())));
            List<Map<String, String>> productProperties = new Gson().fromJson(new Gson().toJson(productMap.get("productProperties")), new TypeToken<List<Map<String, Object>>>() {
            }.getType());
            product.setProductProperties(productProperties);
            products.add(product);
        }

        return products;

    }

    static public List<Product> next() throws IOException, InterruptedException {
        return getProducts(PagesResponseModel.Next.href);
    }

    static public List<Product> prev() throws IOException, InterruptedException {
        return getProducts(PagesResponseModel.Prev.href);
    }

    static public List<Product> first() throws IOException, InterruptedException {
        return getProducts(PagesResponseModel.First.href);

    }

    static public List<Product> last() throws IOException, InterruptedException {
        return getProducts(PagesResponseModel.Last.href);
    }


    static public List<Product> getProducts(String pageURL) throws IOException, InterruptedException {
        if (pageURL == null) pageURL = PRODUCT_API_END_POINT;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(pageURL))
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (!pageURL.equals(PRODUCT_API_END_POINT)) {
            runEvents();
        }
        return parsProducts(response.body());
    }

    static public List<Product> getProducts() throws IOException, InterruptedException {
        return getProducts(PRODUCT_API_END_POINT);
    }

}
