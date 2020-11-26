package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {

    protected boolean _last = false;

    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Explosion(int _x,int _y,int direction,boolean last){
        super();
        x = _x;
        y = _y;
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
    }

    @Override
    public void update() {
    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }


}