package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.powerup.P_bomb;
import uet.oop.bomberman.entities.powerup.P_flame;
import uet.oop.bomberman.entities.powerup.P_speed;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Board {
    protected Level level;
    public Bomber bomber;
    int _x, _y;
    public boolean canBomb;
    public List<Entity> allEntity;
    int enemyCount;
    int bombNum;


    public Board() {
        allEntity = new ArrayList<>();
        level = new Level();
        canBomb=true;
        enemyCount=0;
        bombNum =1;
        bomber = new Bomber(1, 1, Sprite.player_down.getFxImage());
    }

    public void update() {
        int n=0;
        for (int i = 0; i < allEntity.size(); i++) {
            if(allEntity.get(i) instanceof Enemy){
                n++;
            }
            allEntity.get(i).update();
        }
        enemyCount=n;
    }

    public void loadLevel(int i) {
        allEntity.clear();
        String path = "/levels/Level" + i + ".txt";
        try {
            level.load(path);
            for (int y = 0; y < level._height; y++) {
                for (int x = 0; x < level._width; x++) {
                    addLevelEntity(' ', x, y);
                }
            }
            for (int y = 0; y < level._height; y++) {
                for (int x = 0; x < level._width; x++) {
                    if(level._lineTiles[y].charAt(x)!=' ')
                    addLevelEntity(level._lineTiles[y].charAt(x), x, y);
                }
            }
            bomber.setX(_x*Sprite.SCALED_SIZE);
            bomber.setY(_y*Sprite.SCALED_SIZE);
            allEntity.add(bomber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLevelEntity(char c, int x, int y) {
        switch (c) {
            case '#':
                allEntity.add(new Wall(x, y, Sprite.wall.getFxImage()));
                break;
            case '*':
                allEntity.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case ' ':
                allEntity.add(new Grass(x, y, Sprite.grass.getFxImage()));
                break;
            case 'p':
                allEntity.add(new Grass(x, y, Sprite.grass.getFxImage()));
                _x = x;
                _y = y;
                break;
            case '1':
                allEntity.add(new Balloom(x,y,Sprite.balloom_left1.getFxImage()));
                enemyCount++;
                break;
//            case '2':
//                _board.addMob( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                _board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//                break;
            case 'f':
                allEntity.add(new P_flame(x,y,Sprite.powerup_flames.getFxImage()));
                allEntity.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 'b':
                allEntity.add(new P_bomb(x,y,Sprite.powerup_bombs.getFxImage()));
                allEntity.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 's':
                allEntity.add(new P_speed(x,y,Sprite.powerup_speed.getFxImage()));
                allEntity.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 'x':
                allEntity.add(new Portal(x,y,Sprite.portal.getFxImage()));
                allEntity.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
        }
    }

    public Entity getEntityAt(int _x,int _y){
        for(int i=allEntity.size()-1;i>=0;i--){
            if(allEntity.get(i).getY() == _y && allEntity.get(i).getX() == _x){
                return allEntity.get(i);
            }
        }
        return null;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getBombNum() {
        return bombNum;
    }

    public void setBombNum(int bombNum) {
        this.bombNum = bombNum;
    }
}
