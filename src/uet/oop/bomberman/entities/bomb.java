package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class bomb extends Entity {

    protected int explodeTime = 120;
    protected int frameTime = 20;
    boolean exploded = false;
    protected int size;
    protected Explosion[] explosions = null;

    public bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        this.size = 1;
    }

    private void explode() {
        explosions = new Explosion[1 + size*4];
        for (Explosion explosion : explosions) {

        }

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
//            explode();
        }
    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }

}
