package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Brick extends Entity{
    boolean isDestroyed =false;

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
            BombermanGame.board.allEntity.remove(this);
        }
    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }
}
