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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author KoRiSnIk
 */
public class PandaHMACScreen {

    public BorderPane getScreen() {
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(createSiTab());
        tabPane.getTabs().add(createSoTab());
        tabPane.getTabs().add(createFirstHashInvTab());
        tabPane.getTabs().add(createSecondHashInvTab());

        Button simBtn = new Button("Nova simulacija");
        simBtn.setOnAction((ActionEvent event) -> {
            SceneChanger.changeScene((new PandaHashOptionScreen()).getScreen());
        });
        simBtn.setPrefWidth(0.2 * PANDA_HMAC.WIDTH);
        simBtn.setStyle("-fx-font: 16 arial;");

        Button backBtn = new Button("Nazad");
        backBtn.setOnAction((ActionEvent event) -> {
            SceneChanger.changeScene((new PandaKeyPaddingScreen()).getScreen());
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

        /*VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(tabPane);
        vBox.getChildren().add(grid);

        return vBox;*/
        BorderPane borderPane = new BorderPane(null, tabPane, null, grid, null);

        return borderPane;
    }

    private Tab createSiTab() {
        Tab tab = new Tab();
        tab.setText("K⁺ XOR ipad");

        int cells = State.getInstance().getSelectedHash().getBlockCells();
        int columns = State.getInstance().getSelectedHash().getBlockColumns();

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 0, 0, 0));

        Label expLabel = new Label("Si = K⁺ ^ ipad");
        expLabel.setAlignment(Pos.CENTER);
        expLabel.setStyle("-fx-font: 30 arial");

        vBox.getChildren().add(expLabel);

        HBox hBox = new HBox();
        hBox.setSpacing(30);

        LabelMatrix labelMatrix = new LabelMatrix(State.getInstance().getkPlus(), cells, columns);
        GridPane kPlusMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            kPlusMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        kPlusMatrix.setAlignment(Pos.CENTER);
        kPlusMatrix.setVgap(5);
        kPlusMatrix.setHgap(5);

        hBox.getChildren().add(kPlusMatrix);

        Label xorLabel = new Label("XOR");
        xorLabel.setStyle("-fx-font: 20 arial;");
        hBox.getChildren().add(xorLabel);

        labelMatrix = new LabelMatrix(State.getInstance().getIpad(), cells, columns);
        GridPane ipadMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            ipadMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        ipadMatrix.setAlignment(Pos.CENTER);
        ipadMatrix.setVgap(5);
        ipadMatrix.setHgap(5);

        hBox.getChildren().add(ipadMatrix);

        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(hBox);

        Label eqLabel = new Label("=");
        eqLabel.setStyle("-fx-font: 30 arial;");
        vBox.getChildren().add(eqLabel);

        labelMatrix = new LabelMatrix(State.getInstance().getSi(), cells, columns);
        GridPane SiMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            SiMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        SiMatrix.setAlignment(Pos.CENTER);
        SiMatrix.setVgap(5);
        SiMatrix.setHgap(5);

        vBox.getChildren().add(SiMatrix);

        tab.setContent(vBox);
        tab.setClosable(false);

        return tab;
    }

    private Tab createSoTab() {
        Tab tab = new Tab();
        tab.setText("K⁺ XOR opad");

        int cells = State.getInstance().getSelectedHash().getBlockCells();
        int columns = State.getInstance().getSelectedHash().getBlockColumns();

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 0, 0, 0));

        Label expLabel = new Label("So = K⁺ ^ opad");
        expLabel.setAlignment(Pos.CENTER);
        expLabel.setStyle("-fx-font: 30 arial");

        vBox.getChildren().add(expLabel);

        HBox hBox = new HBox();
        hBox.setSpacing(30);

        LabelMatrix labelMatrix = new LabelMatrix(State.getInstance().getkPlus(), cells, columns);
        GridPane kPlusMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            kPlusMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        kPlusMatrix.setAlignment(Pos.CENTER);
        kPlusMatrix.setVgap(5);
        kPlusMatrix.setHgap(5);
        hBox.getChildren().add(kPlusMatrix);

        Label xorLabel = new Label("XOR");
        xorLabel.setStyle("-fx-font: 20 arial;");
        hBox.getChildren().add(xorLabel);

        labelMatrix = new LabelMatrix(State.getInstance().getOpad(), cells, columns);
        GridPane opadMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            opadMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        opadMatrix.setAlignment(Pos.CENTER);
        opadMatrix.setVgap(5);
        opadMatrix.setHgap(5);

        hBox.getChildren().add(opadMatrix);

        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(hBox);

        Label eqLabel = new Label("=");
        eqLabel.setStyle("-fx-font: 30 arial;");
        vBox.getChildren().add(eqLabel);

        labelMatrix = new LabelMatrix(State.getInstance().getSo(), cells, columns);
        GridPane SoMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            SoMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        SoMatrix.setAlignment(Pos.CENTER);
        SoMatrix.setVgap(5);
        SoMatrix.setHgap(5);

        vBox.getChildren().add(SoMatrix);

        tab.setContent(vBox);
        tab.setClosable(false);

        return tab;
    }

    private Tab createFirstHashInvTab() {
        Tab tab = new Tab();
        tab.setText("H((K⁺ XOR ipad) || poruka)");

        int cells = State.getInstance().getSelectedHash().getBlockCells();
        int columns = State.getInstance().getSelectedHash().getBlockColumns();
        int length = columns - 1;

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 0, 0, 0));
        
        Label expLabel = new Label("T = H(Si || poruka)");
        expLabel.setAlignment(Pos.CENTER);
        expLabel.setStyle("-fx-font: 30 arial");

        vBox.getChildren().add(expLabel);

        HBox hBox = new HBox();
        hBox.setSpacing(30);

        GridPane array = new GridPane();
        array.setHgap(5);

        LabelArray labelArray = new LabelArray(State.getInstance().getSi(), length);
        GridPane SiArray = new GridPane();
        for (int i = 0; i < length; i++) {
            SiArray.add(labelArray.getLabel(i), i, 0);
        }
        SiArray.setAlignment(Pos.CENTER);
        SiArray.setHgap(5);

        /*LabelMatrix labelMatrix = new LabelMatrix(State.getInstance().getkPlus(), cells, columns);
        GridPane kPlusMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            kPlusMatrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        kPlusMatrix.setAlignment(Pos.CENTER);
        kPlusMatrix.setVgap(5);
        kPlusMatrix.setHgap(5);*/
        hBox.getChildren().add(SiArray);

        Label xorLabel = new Label("||");
        xorLabel.setStyle("-fx-font: 20 arial;");
        hBox.getChildren().add(xorLabel);

        labelArray = new LabelArray(State.getInstance().getMessage(), length);
        GridPane messageArray = new GridPane();
        for (int i = 0; i < labelArray.getLength(); i++) {
            messageArray.add(labelArray.getLabel(i), i, 0);
        }
        messageArray.setAlignment(Pos.CENTER);
        messageArray.setHgap(5);
        /*labelMatrix = new LabelMatrix(State.getInstance().getOpad(), cells, columns);
        GridPane opadMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            opadMatrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        opadMatrix.setAlignment(Pos.CENTER);
        opadMatrix.setVgap(5);
        opadMatrix.setHgap(5);*/

        hBox.getChildren().add(messageArray);

        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(hBox);

        Button hashBtn = new Button("H");
        hashBtn.setPrefWidth(0.1 * PANDA_HMAC.WIDTH);
        hashBtn.setPrefHeight(0.1 * PANDA_HMAC.HEIGHT);
        hashBtn.setOnMouseClicked(e -> {
            // TODO veza sa Braninim
            //showFunctionScreen();
        });
        vBox.getChildren().add(hashBtn);
        cells = State.getInstance().getSelectedHash().getDigestCells();
        columns = State.getInstance().getSelectedHash().getDigestColumns();
        LabelMatrix labelMatrix = new LabelMatrix(State.getInstance().getFirstHResult(), cells, columns);
        GridPane firstHMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            firstHMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        firstHMatrix.setAlignment(Pos.CENTER);
        firstHMatrix.setVgap(5);
        firstHMatrix.setHgap(5);

        vBox.getChildren().add(firstHMatrix);

        tab.setContent(vBox);
        tab.setClosable(false);

        return tab;
    }

    private Tab createSecondHashInvTab() {
        Tab tab = new Tab();
        tab.setText("H((K⁺ XOR opad) || H((K⁺ XOR ipad) || poruka))");

        int cells = State.getInstance().getSelectedHash().getBlockCells();
        int columns = State.getInstance().getSelectedHash().getBlockColumns();
        int length = columns - 1;

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 0, 0, 0));
        
        Label expLabel = new Label("HMAC = H(So || T)");
        expLabel.setAlignment(Pos.CENTER);
        expLabel.setStyle("-fx-font: 30 arial");

        vBox.getChildren().add(expLabel);

        HBox hBox = new HBox();
        hBox.setSpacing(30);

        GridPane array = new GridPane();
        array.setHgap(5);

        LabelArray labelArray = new LabelArray(State.getInstance().getSo(), length);
        GridPane SoArray = new GridPane();
        for (int i = 0; i < length; i++) {
            SoArray.add(labelArray.getLabel(i), i, 0);
        }
        SoArray.setAlignment(Pos.CENTER);
        SoArray.setHgap(5);

        /*LabelMatrix labelMatrix = new LabelMatrix(State.getInstance().getkPlus(), cells, columns);
        GridPane kPlusMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            kPlusMatrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        kPlusMatrix.setAlignment(Pos.CENTER);
        kPlusMatrix.setVgap(5);
        kPlusMatrix.setHgap(5);*/
        hBox.getChildren().add(SoArray);

        Label xorLabel = new Label("||");
        xorLabel.setStyle("-fx-font: 20 arial;");
        hBox.getChildren().add(xorLabel);

        labelArray = new LabelArray(State.getInstance().getFirstHResult(), length);
        GridPane firstHResult = new GridPane();
        for (int i = 0; i < labelArray.getLength(); i++) {
            firstHResult.add(labelArray.getLabel(i), i, 0);
        }
        firstHResult.setAlignment(Pos.CENTER);
        firstHResult.setHgap(5);
        /*labelMatrix = new LabelMatrix(State.getInstance().getOpad(), cells, columns);
        GridPane opadMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            opadMatrix.add(labelMatrix.getLabel(i/columns, i%columns), i%columns, i/columns);
        }
        opadMatrix.setAlignment(Pos.CENTER);
        opadMatrix.setVgap(5);
        opadMatrix.setHgap(5);*/

        hBox.getChildren().add(firstHResult);

        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(hBox);

        Button hashBtn = new Button("H");
        hashBtn.setPrefWidth(0.1 * PANDA_HMAC.WIDTH);
        hashBtn.setPrefHeight(0.1 * PANDA_HMAC.HEIGHT);
        hashBtn.setOnMouseClicked(e -> {
            // TODO veza sa Braninim
            //showFunctionScreen();
        });
        vBox.getChildren().add(hashBtn);
        cells = State.getInstance().getSelectedHash().getDigestCells();
        columns = State.getInstance().getSelectedHash().getDigestColumns();
        LabelMatrix labelMatrix = new LabelMatrix(State.getInstance().getSecondHResult(), cells, columns);
        GridPane firstHMatrix = new GridPane();
        for (int i = 0; i < cells; i++) {
            firstHMatrix.add(labelMatrix.getLabel(i / columns, i % columns), i % columns, i / columns);
        }
        firstHMatrix.setAlignment(Pos.CENTER);
        firstHMatrix.setVgap(5);
        firstHMatrix.setHgap(5);

        vBox.getChildren().add(firstHMatrix);

        tab.setContent(vBox);
        tab.setClosable(false);

        return tab;
    }

}
