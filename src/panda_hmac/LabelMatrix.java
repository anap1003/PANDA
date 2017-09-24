/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import java.awt.Color;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javax.swing.BorderFactory;

/**
 *
 * @author KoRiSnIk
 */
public class LabelMatrix {
    
    private Label[][] matrix;
    
    StringBuilder kPlus;
    
    public LabelMatrix(String key, int cells, int columns) {
        matrix = new Label[cells/columns + cells%columns][columns];
        
        kPlus = new StringBuilder(cells*4);
        
        for (int i = 0, ki = 0; i < cells; i++, ki += 4) {
            matrix[i/columns][i%columns] = new Label();
            matrix[i/columns][i%columns].setStyle("-fx-border-color: black");
            matrix[i/columns][i%columns].setAlignment(Pos.CENTER);

            String value;
            if (ki < key.length() && ki + 4 <= key.length()) {
                value = key.substring(ki, ki + 4);
            } else if (ki < key.length()) {
                value = key.substring(ki, key.length());
                switch(key.length()%4) {
                    case 3: value += "0"; break;
                    case 2: value += "00"; break;
                    case 1: value += "000"; break;
                }
            } else {
                value = "0000";
                matrix[i/columns][i%columns].setDisable(true);
            }
            matrix[i/columns][i%columns].setText(value);
            matrix[i/columns][i%columns].setPrefWidth(38);
            kPlus.append(value);
        }
    }
    
    public String getkPlus() {
        return kPlus.toString();
    }
    
    public Label getLabel(int row, int column) {
        return matrix[row][column];
    }
    
    private void setBackgroundColor(int row, int column) {
        matrix[row][column].setStyle("-fx-border-color: black; -fx-background-color:  #c5acec");
    }
    
    private void clearBackgroundColor(int row, int column) {
        matrix[row][column].setStyle("-fx-border-color: black");
    }
    
    public static void setHoverEffects(LabelMatrix leftOp, LabelMatrix rightOp, LabelMatrix result, int cells, int columns) {
        for (int i = 0; i < cells; i++) {
            int j = i;
            leftOp.getLabel(i / columns, i % columns).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    leftOp.setBackgroundColor(j / columns, j % columns);
                    rightOp.setBackgroundColor(j / columns, j % columns);
                    result.setBackgroundColor(j / columns, j % columns);
                }
            });
            rightOp.getLabel(i / columns, i % columns).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    leftOp.setBackgroundColor(j / columns, j % columns);
                    rightOp.setBackgroundColor(j / columns, j % columns);
                    result.setBackgroundColor(j / columns, j % columns);
                }
            });
            result.getLabel(i / columns, i % columns).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    leftOp.setBackgroundColor(j / columns, j % columns);
                    rightOp.setBackgroundColor(j / columns, j % columns);
                    result.setBackgroundColor(j / columns, j % columns);
                }
            });
            leftOp.getLabel(i / columns, i % columns).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    leftOp.clearBackgroundColor(j / columns, j % columns);
                    rightOp.clearBackgroundColor(j / columns, j % columns);
                    result.clearBackgroundColor(j / columns, j % columns);
                }
            });
            rightOp.getLabel(i / columns, i % columns).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    leftOp.clearBackgroundColor(j / columns, j % columns);
                    rightOp.clearBackgroundColor(j / columns, j % columns);
                    result.clearBackgroundColor(j / columns, j % columns);
                }
            });
            result.getLabel(i / columns, i % columns).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    leftOp.clearBackgroundColor(j / columns, j % columns);
                    rightOp.clearBackgroundColor(j / columns, j % columns);
                    result.clearBackgroundColor(j / columns, j % columns);
                }
            });
        }
    }
    
}
