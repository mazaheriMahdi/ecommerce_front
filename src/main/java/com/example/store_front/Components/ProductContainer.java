package com.example.store_front.Components;

import com.example.store_front.Models.Product;
import com.example.store_front.Models.ResponseModel.PagesResponseModel;
import com.example.store_front.Service.Product.ProductService;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.io.IOException;
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
        this.firstPage();
        handleSearch();
    }

    public void clear() {
        this.getChildren().clear();
    }


    public void handleSearch(){
        NavBar.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                System.out.println(newValue);
                initial(ProductService.search(newValue));
            } catch (IOException | InterruptedException e) {

            }
        });
    }


    public void nextPage() {
        try {
            initial(ProductService.next());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void prevPage() {
        try {
            initial(ProductService.prev());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void firstPage() {
        try {
            initial(ProductService.first());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void lastPage() {
        try {
            initial(ProductService.last());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void initial(List<Product> products) {
        this.getChildren().clear();
        this.getChildren().add(progressBar);
        double from = 1;
        double to = 1.1;

        List<ProductCard> productCards = new ArrayList<>();
        for (Product product : products) {

            ScaleTransition scaleEnter = new ScaleTransition();
            scaleEnter.setFromX(from);
            scaleEnter.setFromY(from);
            scaleEnter.setToX(to);
            scaleEnter.setToY(to);
            scaleEnter.setDuration(Duration.millis(200));
            scaleEnter.setAutoReverse(false);


            ScaleTransition scaleBack =  new ScaleTransition();
            scaleBack.setToX(1);
            scaleBack.setToY(1);
            scaleBack.setFromX(to);
            scaleBack.setFromY(from);
            scaleBack.setDuration(Duration.millis(200));
            ProductCard productCard = new ProductCard(product);
            productCard.setScaleX(1);
            productCard.setScaleY(1);


            scaleEnter.setNode(productCard);
            scaleBack.setNode(productCard);
            productCard.setOnMouseEntered(event -> {
                    scaleEnter.playFromStart();
                    fadeOut(productCard);
            });
            productCard.setOnMouseExited(event -> {
                    scaleBack.playFromStart();
                    fadeIn(productCard);
            });






            productCards.add(productCard);
            ProductContainer.setMargin(productCard, new Insets(5));
        }


        this.getChildren().remove(progressBar);
        this.getChildren().addAll(productCards);

    }
    private void fadeOut(Node node){
        Transition fade = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }
            @Override
            protected void interpolate(double frac) {
                for (Node child : getChildren()){
                    if (child != node){
                        child.setOpacity(1-0.5);
                    }
                }
            }
        };
        fade.play();
    }

    private void fadeIn(Node node){
        Transition fade = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }
            @Override
            protected void interpolate(double frac) {
                for (Node child : getChildren()){
                    if (child != node){
                        child.setOpacity(1);
                    }
                }
            }
        };
        fade.play();
    }


}
