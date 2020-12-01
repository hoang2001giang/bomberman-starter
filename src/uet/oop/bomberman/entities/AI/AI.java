package uet.oop.bomberman.entities.AI;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy;

import java.util.Random;

public class AI {
        Random random = new Random();
        Bomber bomber;
        Enemy enemy;

    public AI(Bomber bomber, Enemy enemy) {
        this.bomber = bomber;
        this.enemy = enemy;
    }

    public boolean findPlayer(){
        if(bomber == null) return false;
        int xB =bomber.getX();
        int yB = bomber.getY();
        int xE = enemy.getX();
        int yE = enemy.getY();
        if(xE-xB>3*32 || xB-xE>3*32 || yE-yB>3*32 || yB-yE>3*32){
            return false;
        }
        return true;
    }

    public int calculateDirection(){
        if(!findPlayer()){
            return random.nextInt(4);
        }
        int v=calculateV();
        if(v!=-1)
        return v;
        return calculateH();
    }

    public int calculateV(){
        int n= (bomber.getY()+16)/32;
        if(n*32 < enemy.getY())
            return 0;
        else if(n*32 > enemy.getY())
            return 2;
        return -1;
    }

    public int calculateH(){
        int n=(bomber.getX()+16)/32;
        if(n*32 < enemy.getX())
            return 3;
        else if(n*32 > enemy.getX())
            return 1;
        return -1;
    }
}
