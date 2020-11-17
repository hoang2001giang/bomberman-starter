package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class bomb extends Entity {

    protected double timeLeft =120;
    protected int afterExp = 20;
    boolean exp= false;

    public bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
