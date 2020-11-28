package uet.oop.bomberman.entities.powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

public class P_flame extends PowerUp {
    public P_flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void function(Entity e) {
        if(e instanceof Bomber){
            ((Bomber) e).setBombSize(((Bomber) e).getBombSize()+1);
            BombermanGame.board.allEntity.remove(this);
        }
    }
}
