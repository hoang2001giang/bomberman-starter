package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends Entity {

    int preX,preY;
    public int direction;
    protected int speed;
    boolean isAlive;
    int deadTime=60;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isCollided(Entity e) {
        if (e instanceof Grass) return false;
        if ((x + Sprite.SCALED_SIZE <= e.x  ) || (y + Sprite.SCALED_SIZE <= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x) || (e.y + Sprite.SCALED_SIZE  <= y )) {
            return false;
        }
        return true;
    }
}
