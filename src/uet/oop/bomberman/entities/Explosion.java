package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {

    protected boolean _last = false;
    int timeLast=20;

    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Explosion(int _x,int _y,int direction,boolean last){
        x = _x*Sprite.SCALED_SIZE;
        y = _y*Sprite.SCALED_SIZE;
        _last = last;

        switch (direction) {
            case 0:
                if(last == false) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                }
                break;
            case 1:
                if(last == false) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                }
                break;
            case 2:
                if(last == false) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                }
                break;
            case 3:
                if(last == false) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                }
                break;
        }
        BombermanGame.board.allEntity.add(this);
    }

    @Override
    public void update() {
        if(timeLast>0){
            timeLast--;
        }
        else {
            BombermanGame.board.allEntity.remove(this);
        }
    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }


}
