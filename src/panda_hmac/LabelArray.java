/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author KoRiSnIk
 */
public class LabelArray {

    private Label[] array;
    int lenght;

    public LabelArray(String key, int len) {
        int keyLen = key.length();

        if (keyLen > (len + 1) * 4) {
            array = new Label[len];

            for (int i = 0, ki = 0; i < len / 2; i++, ki += 4) {
                array[i] = new Label(key.substring(ki, ki + 4));
                array[len - (i + 1)] = new Label(key.substring(keyLen - (ki + 4), keyLen - ki));

                array[i].setStyle("-fx-border-color: black");
                array[len - (i + 1)].setStyle("-fx-border-color: black");

                array[i].setAlignment(Pos.CENTER);
                array[len - (i + 1)].setAlignment(Pos.CENTER);

                array[i].setPrefWidth(38);
                array[len - (i + 1)].setPrefWidth(38);
            }

            array[len / 2] = new Label(" . . . . . . . . . ");
            array[len / 2].setStyle("-fx-border-color: black");
            array[len / 2].setAlignment(Pos.CENTER);
        } else if (keyLen < (len + 1) * 4) {
            len = keyLen / 4 + (keyLen % 4 == 2 ? 1 : 0);
            array = new Label[len];
            for (int i = 0, ki = 0; i < len - (keyLen % 4 == 2 ? 1 : 0); i++, ki += 4) {
                array[i] = new Label(key.substring(ki, ki + 4));
                array[i].setStyle("-fx-border-color: black");
                array[i].setAlignment(Pos.CENTER);
                array[i].setPrefWidth(38);
            }
            if (keyLen % 4 == 2) {
                array[len - 1] = new Label(key.substring(keyLen - 2));
                array[len - 1].setStyle("-fx-border-color: black");
                array[len - 1].setAlignment(Pos.CENTER);
                array[len - 1].setPrefWidth(38);
            }

        } else if (keyLen > 0) {
            array = new Label[len + 1];
            for (int i = 0, ki = 0; i < len + 1; i++, ki += 4) {
                array[i] = new Label(key.substring(ki, ki + 4));
                array[i].setStyle("-fx-border-color: black");
                array[i].setAlignment(Pos.CENTER);
                array[i].setPrefWidth(38);
            }
        } else {
            array = new Label[1];
            array[0] = new Label("");
            array[0].setStyle("-fx-border-color: black");
            array[0].setAlignment(Pos.CENTER);
            array[0].setPrefWidth(38);
        }
    }

    public Label getLabel(int i) {
        return array[i];
    }

    public int getLength() {
        return array.length;
    }

}
