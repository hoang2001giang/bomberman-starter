package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

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

        explosions = new Explosion[0];

    }



    public Explosion explosionAt(int x, int y) {
        for (int i = 0; i < explosions.length; i++) {
            if(explosions[i].getX() == x && explosions[i].getY() == y)
                return explosions[i];
        }
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isCollided(Entity e) {
        return false;
    }
}
