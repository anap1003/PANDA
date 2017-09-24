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
public class PandaKeyPaddingScreen {
    
    private LabelMatrix labelMatrix;

    public BorderPane getScreen() {
        String key;
        String truncKey = State.getInstance().getTruncKey();
        if (truncKey != null) {
            key = truncKey;
        } else {
            key = State.getInstance().getKey();
        }
        
        Label paddedMessageLabel = new Label("Ključ dopunjen do B bajtova input-a hash funkcije:");
        paddedMessageLabel.setStyle("-fx-font: 30 arial;");
        
        Button simBtn = new Button("Simulacija");
        simBtn.setOnAction((ActionEvent event) -> {
            State.getInstance().calculateHMAC();
            SceneChanger.changeScene((new PandaHMACScreen()).getScreen());
        });
        simBtn.setPrefWidth(0.12 * PANDA_HMAC.WIDTH);
        simBtn.setStyle("-fx-font: 16 arial;");
        
        Button backBtn = new Button("Nazad");
        backBtn.setOnAction((ActionEvent event) -> {
            if (truncKey != null) {
                SceneChanger.changeScene((new PandaKeyTruncateScreen()).getScreen());
            } else {
                SceneChanger.changeScene((new PandaHashOptionScreen()).getScreen());
            }
        });
        backBtn.setPrefWidth(0.12 * PANDA_HMAC.WIDTH);
        backBtn.setStyle("-fx-font: 16 arial;");
        
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setPadding(new Insets(0, 0, 10, 0));
        grid.setAlignment(Pos.CENTER);
        grid.add(backBtn, 0, 0);
        grid.add(simBtn, 1, 0);
        
        VBox vBox = new VBox();
        vBox.setSpacing(40);
        vBox.setAlignment(Pos.CENTER);
        
        vBox.getChildren().add(paddedMessageLabel);
        
        int cells = State.getInstance().getSelectedHash().getBlockCells();
        int columns = State.getInstance().getSelectedHash().getBlockColumns();
        
        labelMatrix = new LabelMatrix(key, cells, columns);
        GridPane matrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            matrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        matrix.setAlignment(Pos.CENTER);
        matrix.setVgap(20);
        matrix.setHgap(20);
        
        vBox.getChildren().add(matrix);
        
        State.getInstance().setkPlus(labelMatrix.getkPlus());
        
        BorderPane borderPane = new BorderPane(vBox, null, null, grid, null);
        
        return borderPane;
    }
}
