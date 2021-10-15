package com.company.model;

import java.util.Map;

public class Game {

    private Map<Cell, Map<Cell, Direction>> map_Cell_map_cell_direction;
    private Map<Cell, Map<Direction, Cell>> map_Cell_map_direction_cell;
    private Map<Cell, Integer> map_Cell_index;
    private Map<Integer, Cell> map_index_Cell;
    private Map<Cell, Player> map_Cell_Player;
    private Cell Fox_Cell;

    public Cell getFox_Cell() {
        return Fox_Cell;
    }

    public void setFox_Cell(Cell fox_Cell) {
        Fox_Cell = fox_Cell;
    }

    public Map<Cell, Map<Cell, Direction>> getMap_Cell_map_cell_direction() {
        return map_Cell_map_cell_direction;
    }

    public void setMap_Cell_map_cell_direction(Map<Cell, Map<Cell, Direction>> map_Cell_map_cell_direction) {
        this.map_Cell_map_cell_direction = map_Cell_map_cell_direction;
    }

    public Map<Cell, Map<Direction, Cell>> getMap_Cell_map_direction_cell() {
        return map_Cell_map_direction_cell;
    }

    public void setMap_Cell_map_direction_cell(Map<Cell, Map<Direction, Cell>> map_Cell_map_direction_cell) {
        this.map_Cell_map_direction_cell = map_Cell_map_direction_cell;
    }

    public Map<Cell, Integer> getMap_Cell_index() {
        return map_Cell_index;
    }

    public void setMap_Cell_index(Map<Cell, Integer> map_Cell_index) {
        this.map_Cell_index = map_Cell_index;
    }

    public Map<Integer, Cell> getMap_index_Cell() {
        return map_index_Cell;
    }

    public void setMap_index_Cell(Map<Integer, Cell> map_index_Cell) {
        this.map_index_Cell = map_index_Cell;
    }

    public Map<Cell, Player> getMap_Cell_Player() {
        return map_Cell_Player;
    }

    public void setMap_Cell_Player(Map<Cell, Player> map_Cell_Player) {
        this.map_Cell_Player = map_Cell_Player;
    }


}
