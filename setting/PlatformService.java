package com.company.setting;

import com.company.model.Cell;
import com.company.model.Direction;
import com.company.model.Game;
import com.company.model.Player;

import java.util.*;

public class PlatformService {

    public static void Create(Game game) {

        Map<Cell, Map<Direction, Cell>> map_Cell_map_direction_cell = new HashMap<>(); // Обновляется в конце
        Cell[] list_Cell = new Cell[49];
        Map<Cell, Map<Cell, Direction>> map_Cell_map_cell_direction = new HashMap<>();
        Map<Cell, Integer> map_Cell_index = new HashMap<>();
        Map<Integer, Cell> map_index_Cell = new HashMap<>();
        Map<Cell, Player> map_Cell_Player = new HashMap<>();
        Cell Fox_Cell = new Cell();
        /**
         *  1) Создает словарь всех ячеек с словарями для связи с другими ячейками и их направлениями
         *  2) Нумерует ячейку для дальнейшего удаления ненужных ячеек
         *  3) Индексирует каждую ячейку для дальнешего удобства
         *  4) Создает словарь для с ячейками начальной расстановкой Fox and Goose **/
        for (int i = 0; i <= 48; i++) {
            list_Cell[i] = new Cell();
            map_Cell_map_cell_direction.put(list_Cell[i], new HashMap<Cell, Direction>());  // 1
            map_Cell_index.put(list_Cell[i], i);  // 2
            map_index_Cell.put(i, list_Cell[i]);  // 3
        }

        /* 4 */
//        int cell_Fox = (int) (Math.random() * 14 + 14);
        for (Map.Entry<Cell, Integer> cell_index : map_Cell_index.entrySet()) {
            if (cell_index.getValue() == 24) {
                map_Cell_Player.put(cell_index.getKey(), Player.FOX);
                Fox_Cell = cell_index.getKey();
            } else if (cell_index.getValue() > 27) {
                map_Cell_Player.put(cell_index.getKey(), Player.GOOSE);
            } else map_Cell_Player.put(cell_index.getKey(), Player.PASS);
        }


        /** Создает платформу 7х7 со связями **/
        for (int i = 0; i <= 48; i++) {
            if (i > 8 && i < 40 && i % 7 != 6 && i % 7 != 0 && i != 12 && i != 36) {
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 8], Direction.NORTHWEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 7], Direction.NORTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 6], Direction.NORTHEAST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 1], Direction.WEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 1], Direction.EAST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 6], Direction.SOUTHWEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 7], Direction.SOUTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 8], Direction.SOUTHEAST);
            }
//          Верх
            if (i > 1 && i < 5) {  // (i == 2 && i == 3 && i == 4)
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 1], Direction.WEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 1], Direction.EAST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 6], Direction.SOUTHWEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 7], Direction.SOUTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 8], Direction.SOUTHEAST);
            }
//          Лево
            if (i > 13 && i < 41 && i % 7 == 0) {  // (i == 14 && i == 21 && i == 28)
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 7], Direction.NORTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 6], Direction.NORTHEAST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 1], Direction.EAST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 7], Direction.SOUTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 8], Direction.SOUTHEAST);
            }
//          Право
            if (i > 13 && i < 41 && i % 7 == 6) {  // (i == 20 && i == 27 && i == 34)
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 8], Direction.NORTHWEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 7], Direction.NORTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 1], Direction.WEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 6], Direction.SOUTHWEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 7], Direction.SOUTH);
            }
//          Низ
            if (i > 43 && i < 47) {  // (i == 44 && i == 45 && i == 46)
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 8], Direction.NORTHWEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 7], Direction.NORTH);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 6], Direction.NORTHEAST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i - 1], Direction.WEST);
                map_Cell_map_cell_direction.get(list_Cell[i]).put(list_Cell[i + 1], Direction.EAST);
            }
        }


        /** Удаляет ненужные ячейки и связи с платформы **/
        Integer[] intArray = new Integer[]{0, 1, 5, 6, 7, 8, 12, 13, 35, 36, 40, 41, 42, 43, 47, 48};
        List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));

        for (int i = 0; i <= 48; i++) {
            if (intList.contains(i)) {
                map_Cell_map_cell_direction.remove(list_Cell[i]);
                continue;
            }
            List<Cell> list_del_cell = new ArrayList<>();
            // Сначала добавляем индексы которые нужно удалить в list_del_cell затем в селдующем цикле их удаляем
            for (Map.Entry<Cell, Direction> p : map_Cell_map_cell_direction.get(map_index_Cell.get(i)).entrySet()) {
                if (intList.contains(map_Cell_index.get(p.getKey()))) {
                    list_del_cell.add(p.getKey());
                }
            }
            for (Cell c : list_del_cell) {
                map_Cell_map_cell_direction.get(map_index_Cell.get(i)).remove(c);
            }
        }

        // Удаляем ненужные ячейки из других словарей
        for (int i = 0; i <= 48; i++) {
            if (intList.contains(i)) {
                map_Cell_index.remove(list_Cell[i]);
                map_index_Cell.remove(i);
                map_Cell_Player.remove(list_Cell[i]);
            }
        }

        /** Создаем обратный словарь cell_cell_direction -> cell_direction_cell для будущего нахождения ячеки в определенном направлении **/
        for (Map.Entry<Cell, Map<Cell, Direction>> key_value : map_Cell_map_cell_direction.entrySet()) {
            map_Cell_map_direction_cell.put(key_value.getKey(), new HashMap<>());
            for (Map.Entry<Cell, Direction> k_v : key_value.getValue().entrySet()) {
                map_Cell_map_direction_cell.get(key_value.getKey()).put(k_v.getValue(), k_v.getKey());
            }
        }

        /** Использовалось для просмотра правильности создания платформы **/
//        for (int i = 0; i <= 48; i++) {
//            if (map_Cell_map_cell_direction.containsKey(list_Cell[i])) {
//                System.out.print(list_Cell[i] + " [" + i + "]" + " - ");
//                for (Map.Entry<Cell, Direction> c : map_Cell_map_cell_direction.get(list_Cell[i]).entrySet()) {
//                    System.out.print(map_Cell_index.get(c.getKey()) + " = " + c.getValue() + "  |-|  ");
//                }
//                System.out.println();
//            }
//        }

//        for (Map.Entry<Cell, Map<Cell, Direction>> c : map_Cell_map_cell_direction.entrySet()) {
//            System.out.println(c);
//        }

        game.setMap_Cell_index(map_Cell_index);
        game.setMap_Cell_map_cell_direction(map_Cell_map_cell_direction);
        game.setMap_Cell_map_direction_cell(map_Cell_map_direction_cell);
        game.setMap_index_Cell(map_index_Cell);
        game.setMap_Cell_Player(map_Cell_Player);
        game.setCell_Fox(Fox_Cell);
        game.setCount_Goose(13);
    }
}

/** Лучше удалять индекс выделяя каждый индекс или поместив все индексы в Список ? **/
//            if (map_Cell_index.get(list_Cell[i]) == 0 || map_Cell_index.get(list_Cell[i]) == 1 || map_Cell_index.get(list_Cell[i]) == 5 || map_Cell_index.get(list_Cell[i]) == 6
//                    || map_Cell_index.get(list_Cell[i]) == 7 || map_Cell_index.get(list_Cell[i]) == 8 || map_Cell_index.get(list_Cell[i]) == 12 || map_Cell_index.get(list_Cell[i]) == 13
//                    || map_Cell_index.get(list_Cell[i]) == 35 || map_Cell_index.get(list_Cell[i]) == 36 || map_Cell_index.get(list_Cell[i]) == 40 || map_Cell_index.get(list_Cell[i]) == 41
//                    || map_Cell_index.get(list_Cell[i]) == 42 || map_Cell_index.get(list_Cell[i]) == 43 || map_Cell_index.get(list_Cell[i]) == 47 || map_Cell_index.get(list_Cell[i]) == 48) {
//                map_Cell_List_cell.remove(list_Cell[i]);
//                continue;
//            }
//            for (Cell c : map_Cell_List_cell.get(list_Cell[i])) {
//                if (map_Cell_index.get(c) == 0 || map_Cell_index.get(c) == 1 || map_Cell_index.get(c) == 5 || map_Cell_index.get(c) == 6 ||
//                        map_Cell_index.get(c) == 7 || map_Cell_index.get(c) == 8 || map_Cell_index.get(c) == 12 || map_Cell_index.get(c) == 13 ||
//                        map_Cell_index.get(c) == 35 || map_Cell_index.get(c) == 36 || map_Cell_index.get(c) == 40 || map_Cell_index.get(c) == 41 ||
//                        map_Cell_index.get(c) == 42 || map_Cell_index.get(c) == 43 || map_Cell_index.get(c) == 47 || map_Cell_index.get(c) == 48) {
//                    updateDelValueCell(map_Cell_List_cell, list_Cell[i], map_Cell_List_cell.get(list_Cell[i]), c);
//                }
//            }