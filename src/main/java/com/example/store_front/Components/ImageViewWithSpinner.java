package com.example.store_front.Components;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ImageViewWithSpinner extends VBox {
    public ImageViewWithSpinner(String url, double width, double height) {
        super();
        this.getStyleClass().add("roundedBG");
        this.setMaxHeight(height);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setMinWidth(width);

        ProgressBar progressBar = new ProgressBar();

        Image image = new Image(url, true);


            ImageView imageView = new ImageView();
            imageView.getStyleClass().add("roundedBG");
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(progressBar);


            image.progressProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.doubleValue() == 1.0) {
                            try {

                                if (image != null){
                                    ImagePattern imagePattern = new ImagePattern(image, 0, 0, 1, 1, true);
                                    Rectangle rectangle = new Rectangle(width, height);
                                    rectangle.setFill(imagePattern);
                                    rectangle.getStyleClass().add("roundedBG");
                                    rectangle.arcHeightProperty().setValue(20);
                                    rectangle.arcWidthProperty().setValue(20);
                                    this.getChildren().clear();
                                    this.getChildren().add(rectangle);
                                }
                            }catch (Exception e){
                                this.getChildren().clear();
                                Text text = new Text("Broken Image");
                                Text icon = GlyphsDude.createIcon(FontAwesomeIcon.EXCLAMATION_TRIANGLE, "1.5em");
                                this.getChildren().add(icon);
                            }
                        }
                    }
            );




    }

}
