package com.company.setting;

import com.company.model.Game;
import com.company.model.Player;

public class DrawGameService {

    public void draw(Game game){

        for (int i = 0; i <= 48; i++) {
//            System.out.print(map_Cell_Player.get(map_index_cell.get(i)));
            if (i % 7 == 0) {
                System.out.println();
            }
            if (game.getMap_index_Cell().containsKey(i)) {
                if (game.getMap_Cell_Player().get(game.getMap_index_Cell().get(i)) == Player.FOX) {
                    System.out.print("f ");
                }
                else if (game.getMap_Cell_Player().get(game.getMap_index_Cell().get(i)) == Player.GOOSE) {
                    System.out.print("g ");
                }
                else {
                    System.out.print("* ");
                }
            }
            else {
                System.out.print("  ");
            }
        }
        System.out.println("\n");

    }
}
