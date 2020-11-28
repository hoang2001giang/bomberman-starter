package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(BombermanGame.board.getEnemyCount()==0){
            for(int i=0;i< BombermanGame.board.allEntity.size();i++){
                if(BombermanGame.board.allEntity.get(i) instanceof Bomber){
                    if(this.isCollidedBomber(BombermanGame.board.allEntity.get(i))){
                        BombermanGame.board.loadLevel(BombermanGame.board.getLevel().get_level()+1);
                    }
                }
            }
        }
    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }

    public boolean isCollidedBomber(Entity e) {
        if (e instanceof Grass) return false;
        if ((x + Sprite.SCALED_SIZE <= e.x +8 ) || (y + Sprite.SCALED_SIZE -8 <= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x+8) || (e.y + Sprite.SCALED_SIZE  <= y +8)) {
            return false;
        }
        return true;
    }

}
