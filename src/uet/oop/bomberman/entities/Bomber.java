package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Entity {


    private int speed;
    private boolean isMoving;
    private boolean isAlive;
    public Keyboard key;
    private int direction;
    protected int ani;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        key =new Keyboard();
        direction=3;
        isMoving=false;
        isAlive=true;
        speed=1;
        ani=0;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void moveUp(int a) {
        y = y - speed*a;
        direction=1;
    }

    public void moveDown(int a) {
        y += speed*a;
        direction=3;
    }

    public void moveLeft(int a) {
        x -= speed*a;
        direction=4;
    }

    public void moveRight(int a) {
        x += speed*a;
        direction=2;
    }

    public void chooseSprite(){
        switch(direction) {
            case 1:
                if(isMoving) {
                    img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, ani, 20).getFxImage();
                }
                else img = Sprite.player_up.getFxImage();
                break;
            case 2:
                if(isMoving) {
                    img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, ani, 20).getFxImage();
                }
                else img = Sprite.player_right.getFxImage();
                break;
            case 3:
                if(isMoving) {
                    img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, ani, 20).getFxImage();
                }
                else img = Sprite.player_down.getFxImage();
                break;
            case 4:
                if(isMoving) {
                    img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, ani, 20).getFxImage();
                }
                else img = Sprite.player_left.getFxImage();
                break;
        }
    }

    public void move(){
        int a=0,b=0;
        if(key.up)a--;
        if(key.down)a++;
        if(key.right)b++;
        if(key.left)b--;

        if(a!=0 || b!=0){
            ani++;
            x+=b*speed;
            y+=a*speed;
            moveDirection(a,b);
            isMoving=true;
        }
        else {
            isMoving=false;
            ani=0;
        }
    }

    public void moveDirection(double xa, double ya) {
        if(xa > 0) direction = 3;
        if(xa < 0) direction = 1;
        if(ya > 0) direction = 2;
        if(ya < 0) direction = 4;
    }

    @Override
    public void update() {
        if(!isAlive){
            return;
        }
        move();
        chooseSprite();

    }
}
