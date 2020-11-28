package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Dexp extends Entity{
    protected int direction;
    private int radius;
    protected Explosion[] explosions;
    protected int xO,yO;



    public Dexp(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Dexp(int _x,int _y,int di, int r){
        xO=_x;
        yO=_y;
        this.x =_x;
        this.y = _y;
        direction=di;
        radius=r;
        explosions = new Explosion[caculate()];
        createExplosion();
    }

    public Explosion explosionAt(int x, int y) {
        for (int i = 0; i < explosions.length; i++) {
            if(explosions[i].getX() == x && explosions[i].getY() == y)
                return explosions[i];
        }
        return null;
    }

    public void createExplosion(){
        boolean last =false;

        int xUnit = xO / Sprite.SCALED_SIZE;
        int yUnit = yO / Sprite.SCALED_SIZE;
        for (int i = 0; i < explosions.length; i++) {
            if(i==explosions.length-1){
                last=true;
            }
            switch (this.direction) {
                case 0: yUnit--; break;
                case 1: xUnit++; break;
                case 2: yUnit++; break;
                case 3: xUnit--; break;
            }
            explosions[i] = new Explosion(xUnit, yUnit, direction, last);
        }
    }

    public int caculate(){
        int r = 0;
        int xE = this.xO;
        int yE = this.yO;
        while(r < this.radius) {
            if (direction == 0) {
                yE -= Sprite.SCALED_SIZE;
            } else if (direction == 1) {
                xE += Sprite.SCALED_SIZE;
            } else if (direction == 2) {
                yE += Sprite.SCALED_SIZE;
            } else if (direction == 3) {
                xE -= Sprite.SCALED_SIZE;
            }

            Entity entity = BombermanGame.board.getEntityAt(xE, yE);
            if (entity instanceof Wall) {
                break;
            }if (entity instanceof Brick) {
                ((Brick) entity).setDestroyed(true);
                break;
            }
            ++r;
        }
        return r;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }

}
