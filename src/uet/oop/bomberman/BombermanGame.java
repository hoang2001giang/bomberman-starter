package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static GraphicsContext gc;
    private Canvas canvas;

    public static Board board = new Board();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        String path = "music.wav";
        Media media =new Media(new File(path).toURI().toString());
        MediaPlayer mediaplayer =new MediaPlayer(media);
//        mediaplayer.setAutoPlay(true);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                render();
                stage.setTitle("ShazamBomber-Level: "+board.getLevel().get_level());
            }
        };
        timer.start();

        createMap();

        scene.setOnKeyPressed(k -> {
            board.bomber.key.keyPressed(k);
        });

        scene.setOnKeyReleased(k -> {
            board.bomber.key.keyReleased(k);
        });
    }

    public void createMap() {
        board.loadLevel(1);
    }

    public void update() {
        board.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < board.allEntity.size(); i++) {
            board.allEntity.get(i).render(gc);
        }
    }

}
