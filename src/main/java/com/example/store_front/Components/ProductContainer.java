package com.example.store_front.Components;

import com.example.store_front.Models.Product;
import com.example.store_front.Models.ResponseModel.PagesResponseModel;
import com.example.store_front.Service.Product.ProductService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;

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
    }

    public void clear() {
        this.getChildren().clear();
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
        List<ProductCard> productCards = new ArrayList<>();
        for (Product product : products) {
            ProductCard productCard = new ProductCard(product);
            productCards.add(productCard);
            ProductContainer.setMargin(productCard, new Insets(5));
        }
        this.getChildren().remove(progressBar);
        this.getChildren().addAll(productCards);


    }
}
