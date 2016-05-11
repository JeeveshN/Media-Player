/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.player;


import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class MediaPlayer extends Application {

    Player newPlayer;
    FileChooser filechooser;
    @Override
    public void start(final Stage primaryStage) {
        
        primaryStage.centerOnScreen();
        primaryStage.setMinWidth(700);
        
        MenuItem open = new MenuItem("Open");
        Menu File = new Menu("File");
        final MenuBar menu = new MenuBar();
        
        File.getItems().add(open);
        menu.getMenus().add(File);
        
        filechooser = new FileChooser();
        
        open.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                newPlayer.player.pause();
                File file = filechooser.showOpenDialog(primaryStage);
                
                if(file!=null){
                    try{                        
                        newPlayer = new Player(file.toURI().toURL().toExternalForm());
                        newPlayer.setTop(menu);
                        Scene newScene = new Scene(newPlayer,720,535,Color.BLACK);                        
                        newPlayer.view.fitWidthProperty().bind(newScene.widthProperty());                        
                        primaryStage.setScene(newScene);
                        primaryStage.show();
                    }
                    catch(MalformedURLException ex){
                    }
                }
  
            }
        });
        
        
        newPlayer = new Player("file:///F:/Mind.mp4");
        newPlayer.setTop(menu);
        Scene newScene = new Scene(newPlayer,720,535,Color.BLACK);
        newPlayer.view.fitWidthProperty().bind(newScene.widthProperty());
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
