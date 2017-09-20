/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import control.State;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;

/**
 *
 * @author KoRiSnIk
 */
public class PandaHashOptionScreen {

    private ComboBox comboBox;

    private Label optionLabel, messageLabel, keyLabel, errorLabel;

    private TextField msgField, keyField;

    public VBox getScreen() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "SHA3-224",
                "SHA3-256",
                "SHA3-384",
                "SHA3-512"
        );

        comboBox = new ComboBox(options);
        comboBox.setStyle("-fx-font: 16 arial;");
        comboBox.setValue(options.get(0));

        optionLabel = new Label("Algoritam:");
        optionLabel.setStyle("-fx-font: 20 arial;");

        messageLabel = new Label("Poruka:");
        messageLabel.setStyle("-fx-font: 20 arial;");

        msgField = new TextField();
        msgField.setStyle("-fx-font: 16 arial;");

        msgField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (msgField.getText().length() > PANDA_HMAC.MAXIMUM_MESSAGE_LENGTH) {
                String s = msgField.getText().substring(0, PANDA_HMAC.MAXIMUM_MESSAGE_LENGTH);
                msgField.setText(s);
            }
        });

        keyLabel = new Label("Ključ:");
        keyLabel.setStyle("-fx-font: 20 arial;");

        keyField = new TextField();
        keyField.setStyle("-fx-font: 16 arial;");

        comboBox.setPrefWidth(0.25 * PANDA_HMAC.WIDTH);
        msgField.setPrefWidth(0.25 * PANDA_HMAC.WIDTH);
        keyField.setPrefWidth(0.25 * PANDA_HMAC.WIDTH);

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-font: 20 arial;");
        errorLabel.setTextFill(Color.web("#ff0000"));

        Button btn = new Button();
        btn.setStyle("-fx-font: 16 arial;");
        btn.setText("Skraćivanje/Dopunjavanje");
        btn.setOnAction((ActionEvent event) -> {
            if (msgField.getText() == null) {
                msgField.setText("");
            }
            if (keyField.getText() == null) {
                keyField.setText("");
            }
            if (msgField.getText().matches("([0-9]|[a-f]|[A-F])*")) {
                if (keyField.getText().matches("([0-9]|[a-f]|[A-F])+")) {

                    if (msgField.getText().length() % 2 > 0) {
                        errorLabel.setText("Poruka mora sadržati parni broj karaktera!");
                    } else {

                        State.getInstance().setSelectedHash((String) comboBox.getValue());
                        State.getInstance().setMessage(msgField.getText().toUpperCase());
                        State.getInstance().setKey(keyField.getText().toUpperCase());
                        State.getInstance().setTruncKey(null);

                        if (keyField.getText().length() > State.getInstance().getSelectedHash().getBlockSizeInHex()) {
                            SceneChanger.changeScene((new PandaKeyTruncateScreen()).getScreen());
                        } else {
                            SceneChanger.changeScene((new PandaKeyPaddingScreen()).getScreen());
                        }
                    }
                } else {
                    errorLabel.setText("Ključ mora da bude u heksadecimalnom formatu!");
                }
            } else {
                errorLabel.setText("Poruka mora da bude u heksadecimalnom formatu!");
            }
        });
        btn.setPrefWidth(0.25 * PANDA_HMAC.WIDTH);

        GridPane grid = new GridPane();
        grid.setVgap(30);
        grid.setHgap(40);
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setAlignment(Pos.CENTER);
        grid.add(optionLabel, 0, 0);
        grid.add(comboBox, 1, 0);
        grid.add(messageLabel, 0, 1);
        grid.add(msgField, 1, 1);
        grid.add(keyLabel, 0, 2);
        grid.add(keyField, 1, 2);

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(grid);
        vBox.getChildren().add(btn);
        vBox.getChildren().add(errorLabel);

        return vBox;
    }
}
