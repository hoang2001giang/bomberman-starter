package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AI.AI;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy{
    AI ai =new AI(BombermanGame.board.bomber,this);
    int ani;
    int restTime ;

    public Oneal(int _x, int _y, Image i) {
        x=_x* Sprite.SCALED_SIZE;
        y=_y*Sprite.SCALED_SIZE;
        img=i;
        preY=y;
        preX=x;
        firstX=x;
        firstY=y;
        speed=1;
        isAlive=true;
        direction = new Random().nextInt(4);
        ani=0;
        restTime=0;
        isMoving=false;
    }


    @Override
    public void move() {
        if(isAlive){
            if(!ai.findPlayer()){
                isMoving = false;
            }
            if (canMove() && isMoving) {
                switch (direction){
                    case 0:
                        preY=y;
                        y-=speed;
                        break;
                    case 1:
                        preX=x;
                        x+=speed;break;
                    case 2:
                        preY=y;
                        y+=speed;
                        break;
                    case 3:
                        preX=x;
                        x-=speed;
                        break;
                }
            }
        }
    }

    void checkDirection(){
        if(restTime>0){
            restTime--;
            return;
        }
        if(ai.findPlayer()){
            isMoving=true;
            int n=ai.calculateH();
            int m= ai.calculateV();
            if(direction==n || direction==m) return;
            if(x%32==0 && y%32==0)
            direction= ai.calculateDirection();
        }
    }

    public void check2(){
        if(ai.findPlayer()){
            int n=ai.calculateH();
            int m= ai.calculateV();
            if(!canMove()){
                x=preX;
                y=preY;
                if((direction==n && m==-1) || (direction==m && n==-1)){
//                    direction=new Random().nextInt(4);
                    isMoving=false;
                    restTime=20;
                }
                else if(direction==n && m!=-1) direction=m;
                else if(direction==m && n!=-1) direction=n;
            }
        }
    }

    public void chooseSprite(){
        if(ani > 60)ani =0;
        ani++;
        switch (direction){
            case 0:
            case 1:
                img=Sprite.movingSprite(Sprite.oneal_right1,Sprite.oneal_right2,ani,60).getFxImage();
                break;
            case 2:
            case 3:
                img=Sprite.movingSprite(Sprite.oneal_left1,Sprite.oneal_left2,ani,60).getFxImage();
                break;
        }
    }

    @Override
    public void update() {
        if(isAlive){
            checkDirection();
            move();
            check2();
            chooseSprite();
        }
        if(!isAlive){
            if(deadTime>0){
                deadTime--;
                if(deadTime>60){
                    img=Sprite.oneal_dead.getFxImage();
                }
                else {
                    img= Sprite.movingSprite(Sprite.mob_dead1,Sprite.mob_dead1,Sprite.mob_dead3,deadTime,20).getFxImage();
                }
            }
            else{
                BombermanGame.board.allEntity.remove(this);
            }
        }
    }
}
