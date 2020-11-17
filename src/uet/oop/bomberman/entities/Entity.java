package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( double xUnit, double yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isCollided(Entity e){
        if(e instanceof Grass)return false;

        if((x+Sprite.SCALED_SIZE-11<=e.x) || (y+Sprite.SCALED_SIZE<=e.y)
           || (e.x+Sprite.SCALED_SIZE<=x) || (e.y+Sprite.SCALED_SIZE-11<=y) ){
            return false;
        }
        return true;
    }
}
