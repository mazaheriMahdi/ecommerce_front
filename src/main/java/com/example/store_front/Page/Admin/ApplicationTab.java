package com.example.store_front.Page.Admin;

import com.example.store_front.Components.ApplicationCard;
import com.example.store_front.Models.Application;
import com.example.store_front.Service.Application.ApplicationAcceptedEvent;
import com.example.store_front.Service.Application.ApplicationRejectedEvent;
import com.example.store_front.Service.Application.ApplicationService;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ApplicationTab extends BorderPane {
    VBox root;

    public void init() {
        root.getChildren().clear();
        try {
            for (Application application : ApplicationService.getAll()) {
                root.getChildren().add(new ApplicationCard(application));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ApplicationTab() {
        super();
        root = new VBox();

        ApplicationService.addApplicationAcceptedEvent(this::init);
        ApplicationService.addApplicationRejectedEvent(this::init);
        init();

        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getStyleClass().add("darkPrimaryBack");
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        this.setCenter(scrollPane);
    }
}
