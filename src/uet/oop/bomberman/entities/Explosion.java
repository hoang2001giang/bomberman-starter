package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {

    protected boolean _last = false;
    int timeLast=19;

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
            canKill();
        }
        else {
            BombermanGame.board.allEntity.remove(this);
        }
    }

    public void canKill(){
        for (int i = 0; i < BombermanGame.board.allEntity.size() ; i++) {
            if (BombermanGame.board.allEntity.get(i) instanceof Bomber) {
                if (this.isCollided(BombermanGame.board.allEntity.get(i))) {
                    ((Bomber) BombermanGame.board.allEntity.get(i)).setAlive(false);
                }
            }
            if (BombermanGame.board.allEntity.get(i) instanceof Enemy) {
                if (this.isCollided(BombermanGame.board.allEntity.get(i))) {
                    ((Enemy) BombermanGame.board.allEntity.get(i)).setAlive(false);
                }
            }
        }
    }

    public boolean isCollided(Entity e) {
        if ((x + Sprite.SCALED_SIZE+16 <= e.x ) || (y + Sprite.SCALED_SIZE +16<= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x) || (e.y + Sprite.SCALED_SIZE  <= y)) {
            return false;
        }
        return true;
    }


}
