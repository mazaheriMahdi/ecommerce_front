package com.example.store_front.Components;

import com.example.store_front.Models.Product;
import com.example.store_front.Service.ProductService;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class ProductContainer extends FlowPane {
    private final ProductService productService = new ProductService();
    private final ProgressBar progressBar = new ProgressBar();

    public ProductContainer() {
        super();
        this.getChildren().add(progressBar);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("darkPrimaryBack");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initial();
            }


        });
    }

    public void clear() {
        this.getChildren().clear();
    }

    public void initial() {
        try {
            List<Product> products = productService.getProducts();
            List<ProductCard> productCards = new ArrayList<>();
            for (Product product : products) {
                ProductCard productCard = new ProductCard(product);
                productCards.add(productCard);
                ProductContainer.setMargin(productCard, new Insets(5));
            }
            this.getChildren().remove(progressBar);
            this.getChildren().addAll(productCards);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
