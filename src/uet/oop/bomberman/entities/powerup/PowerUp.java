package uet.oop.bomberman.entities.powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class PowerUp extends Entity {

    public PowerUp(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }



    @Override
    public void update() {
        for(int i=0;i< BombermanGame.board.allEntity.size();i++){
            if(BombermanGame.board.allEntity.get(i) instanceof Bomber){
                if(this.isCollided(BombermanGame.board.allEntity.get(i))){
                    function(BombermanGame.board.allEntity.get(i));
                }
            }
        }
    }

    public abstract void function(Entity e);

    @Override
    public boolean isCollided(Entity e) {
        if ((x + Sprite.SCALED_SIZE <= e.getX() ) || (y + Sprite.SCALED_SIZE <= e.getY())
                || (e.getX() + Sprite.SCALED_SIZE <= x) || (e.getY() + Sprite.SCALED_SIZE - 8 <= y)) {
            return false;
        }
        return true;
    }
}
