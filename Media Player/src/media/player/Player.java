/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.player;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;



/**
 *
 * @author hp
 */
public class Player extends BorderPane  {
    
    Media Media;
    MediaPlayer player;
    MediaView view;
    Pane Media_pane;
    Navigation_Bar bar;
    
    public Player(String File){
        Media = new Media(File);
        player = new MediaPlayer(Media);
        view = new MediaView(player);
        Media_pane = new Pane();
         
       Media_pane.getChildren().add(view);
        
       setCenter(Media_pane);
       
       
       
       bar = new Navigation_Bar(player);
       setBottom(bar);
       
       player.play();
        
    }
    
}
