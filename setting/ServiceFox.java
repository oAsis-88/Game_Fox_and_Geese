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
        Map<Cell, Integer> map_Cell_Index = game.getMap_Cell_index();
        Cell Cell_Fox = game.getCell_Fox();

        for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Cell_Fox).entrySet()) {
            if (map_Cell_Player.get(cd.getKey()) == Player.GOOSE) {
                if (map_Cell_Player.get(cell_direction_cell.get(cd.getKey()).get(cd.getValue())) == Player.PASS) {
//                    System.out.println("Found a goose, ate");
                    map_Cell_Player.put(Cell_Fox, Player.PASS);
                    map_Cell_Player.put(cd.getKey(), Player.PASS); // cd.getKey() - Goose
                    game.setCount_Goose(game.getCount_Goose() - 1); // Уменьшает количество гусей
                    map_Cell_Player.put(cell_direction_cell.get(cd.getKey()).get(cd.getValue()), Player.FOX);
                    game.setCell_Fox(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
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
        Cell Cell_Fox = game.getCell_Fox();

        /** Fox Кушает Goose **/
        if (eating_geese(game)) return;
        boolean b = false;
        List<Cell> pass_Cells_for_Fox = new ArrayList<>();
        for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Cell_Fox).entrySet()) {
            if (map_Cell_Player.get(cd.getKey()) == Player.GOOSE) {
                b = true;
            }
            if (map_Cell_Player.get(cd.getKey()) == Player.PASS) {
                pass_Cells_for_Fox.add(cd.getKey());
            }
        }
        if (pass_Cells_for_Fox.size() == 1) {
            for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Cell_Fox).entrySet()) {
                if (map_Cell_Player.get(cd.getKey()) == Player.PASS) {
//                    System.out.println("0000000000000000000000000000000");
                    map_Cell_Player.put(Cell_Fox, Player.PASS);
                    map_Cell_Player.put(cell_direction_cell.get(cd.getKey()).get(cd.getValue()), Player.FOX);
                    game.setCell_Fox(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
                    return;
                }
            }
        }
        if (b) {
            for (Cell cell : pass_Cells_for_Fox) {
//                System.out.println("---------------------");
                map_Cell_Player.put(Cell_Fox, Player.PASS);
                map_Cell_Player.put(cell, Player.FOX);
                game.setCell_Fox(cell);
                return;
//                for (Cell near_cell : cell_cell_direction.get(cell).keySet()) {
//                    if (Cell_Fox == near_cell) {
//                        continue;
//                    }
//                    if (map_Cell_Player.get(near_cell) == Player.PASS) {
//                        System.out.println("---------------------");
//                        map_Cell_Player.put(Cell_Fox, Player.PASS);
//                        map_Cell_Player.put(near_cell, Player.FOX);
////                        Cell_Fox = near_cell;
//                        game.setFox_Cell(near_cell);
//                        return;
//                    }
//                }
            }
        }

        for (Map.Entry<Cell, Direction> cd : cell_cell_direction.get(Cell_Fox).entrySet()) {
            if (map_Cell_Player.get(cd.getKey()) == Player.PASS) {
//                System.out.println("==================================");
                map_Cell_Player.put(Cell_Fox, Player.PASS);
                map_Cell_Player.put(cd.getKey(), Player.FOX); // cell_direction_cell.get(cd.getKey()).get(cd.getValue())
                game.setCell_Fox(cd.getKey());
//                Cell_Fox = cell_direction_cell.get(cd.getKey()).get(cd.getValue());
//                game.setCell_Fox(cell_direction_cell.get(cd.getKey()).get(cd.getValue()));
                return;
            }
        }

    }

}
