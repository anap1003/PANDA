/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panda_hmac;

import control.HashInfo;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KoRiSnIk
 */
public class PANDA_HMAC extends Application {
    
    private Stage stage;
    
    private int step;
    private int maxSteps;
    
    private Background defaultBackground;
    
    public static int WIDTH = 1000, HEIGHT = 700;
    public static final int MAXIMUM_MESSAGE_LENGTH = 420;
    
    public static HashMap<String, HashInfo> hashInfoMap;
    
    private void initHashInfoMap() {
        hashInfoMap = new HashMap<>();
        hashInfoMap.put("SHA3-224", new HashInfo("SHA3-224", 288, 56, false, 72, 8, 14, 4));
        hashInfoMap.put("SHA3-256", new HashInfo("SHA3-256", 272, 64, false, 68, 8, 16, 4));
        hashInfoMap.put("SHA3-384", new HashInfo("SHA3-384", 208, 96, false, 52, 8, 24, 4));
        hashInfoMap.put("SHA3-512", new HashInfo("SHA3-512", 144, 128, false, 36, 8, 32, 4));
    }
    
    @Override
    public void start(Stage primaryStage) {
        initHashInfoMap();
        
        VBox root = (new PandaIntroScreen()).getScreen(primaryStage);
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        this.defaultBackground = root.getBackground();
        
        primaryStage.setTitle("PANDA - Vizuelizacija HMAC algoritma sa SHA-3 hash funkcijom");
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        
        SceneChanger.setStage(primaryStage);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
