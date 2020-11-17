package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int width, height;
    protected Level level;
    public Bomber bomber;
    int _x,_y;

    List<Entity> allEntity;
    List<bomb> allBomb;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        allBomb=new ArrayList<>();
        allEntity=new ArrayList<>();
        level=new Level();
    }

    public void update(){
        allEntity.forEach(Entity::update);
    }

    public void loadLevel(int i){
        String path="/levels/Level"+i+".txt";
        try {
            level.load(path);
            for (int y = 0; y < level._height; y++) {
                for (int x = 0; x < level._width; x++) {
                    addLevelEntity( level._lineTiles[y].charAt(x), x, y );
                }
            }
            bomber = new Bomber(_x,_y,Sprite.player_down.getFxImage());
            allEntity.add(bomber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLevelEntity(char c, int x, int y) {
        switch(c) {
            case '#':
                allEntity.add(new Wall(x, y, Sprite.wall.getFxImage()));
                break;
            case '*':
                allEntity.add(new Grass(x ,y, Sprite.grass.getFxImage()));
                allEntity.add(new Brick(x ,y, Sprite.brick.getFxImage()));
                break;
            case ' ':
                allEntity.add(new Grass(x, y, Sprite.grass.getFxImage()));
                break;
            case 'p':
                allEntity.add(new Grass(x ,y, Sprite.grass.getFxImage()));
                _x=x;
                _y=y;
                break;
//            case '1':
//                _board.addMob( new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                _board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//                break;
//            case '2':
//                _board.addMob( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                _board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
//                break;
        }
    }

}
