package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.Bomber.Orient.*;

public class Bomber extends Entity {


    private int speed;
    private boolean isMoving;
    private boolean isAlive;
    public Keyboard key;
    private Orient direction;
    protected int ani;
    private int preX, preY;
    private List<bomb> bombList;
    private int killTime=60;
    public int bombSize;
    private int bombTime=10;


    protected enum Orient {UP, DOWN, LEFT, RIGHT};

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        key = new Keyboard();
        direction = DOWN;
        isMoving = false;
        isAlive = true;
        speed = 2;
        ani = 0;
        preX = this.x;
        preY = this.y;
        bombList = new ArrayList<>();
        bombSize=1;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void chooseSprite() {
        switch (direction) {
            case UP:
                if (isMoving) {
                    img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, ani, 20).getFxImage();
                } else img = Sprite.player_up.getFxImage();
                break;
            case RIGHT:
                if (isMoving) {
                    img = Sprite.movingSprite(Sprite.player_right_2, Sprite.player_right_1, ani, 20).getFxImage();
                } else img = Sprite.player_right.getFxImage();
                break;
            case DOWN:
                if (isMoving) {
                    img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, ani, 20).getFxImage();
                } else img = Sprite.player_down.getFxImage();
                break;
            case LEFT:
                if (isMoving) {
                    img = Sprite.movingSprite(Sprite.player_left_2, Sprite.player_left_1, ani, 20).getFxImage();
                } else img = Sprite.player_left.getFxImage();
                break;
        }
    }

    public void move() {
        int a = 0, b = 0;
        if (key.up) a--;
        if (key.down) a++;
        if (key.right) b++;
        if (key.left) b--;

        if (a != 0 || b != 0) {
            ani++;
            isMoving = true;
            moveDirection(a, b);
        } else {
            isMoving = false;
            ani = 0;
        }
    }

//    public void moveDirection(double a, double b) {
//        if (a > 0) direction = DOWN;
//        if (a < 0) direction = UP;
//        if (b > 0) direction = RIGHT;
//        if (b < 0) direction = LEFT;
//
//        if (!canMove()) {
//            x = preX;
//            y = preY;
//        }
//        if (canMove()) {
//            preY = y;
//            y += a * speed;
//            preX = x;
//            x += b * speed;
//        }
//    }
//
//    public boolean canMove() {
//        for (int i = 0; i < BombermanGame.board.allEntity.size() ; i++) {
//            if (BombermanGame.board.allEntity.get(i) instanceof Brick
//                    || BombermanGame.board.allEntity.get(i) instanceof Wall) {
//                if (this.isCollided(BombermanGame.board.allEntity.get(i))) {
//                    return false;
//                }
//            }
//            else if (BombermanGame.board.allEntity.get(i) instanceof Explosion){
//                if (this.isCollided(BombermanGame.board.allEntity.get(i))) {
//                    this.kill();
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public void placeBomb() {
        if (key.space && BombermanGame.board.getBombNum()>0 && bombTime>10) {
            BombermanGame.board.allEntity.add(new bomb( (this.x +16)/ Sprite.SCALED_SIZE,  (this.y +16) / Sprite.SCALED_SIZE,bombSize));
            BombermanGame.board.setBombNum(BombermanGame.board.getBombNum()-1);
            bombTime=0;
        }
        bombTime++;
    }

    public void kill(){
        for(int i=0;i<BombermanGame.board.allEntity.size();i++){
            if(BombermanGame.board.allEntity.get(i) instanceof Enemy){
                ((Enemy) BombermanGame.board.allEntity.get(i)).resetPlace();
            }
        }
        if(killTime>0){
            killTime--;
            img=Sprite.movingSprite(Sprite.player_dead1,Sprite.player_dead2,Sprite.player_dead3,killTime,15).getFxImage();
        }
        else {

            killTime=60;
            isAlive=true;
            direction=RIGHT;
            x=Sprite.SCALED_SIZE;
            y=Sprite.SCALED_SIZE;
        }
    }

    @Override
    public void update() {
        if(isAlive){
            move();
            placeBomb();
            chooseSprite();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if(isAlive){
            chooseSprite();
        }
        else {
            kill();
        }
        gc.drawImage(img,x,y);
    }

    public boolean isCollided(Entity e) {
        if (e instanceof Grass) return false;
        if ((x + Sprite.SCALED_SIZE <= e.x + 8) || (y + Sprite.SCALED_SIZE <= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x) || (e.y + Sprite.SCALED_SIZE - 8 <= y)) {
            return false;
        }
        return true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getBombSize() {
        return bombSize;
    }

    public void setBombSize(int bombSize) {
        this.bombSize = bombSize;
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    public void moveDirection(int a, int b) {
        if (a > 0) direction = DOWN;
        if (a < 0) direction = UP;
        if (b > 0) direction = RIGHT;
        if (b < 0) direction = LEFT;

        if(canMove(a, 0)) {
            preY=y;
            y += a * speed;
        }

        if(canMove(0, b)) {
            preX=x;
            x += b * speed;
        }
    }

    public boolean canMove(int a, int b) {
        int xx = x + b * speed;
        int yy = y + a * speed;
        for (Entity entity : BombermanGame.board.allEntity) {
            if (entity instanceof Brick || entity instanceof Wall) {
                if (this.isCollided(entity, xx, yy)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCollided(Entity e, int x, int y) {
        if (e instanceof Grass) return false;
        if ((x + Sprite.SCALED_SIZE <= e.x + 8) || (y + Sprite.SCALED_SIZE <= e.y)
                || (e.x + Sprite.SCALED_SIZE <= x) || (e.y + Sprite.SCALED_SIZE - 8 <= y)) {
            return false;
        }
        return true;

    }
}
