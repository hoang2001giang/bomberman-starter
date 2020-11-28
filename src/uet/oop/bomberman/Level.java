package uet.oop.bomberman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class Level {
    protected int _width, _height, _level;
    protected String[] _lineTiles;

    Level(){
    }

    public void load(String path) throws Exception {
        try {
            URL absPath = Level.class.getResource(path);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(absPath.openStream()));

            String data = in.readLine();
            StringTokenizer tokens = new StringTokenizer(data);

            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            _lineTiles = new String[_height];

            for(int i = 0; i < _height; ++i) {
                _lineTiles[i] = in.readLine().substring(0, _width);
            }
            in.close();
        } catch (IOException e) {
            throw new Exception("Error loading level " + path, e);
        }
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }
}
