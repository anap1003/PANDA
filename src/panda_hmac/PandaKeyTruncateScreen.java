/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import control.State;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author KoRiSnIk
 */
public class PandaKeyTruncateScreen {
    
    public BorderPane getScreen() {
        String key = State.getInstance().getKey();
        String newKey = State.getInstance().getSelectedHash().getHashValue(key);
        State.getInstance().setTruncKey(newKey);
        
        int cells = State.getInstance().getSelectedHash().getDigestCells();
        int columns = State.getInstance().getSelectedHash().getDigestColumns();
        int length = columns - 1;
        
        Label paddedMessageLabel = new Label("Skraćivanje ključa na n bita izlaza heš funkcije:");
        paddedMessageLabel.setStyle("-fx-font: 30 arial;");
        
        Button simBtn = new Button("Dopunjavanje");
        simBtn.setOnAction((ActionEvent event) -> {
            SceneChanger.changeScene((new PandaKeyPaddingScreen()).getScreen());
        });
        simBtn.setPrefWidth(0.12 * PANDA_HMAC.WIDTH);
        simBtn.setStyle("-fx-font: 16 arial;");
        
        Button backBtn = new Button("Nazad");
        backBtn.setOnAction((ActionEvent event) -> {
            SceneChanger.changeScene((new PandaHashOptionScreen()).getScreen());
        });
        backBtn.setPrefWidth(0.12 * PANDA_HMAC.WIDTH);
        backBtn.setStyle("-fx-font: 16 arial;");
        
        GridPane grid = new GridPane();
        grid.setVgap(40);
        grid.setHgap(30);
        grid.setPadding(new Insets(0, 0, 10, 0));
        grid.setAlignment(Pos.CENTER);
        grid.add(backBtn, 0, 0);
        grid.add(simBtn, 1, 0);
        
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        
        vBox.getChildren().add(paddedMessageLabel);
        
        VBox vBox2 = new VBox();
        vBox2.setSpacing(30);
        
        GridPane array = new GridPane();
        array.setHgap(5);
        
        LabelMatrix labelMatrix = new LabelMatrix(key, cells, columns);
        GridPane beforeHMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            beforeHMatrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        beforeHMatrix.setAlignment(Pos.CENTER);
        beforeHMatrix.setVgap(5);
        beforeHMatrix.setHgap(5);
        vBox2.getChildren().add(beforeHMatrix);
        
        Button hashBtn = new Button("H");
        hashBtn.setPrefWidth(0.1 * PANDA_HMAC.WIDTH);
        hashBtn.setPrefHeight(0.1 * PANDA_HMAC.HEIGHT);
        hashBtn.setOnMouseClicked(e -> {
            // TODO veza sa Braninim
            //showFunctionScreen();
        });
        
        vBox2.getChildren().add(hashBtn);
        
        labelMatrix = new LabelMatrix(newKey, cells, columns);
        GridPane afterHMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            afterHMatrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        afterHMatrix.setAlignment(Pos.CENTER);
        afterHMatrix.setVgap(5);
        afterHMatrix.setHgap(5);
        
        State.getInstance().setkPlus(labelMatrix.getkPlus());
        
        vBox2.getChildren().add(afterHMatrix);
        
        vBox2.setAlignment(Pos.CENTER);
        
        vBox.getChildren().add(vBox2);
        //vBox.getChildren().add(grid);
        
        BorderPane borderPane = new BorderPane(vBox, null, null, grid, null);
        
        //return vBox;
        return borderPane;
    }
}
