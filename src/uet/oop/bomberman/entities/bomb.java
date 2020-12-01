package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class bomb extends Entity {

    protected int explodeTime = 120;
    protected int frameTime = 20;
    protected int size;
    protected Dexp[] explosions = null;

    public bomb(int xUnit, int yUnit,int s) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        this.size = s;
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
            img = Sprite.bomb_exploded2.getFxImage();
            renderExplosions(frameTime);
            if(this.isCollidedBomber(BombermanGame.board.bomber)){
                BombermanGame.board.bomber.setAlive(false);
            }
            if (frameTime > 0) {
                frameTime--;
            } else {
                BombermanGame.board.allEntity.remove(this);
                BombermanGame.board.setBombNum(BombermanGame.board.getBombNum()+1);
            }
        }
    }


    public void renderExplosions(int n){
        if(n!=20) return;
        explosions = new Dexp[4];
        for(int i=0;i<explosions.length;i++){
            explosions[i]=new Dexp(this.x,this.y,i,size);
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

    public int getExplodeTime() {
        return explodeTime;
    }

    public void setExplodeTime(int explodeTime) {
        this.explodeTime = explodeTime;
    }
}
