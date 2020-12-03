package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Kondoria extends Enemy{

    int changeTime=120;

    public Kondoria(int _x, int _y, Image a) {
        Random g=new Random();
        x=_x* Sprite.SCALED_SIZE;
        y=_y*Sprite.SCALED_SIZE;
        preY=y;
        preX=x;
        firstX=x;
        firstY=y;
        img=a;
        speed=1;
        direction=g.nextInt(4);
        changeTime+= g.nextInt(30);
        isAlive=true;
    }

    @Override
    public boolean canMove() {
        for (int i = 0; i < BombermanGame.board.allEntity.size() ; i++) {
            if (BombermanGame.board.allEntity.get(i) instanceof Wall
                    || BombermanGame.board.allEntity.get(i) instanceof bomb) {
                if (this.isCollided(BombermanGame.board.allEntity.get(i))) {
                    return false;
                }
            }
            if(BombermanGame.board.allEntity.get(i) instanceof Bomber){
                if (this.isCollidedBomber(BombermanGame.board.allEntity.get(i))) {
                    ((Bomber) BombermanGame.board.allEntity.get(i)).setAlive(false);
                    return true;
                }
            }
        }
        return true;
    }

    public void move(){
        if(isAlive){
            if(!canMove()){
                Random g = new Random();
                x=preX;
                y=preY;
                direction = g.nextInt(4);
            }
            if(canMove()){
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

    public void chooseSprite(){
        if(changeTime>0){
            changeTime--;
        }
        else {
            Random g= new Random();
            changeTime=120+g.nextInt(30);
            direction = g.nextInt(4);
        }

        switch (direction){
            case 0:
            case 1:
                img=Sprite.movingSprite(Sprite.kondoria_right1,Sprite.kondoria_right2,changeTime,60).getFxImage();
                break;
            case 2:
            case 3:
                img=Sprite.movingSprite(Sprite.kondoria_left1,Sprite.kondoria_left2,changeTime,60).getFxImage();
                break;
        }
    }

    @Override
    public void update() {
        move();
        chooseSprite();
        if(!isAlive){
            if(deadTime>0){
                deadTime--;
                if(deadTime>60){
                    img=Sprite.kondoria_dead.getFxImage();
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
