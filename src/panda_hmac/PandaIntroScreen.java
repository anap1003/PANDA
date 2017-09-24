/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author KoRiSnIk
 */
public class PandaIntroScreen {
    
    public VBox getScreen(Stage primaryStage) {
        Image image = new Image(new File("resources/images/panda.jpg").toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(0.45 * Math.min(primaryStage.getHeight(), primaryStage.getWidth()));
        imageView.setFitWidth(0.45 * Math.min(primaryStage.getHeight(), primaryStage.getWidth()));
        
        Text text = new Text("PANDA™ - Vizuelizacija HMAC algoritma sa SHA-3 heš funkcijom");
        text.setStyle("-fx-font: 24 arial;");
        
        Button btn = new Button();
        btn.setText("Početak");
        btn.setOnAction((ActionEvent event) -> {
            SceneChanger.changeScene((new PandaHashOptionScreen()).getScreen());
        });
        btn.setPrefWidth(imageView.getFitWidth());
        btn.setStyle("-fx-font: 16 arial;");
        
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(imageView);
        root.getChildren().add(text);
        root.getChildren().add(btn);
        
        primaryStage.widthProperty().addListener(e -> {
            imageView.setFitWidth(0.45 * Math.min(primaryStage.getHeight(), primaryStage.getWidth()));
            imageView.setFitHeight(0.45 * Math.min(primaryStage.getHeight(), primaryStage.getWidth()));
            btn.setPrefWidth(imageView.getFitWidth());
        });
        
        primaryStage.heightProperty().addListener(e -> {
            imageView.setFitWidth(0.45 * Math.min(primaryStage.getHeight(), primaryStage.getWidth()));
            imageView.setFitHeight(0.45 * Math.min(primaryStage.getHeight(), primaryStage.getWidth()));
            btn.setPrefWidth(imageView.getFitWidth());
            root.setSpacing(0.1 * primaryStage.getScene().getHeight());
        });
        
        return root;
    }
}
