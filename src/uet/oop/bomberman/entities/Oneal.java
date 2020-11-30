package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{
    public Oneal(int _x, int _y, Image i) {
        x=_x* Sprite.SCALED_SIZE;
        y=_y*Sprite.SCALED_SIZE;
        img=i;
        preY=y;
        preX=x;
        speed=1;
        isAlive=true;
    }


    @Override
    public void move() {

    }

    @Override
    public void update() {
        canMove();
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
