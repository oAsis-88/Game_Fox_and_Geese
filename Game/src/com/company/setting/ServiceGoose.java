package com.company.setting;

import com.company.model.*;

import java.util.*;

public class ServiceGoose {

    private int Number_Goose = 13;

    public int getNumber_Goose() {
        return Number_Goose;
    }

    public void setNumber_Goose(int number_Goose) {
        Number_Goose = number_Goose;
    }

    public void logic_Goose(Game game){
        Map<Direction, Direction> reverse_direction = new Direction_reverse_direction().getDirection_reverse_direction();

        Map<Cell, Map<Cell, Direction>> cell_cell_direction = game.getMap_Cell_map_cell_direction();
        Map<Cell, Map<Direction, Cell>> cell_direction_cell = game.getMap_Cell_map_direction_cell();
        Map<Cell, Integer> map_cell_index = game.getMap_Cell_index();
        Map<Integer, Cell> map_index_cell = game.getMap_index_Cell();
        Map<Cell, Player> map_Cell_Player = game.getMap_Cell_Player();

        // Создает словарь гусей с массивом пустых ячеек куда они могут пойти
        Map<Cell, Cell[]> map_cell_list_cell = new HashMap<>();
        for (Map.Entry<Cell, Player> cell_player : map_Cell_Player.entrySet()) {
            if (cell_player.getValue() == Player.GOOSE) {
                for (Map.Entry<Cell, Direction> cell_direction : cell_cell_direction.get(cell_player.getKey()).entrySet()) {
                    if (map_Cell_Player.get(cell_direction.getKey()) == Player.PASS && !cell_direction.getValue().toString().equals("SOUTH") && !cell_direction.getValue().toString().equals("SOUTHEAST") && !cell_direction.getValue().toString().equals("SOUTHWEST")) {
//                        if (map_cell_list_cell.size() > 0) {
                        if (map_cell_list_cell.containsKey(cell_player.getKey())) {
                            updateAddValueCell(map_cell_list_cell, cell_player.getKey(), cell_direction.getKey());
                        }
                        else {
                            map_cell_list_cell.put(cell_player.getKey(), new Cell[] {cell_direction.getKey()});
                        }
                    }
                }
            }
        }

        // Переберает всех гусей которые могут ходить и находит того кто может безопасно сходить
        for (Cell goose : map_cell_list_cell.keySet()) {
            // берет гуся и проверяет все пустые ячейки куда он может походить
//            System.out.print("Goose - " + map_cell_index.get(goose) + " ");
//            System.out.print("  -> [");
//            for (Cell c : map_cell_list_cell.get(goose)) {
//                System.out.print(map_cell_index.get(c) + " ");
//            }
//            System.out.println("]");
            for (Cell cell : map_cell_list_cell.get(goose)) {
                boolean b = true;
                for (Map.Entry<Cell, Direction> cell_direction : cell_cell_direction.get(cell).entrySet()) {
                    if (map_Cell_Player.get(cell_direction.getKey()) == Player.FOX) {
                        if (map_Cell_Player.get(cell_direction_cell.get(cell).get(reverse_direction.get(cell_direction.getValue()))) == Player.GOOSE || map_Cell_Player.get(cell_direction_cell.get(cell).get(reverse_direction.get(cell_direction.getValue()))) == Player.FOX){
                            map_Cell_Player.put(goose, Player.PASS);
                            map_Cell_Player.put(cell, Player.GOOSE);
                            return;
                        }
                        b = false;
                        break;
                    }
                }

//                System.out.println(map_cell_index.get(cell) + "  - " + b);
                if (b) {
                    map_Cell_Player.put(goose, Player.PASS);
                    map_Cell_Player.put(cell, Player.GOOSE);
                    return;
                }

            }
        }

        /** Рандомный гусь **/
        Random       random    = new Random();
        List<Cell> keys = new ArrayList<Cell>(map_cell_list_cell.keySet());
        Cell random_goose = keys.get( random.nextInt(keys.size()) );

//        System.out.println("----- random_goose -----");
//        System.out.print(map_cell_index.get(random_goose) + " - " + random_goose);
//        System.out.print("  -> [");
//        for (Cell c : map_cell_list_cell.get(random_goose)) {
//            System.out.print(map_cell_index.get(c) + " ");
//        }
//        System.out.println("]");

        /** На случай если ввсе ходы будут фатальными **/
        for (Cell c : map_cell_list_cell.get(random_goose)) {
            if (map_Cell_Player.get(c) == Player.PASS){
                map_Cell_Player.put(random_goose, Player.PASS);
                map_Cell_Player.put(c, Player.GOOSE);
                return;
            }
        }

    }

    private static void updateAddValueCell(Map<Cell, Cell[]> map, Cell key, Cell value) {
        if (map.containsKey(key)) {
            Cell[] g = new Cell[map.get(key).length + 1];
            System.arraycopy(map.get(key), 0, g, 0, map.get(key).length);
            g[map.get(key).length] = value;
            map.put(key, g);
        } else {
            map.put(key, new Cell[]{});
        }
    }

}
