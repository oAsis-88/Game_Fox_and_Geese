package com.company;

import com.company.model.Game;
import com.company.setting.GameService;

public class Application {

    public static void main(String[] args) {
        // write your code here
        Game g = new Game();
        GameService game = new GameService();
        game.start(g);

    }
}
