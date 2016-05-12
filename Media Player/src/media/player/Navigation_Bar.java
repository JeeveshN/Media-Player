/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.player;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer.Status;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hp
 */
public class Navigation_Bar extends HBox{
    
    Slider vol = new Slider();
    Slider time = new Slider();
    
    
    Button play= new Button();
    Label volume = new Label ("");
    MediaPlayer newPlayer;    

    public Navigation_Bar(MediaPlayer player){
        newPlayer = player;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,10,5,10));
        
        final Image imgPlay= new Image(getClass().getResourceAsStream("play.png"));
        final Image imgPause= new Image(getClass().getResourceAsStream("pause.png"));
        final Image imgVolume= new Image(getClass().getResourceAsStream("volume.png"));
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        HBox.setHgrow(time,Priority.ALWAYS);
        
        play.setPrefWidth(30);
        play.setStyle("-fx-background-color: transparent;");
        play.setGraphic(new ImageView(imgPause));
        volume.setGraphic(new ImageView(imgVolume));
        
        
        getChildren().add(play);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);
        newPlayer.setVolume(100);
        vol.setValue(100);
        
        
        
        play.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Status status = newPlayer.getStatus();
                
                if(status == Status.PLAYING){
                    if(newPlayer.getCurrentTime().greaterThanOrEqualTo(newPlayer.getTotalDuration())){
                        newPlayer.seek(newPlayer.getStartTime());
                        play.setGraphic(new ImageView(imgPause));
                        newPlayer.play();                        
                    }
                    else{
                        newPlayer.pause();
                        play.setText("");
                        play.setGraphic(new ImageView(imgPlay));     
                        
                    }
                    
                }
                if(status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED ){
                        newPlayer.play();
                        play.setGraphic(new ImageView(imgPause));
                    }
            }
            });
        
        newPlayer.currentTimeProperty().addListener
        (
                new InvalidationListener(){
                    @Override
                    public void invalidated(Observable ob){
                    Update();                
                    }
                }
        );
        
        time.valueProperty().addListener(new InvalidationListener(){
        
            @Override
            public void invalidated(Observable ov){
                if(time.isPressed()){
                    newPlayer.seek(newPlayer.getMedia().getDuration().multiply(time.getValue()/100));
                }
            }
        });
        
        vol.valueProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable ob){
                if(vol.isPressed()){
                    newPlayer.setVolume(vol.getValue()/100);
                }
            }
        });
        
        /*newPlayer.setOnEndOfMedia(new Runnable(){
            @Override
            public void run(){
                play.setGraphic(new ImageView(imgPlay));               
            }
        });*/
    }
    protected void Update(){
        
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    time.setValue(newPlayer.getCurrentTime().toMillis()/newPlayer.getTotalDuration().toMillis()*100);
                }
            });
        }    
}
