package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Bomber extends Entity {


    private int speed=5;
    private int step=0;
    private boolean isMoving=false;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void moveUp(){
        isMoving=true;
        changeStep();
        y-=speed;
        upM(step);
    }

    public void moveDown(){
        isMoving=true;
        changeStep();
        y+=speed;
        downM(step);
    }

    public void moveLeft(){
        isMoving=true;
        changeStep();
        x-=speed;
        leftM(step);
    }

    public void moveRight(){
        isMoving=true;
        changeStep();
        x+=speed;
        rightM(step);
    }


    public void changeStep(){
        if(step==2){
            step=0;
        }
        else step++;
    }

    public void downM(int i){
        List<Image> myL =new ArrayList<>();
        myL.add(Sprite.player_down.getFxImage());
        myL.add(Sprite.player_down_1.getFxImage());
        myL.add(Sprite.player_down_2.getFxImage());
        img= myL.get(i);
    }

    public void upM(int i){
        List<Image> myL =new ArrayList<>();
        myL.add(Sprite.player_up.getFxImage());
        myL.add(Sprite.player_up_1.getFxImage());
        myL.add(Sprite.player_up_2.getFxImage());
        img= myL.get(i);
    }

    public void leftM(int i){
        List<Image> myL =new ArrayList<>();
        myL.add(Sprite.player_left.getFxImage());
        myL.add(Sprite.player_left_1.getFxImage());
        myL.add(Sprite.player_left_2.getFxImage());
        img= myL.get(i);
    }

    public void rightM(int i){
        List<Image> myL =new ArrayList<>();
        myL.add(Sprite.player_right.getFxImage());
        myL.add(Sprite.player_right_1.getFxImage());
        myL.add(Sprite.player_right_2.getFxImage());
        img= myL.get(i);
    }

    @Override
    public void update() {
        if(!isMoving){
            img=Sprite.player_down.getFxImage();
        }
    }
}
