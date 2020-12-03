package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.powerup.PowerUp;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends Entity {

    int firstX,firstY;
    int preX,preY;
    public int direction;
    protected int speed;
    boolean isAlive;
    int deadTime=90;
    boolean isMoving;
    boolean justKill;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void update() {

    }

    public boolean canMove() {
        for (int i = 0; i < BombermanGame.board.allEntity.size() ; i++) {
            if(BombermanGame.board.allEntity.get(i) instanceof Bomber){
                if (this.isCollidedBomber(BombermanGame.board.allEntity.get(i))) {
                    ((Bomber) BombermanGame.board.allEntity.get(i)).setAlive(false);
                    return false;
                }
            }
            if (BombermanGame.board.allEntity.get(i) instanceof Brick
                    || BombermanGame.board.allEntity.get(i) instanceof Wall
                    || BombermanGame.board.allEntity.get(i) instanceof bomb
                    || BombermanGame.board.allEntity.get(i) instanceof PowerUp) {
                if (this.isCollided(BombermanGame.board.allEntity.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract void move();

    @Override
    public boolean isCollided(Entity e) {
        if (e instanceof Grass) return false;
        if ((x + Sprite.SCALED_SIZE <= e.x  ) || (y + Sprite.SCALED_SIZE <= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x) || (e.y + Sprite.SCALED_SIZE  <= y )) {
            return false;
        }
        return true;
    }

    public boolean isCollidedBomber(Entity e) {
        if (e instanceof Grass) return false;
        if ((x + Sprite.SCALED_SIZE <= e.x +8 ) || (y + Sprite.SCALED_SIZE -8 <= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x+8) || (e.y + Sprite.SCALED_SIZE  <= y +8)) {
            return false;
        }
        return true;
    }

    public void resetPlace(){
        x=firstX;
        y=firstY;
        preX=x;
        preY=y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
