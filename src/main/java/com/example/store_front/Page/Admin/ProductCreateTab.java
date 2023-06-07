package com.example.store_front.Page.Admin;

import com.example.store_front.Components.ComboBoxWithLabel;
import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Components.KeyValueTextFiled;
import com.example.store_front.Components.TextFieldWithLabel;
import com.example.store_front.Models.Category;
import com.example.store_front.Models.RequestModel.CreateProductRequestModel;
import com.example.store_front.Models.RequestModel.ProductRequestModel;
import com.example.store_front.Service.Category.CategoryService;
import com.example.store_front.Service.Product.ProductService;
import com.example.store_front.Service.Product.PropertyKeyService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCreateTab extends BorderPane {
    private List<KeyValueTextFiled> keyValueTextFiled;
    VBox center = new VBox();

    Boolean isFirstTime = true;

    public List<KeyValueTextFiled> getKeyValueTextFiled() {
        return keyValueTextFiled;
    }

    public void setKeyValueTextFiled(List<KeyValueTextFiled> keyValueTextFiled) {

        this.keyValueTextFiled = keyValueTextFiled;
    }

    public void addKeyValueTextFiled() {
        try {
            List<Map<String, String>> list = PropertyKeyService.getAll();
            List<String> nameList = new ArrayList<>();
            for (Map<String, String> map : list) {
                nameList.add(map.get("name"));
            }
            KeyValueTextFiled keyValueTextFiled = new KeyValueTextFiled(nameList);
            this.keyValueTextFiled.add(keyValueTextFiled);
            center.getChildren().add(center.getChildren().size() - 2, keyValueTextFiled);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductCreateTab() {
        super();
        keyValueTextFiled = new ArrayList<>();

        init();


    }

    public void init() {
        if (!isFirstTime) {
            center.getChildren().remove(5, center.getChildren().size());
        }

        TextFieldWithLabel name = new TextFieldWithLabel("Name");
        TextFieldWithLabel price = new TextFieldWithLabel("Price");
        price.getTextField().textProperty().addListener((w, e, r) -> System.out.println(price.getTextField().getText()));
        TextFieldWithLabel count = new TextFieldWithLabel("Count");
        TextFieldWithLabel image = new TextFieldWithLabel("Image URL");

        List<String> nameList = new ArrayList<>();
        try {
            CategoryService.getAll().forEach(category -> nameList.add(category.getName()));
        } catch (IOException | InterruptedException e) {
            CustomAlert customAlert = new CustomAlert("Error");
            customAlert.show();
        }
        ComboBoxWithLabel<String> category = new ComboBoxWithLabel<>("Category", nameList.toArray(String[]::new));
        Button button = new Button("add");
        button.getStyleClass().add("loginPageBtn");
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.PLUS, "10px");
        icon.setFill(Color.WHITE);
        button.setGraphic(icon);


        center.setFillWidth(true);
        center.setSpacing(20);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(100, 0, 100, 0));

        Button addKeyValueTextFiled = new Button();
        addKeyValueTextFiled.getStyleClass().add("darkAccent");
        Text icon2 = GlyphsDude.createIcon(FontAwesomeIcon.PLUS, "10px");
        icon2.setFill(Color.WHITE);
        addKeyValueTextFiled.setGraphic(icon2);
        addKeyValueTextFiled.setOnAction(e -> {
            addKeyValueTextFiled();
        });

        this.getKeyValueTextFiled().clear();
        if (isFirstTime) center.getChildren().addAll(name, price, count, image, category);
        center.getChildren().addAll(keyValueTextFiled);
        center.getChildren().addAll(btnBox(), addKeyValueTextFiled, button);

        HBox centerHbox = new HBox(center);
        centerHbox.getStyleClass().add("darkPrimaryBack");

        ScrollPane scrollPane = new ScrollPane(centerHbox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        centerHbox.setAlignment(Pos.CENTER);
        this.setCenter(scrollPane);


        button.setOnMouseClicked(mouseEvent -> {
            int countValue = 0;
            double priceValue = 0;
            ProductRequestModel product = new ProductRequestModel(
                    name.getTextField().getText(),
                    countValue,
                    priceValue,
                    0,
                    new Category(Long.valueOf(category.getComboBox().getSelectionModel().getSelectedIndex()) + 1, category.getComboBox().getSelectionModel().getSelectedItem()),
                    image.getTextField().getText()
            );


            List<Map<String, String>> productProperties = new ArrayList<>() {
            };
            for (KeyValueTextFiled keyValueTextFiled1 : keyValueTextFiled) {
                productProperties.add(keyValueTextFiled1.getMap());
            }
            CreateProductRequestModel createProductRequestModel = new CreateProductRequestModel(
                    product,
                    productProperties
            );
            try {
                ProductService.createProduct(createProductRequestModel);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        isFirstTime = false;

    }

    public static Map<String, List<String>> getPropertyMap() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> carPreset = new ArrayList<>();
        carPreset.add("Engine Capacity");
        carPreset.add("Is Automatic");
        carPreset.add("Company");
        map.put("Car preset", carPreset);

        List<String> stationeryPreset = new ArrayList<>();
        stationeryPreset.add("Made in");
        map.put("Stationery preset", stationeryPreset);

        List<String> phonePreset = new ArrayList<>();
        phonePreset.add("Screen Size");
        phonePreset.add("Camera Pixel");
        phonePreset.add("Battery Capacity");
        phonePreset.add("Company");
        map.put("Phone preset", phonePreset);

        List<String> ssdPreset = new ArrayList<>();
        ssdPreset.add("Capacity");
        ssdPreset.add("Company");
        map.put("SSD preset", ssdPreset);

        List<String> laptopPreset = new ArrayList<>();
        laptopPreset.add("Screen Size");
        laptopPreset.add("Ram");
        laptopPreset.add("CPU");
        laptopPreset.add("Company");
        map.put("Laptop preset", laptopPreset);

        List<String> tvPreset = new ArrayList<>();
        tvPreset.add("Screen Size");
        tvPreset.add("Company");
        map.put("TV preset", tvPreset);


        return map;


    }

    public FlowPane btnBox() {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER_LEFT);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.getStyleClass().add("darkPrimaryBack");
        flowPane.setMaxWidth(400);
        flowPane.setMinWidth(400);

        getPropertyMap().forEach((key, value) -> {
            Button button = new Button(key);
            button.getStyleClass().add("darkAccent");
            button.setOnMouseClicked(mouseEvent -> {

                this.init();
                this.setKeyValueTextFiled(new ArrayList<>());
                for (String s : value) {
                    this.addKeyValueTextFiled();
                    this.getKeyValueTextFiled().get(this.getKeyValueTextFiled().size() - 1).addKey(s);
                    this.getKeyValueTextFiled().get(this.getKeyValueTextFiled().size() - 1).selectLast();
                }

            });
            flowPane.getChildren().add(button);
        });

        return flowPane;

    }
}
