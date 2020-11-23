package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Entity {


    private double speed;
    private boolean isMoving;
    private boolean isAlive;
    public Keyboard key;
    private int direction;
    protected int ani;
    private double preX,preY;
    private  List<bomb> bombList;
    private boolean canBomb;

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
        key =new Keyboard();
        direction=3;
        isMoving=false;
        isAlive=true;
        speed=2;
        ani=0;
        preX=this.x;
        preY=this.y;
        bombList=new ArrayList<>();
        canBomb=true;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
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
            isMoving=true;
            moveDirection(a,b);
        }
        else {
            isMoving=false;
            ani=0;
        }
    }

    public void moveDirection(double a, double b) {
        if(a > 0) direction = 3;
        if(a < 0) direction = 1;
        if(b > 0) direction = 2;
        if(b < 0) direction = 4;

        if(!canMove()){
            x=preX;
            y=preY;
        }
        if(canMove()){
            preY=y;
            y+=a*speed;
            preX=x;
            x += b * speed;
        }
    }

    public boolean canMove(){
        int n=BombermanGame.board.allEntity.size();
        for(int i=0;i<n-1;i++){
            if(BombermanGame.board.allEntity.get(i) instanceof Brick
                || BombermanGame.board.allEntity.get(i) instanceof Wall){
                if(this.isCollided(BombermanGame.board.allEntity.get(i))){
                    return false;
                }
            }
        }
        return true;
    }

    public void placeBomb(){
        if(key.space && canBomb){
            BombermanGame.board.allEntity.add(new bomb(this.x/Sprite.SCALED_SIZE,this.y/Sprite.SCALED_SIZE,Sprite.bomb.getFxImage()));
            canBomb=false;
        }
    }

    @Override
    public void update() {
        if(!isAlive){
            return;
        }
        move();
        placeBomb();
        chooseSprite();
    }
}
