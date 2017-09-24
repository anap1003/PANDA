/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KoRiSnIk
 */
public class SceneChanger {
    
    private static Stage stage;
    
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
    
    public static void changeScene(Pane root) {
        Scene scene = new Scene(root, PANDA_HMAC.WIDTH, PANDA_HMAC.HEIGHT);
        stage.setScene(scene);
    }
    
    public static void changeScene(VBox root) {
        Scene scene = new Scene(root, PANDA_HMAC.WIDTH, PANDA_HMAC.HEIGHT);
        stage.setScene(scene);
    }
    
}
