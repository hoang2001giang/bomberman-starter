package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity{
    boolean isDestroyed =false;
    private int timeLeft=20;

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }



    @Override
    public void update() {
        if(isDestroyed){
            if(timeLeft>0){
                timeLeft--;
                img=Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,timeLeft,20).getFxImage();
            }
            else {
                BombermanGame.board.allEntity.remove(this);
            }
        }
    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }
}
