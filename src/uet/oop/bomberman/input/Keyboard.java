package uet.oop.bomberman.input;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard{

    public boolean up, down, left, right, space;

    public Keyboard(){
        up=false;
        down=false;
        left=false;
        right=false;
        space=false;
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getCode()){
            case UP:
            case W:
                up=true;
                break;
            case D:
            case RIGHT:
                right=true;
                break;
            case S:
            case DOWN:
                down=true;
                break;
            case A:
            case LEFT:
                left=true;
                break;
            case SPACE:
                space=true;
                break;
        }
    }

    public void keyReleased(KeyEvent k) {
        switch (k.getCode()){
            case UP:
            case W:
                up=false;
                break;
            case D:
            case RIGHT:
                right=false;
                break;
            case S:
            case DOWN:
                down=false;
                break;
            case A:
            case LEFT:
                left=false;
                break;
            case SPACE:
                space=false;
                break;
        }
    }
}
