package com.company.setting;

import com.company.model.Cell;
import com.company.model.Direction;
import com.company.model.Game;
import com.company.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceFox {

    private boolean eating_geese(Game game) {
        Map<Cell, Map<Cell, Direction>> cell_cell_direction = game.getMap_Cell_map_cell_direction();
        Map<Cell, Map<Direction, Cell>> cell_direction_cell = game.getMap_Cell_map_direction_cell();
        Map<Cell, Player> map_Cell_Player = game.getMap_Cell_Player();
        Cell Fox_Cell = game.getFox_Cell();

        for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Fox_Cell).entrySet()) {
            if (map_Cell_Player.get(cd.getKey()) == Player.GOOSE) {
                if (map_Cell_Player.get(cell_direction_cell.get(cd.getKey()).get(cd.getValue())) == Player.PASS) {
                    System.out.println("YES");
                    map_Cell_Player.put(Fox_Cell, Player.PASS);
                    map_Cell_Player.put(cd.getKey(), Player.PASS);
                    map_Cell_Player.put(cell_direction_cell.get(cd.getKey()).get(cd.getValue()), Player.FOX);
                    game.setFox_Cell(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
                    eating_geese(game);
                    return true;
                }
            }
        }
        return false;
    }

    public void logic_Fox(Game game){
        Map<Cell, Map<Cell, Direction>> cell_cell_direction = game.getMap_Cell_map_cell_direction();
        Map<Cell, Map<Direction, Cell>> cell_direction_cell = game.getMap_Cell_map_direction_cell();
        Map<Cell, Integer> map_cell_index = game.getMap_Cell_index();
        Map<Integer, Cell> map_index_cell = game.getMap_index_Cell();
        Map<Cell, Player> map_Cell_Player = game.getMap_Cell_Player();
        Cell Fox_Cell = game.getFox_Cell();

        System.out.println(map_cell_index.get(Fox_Cell));
        System.out.println(Fox_Cell);
        boolean b = false;
        List<Cell> pass_Cells_for_Fox = new ArrayList<>();
        /** Fox Кушает Goose **/
//        b = eating_geese(game);
        if (eating_geese(game)) return;
        for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Fox_Cell).entrySet()) {
            if (map_Cell_Player.get(cd.getKey()) == Player.GOOSE) {
                b = true;
//                if (map_Cell_Player.get(cell_direction_cell.get(cd.getKey()).get(cd.getValue())) == Player.PASS) {
//                    System.out.println("YES");
//                    map_Cell_Player.put(Fox_Cell, Player.PASS);
//                    map_Cell_Player.put(cd.getKey(), Player.PASS);
//                    map_Cell_Player.put(cell_direction_cell.get(cd.getKey()).get(cd.getValue()), Player.FOX);
////                    Fox_Cell = cell_direction_cell.get(cd.getKey()).get(cd.getValue());
//                    game.setFox_Cell(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
//                    return;
//                }
            }
            if (map_Cell_Player.get(cd.getKey()) == Player.PASS) {
                pass_Cells_for_Fox.add(cd.getKey());
            }
        }
        if (pass_Cells_for_Fox.size() == 1) {
            for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Fox_Cell).entrySet()) {
                if (map_Cell_Player.get(cd.getKey()) == Player.PASS) {
                    System.out.println("0000000000000000000000000000000");
                    map_Cell_Player.put(Fox_Cell, Player.PASS);
                    map_Cell_Player.put(cell_direction_cell.get(cd.getKey()).get(cd.getValue()), Player.FOX);
//                    Fox_Cell = cell_direction_cell.get(cd.getKey()).get(cd.getValue());
                    game.setFox_Cell(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
                    return;
                }
            }
        }
//        Map<Cell, > analysis_Cell = "";
        if (b) {
            for (Cell cell : pass_Cells_for_Fox) {
                for (Cell near_cell : cell_cell_direction.get(cell).keySet()) {
                    if (Fox_Cell == near_cell) {
                        continue;
                    }
                    if (map_Cell_Player.get(near_cell) == Player.PASS) {
                        System.out.println("---------------------");
                        map_Cell_Player.put(Fox_Cell, Player.PASS);
                        map_Cell_Player.put(near_cell, Player.FOX);
//                        Fox_Cell = near_cell;
                        game.setFox_Cell(near_cell);
                        return;
                    }
                }
            }
        }
        for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Fox_Cell).entrySet()) {
            if (map_Cell_Player.get(cd.getKey()) == Player.PASS) {
                System.out.println("==================================");
                map_Cell_Player.put(Fox_Cell, Player.PASS);
                map_Cell_Player.put(cell_direction_cell.get(cd.getKey()).get(cd.getValue()), Player.FOX);
//                Fox_Cell = cell_direction_cell.get(cd.getKey()).get(cd.getValue());
                game.setFox_Cell(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
                return;
            }
        }

    }

}
