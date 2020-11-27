package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class bomb extends Entity {

    protected int explodeTime = 120;
    protected int frameTime = 20;
    boolean exploded = false;
    protected int size;
    protected Dexp[] explosions = null;

    public bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        this.size = 1;
    }

    private void explode() {
        exploded =true;
        BombermanGame.board.canBomb=true;
    }

    @Override
    public void update() {
        if (explodeTime > 0) {
            explodeTime --;
            switch (explodeTime % 60) {
                case 0:
                    img = Sprite.bomb.getFxImage();
                    break;
                case 40:
                    img = Sprite.bomb_1.getFxImage();
                    break;
                case 20:
                    img = Sprite.bomb_2.getFxImage();
                    break;
            }
        }
        else {
            BombermanGame.board.canBomb=true;
            img = Sprite.bomb_exploded2.getFxImage();
            if (frameTime > 0) {
                frameTime--;
                renderExplosions();
            } else {
                BombermanGame.board.allEntity.remove(this);
            }
        }
    }

    public void renderExplosions(){
        explosions = new Dexp[4];
        for(int i=0;i<explosions.length;i++){
            explosions[i]=new Dexp(this.x,this.y,i,size);
        }
    }


    @Override
    public boolean isCollided(Entity e) {
        return false;
    }


}
