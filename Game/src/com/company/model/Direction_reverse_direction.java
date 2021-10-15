package com.company.model;

import java.util.HashMap;
import java.util.Map;

public class Direction_reverse_direction {
    public Map<Direction, Direction> getDirection_reverse_direction(){
        Map<Direction, Direction> direction_reverse_direction = new HashMap<>();
        direction_reverse_direction.put(Direction.NORTH, Direction.SOUTH);
        direction_reverse_direction.put(Direction.NORTHEAST, Direction.SOUTHWEST);
        direction_reverse_direction.put(Direction.EAST, Direction.WEST);
        direction_reverse_direction.put(Direction.SOUTHEAST, Direction.NORTHWEST);
        direction_reverse_direction.put(Direction.SOUTH, Direction.NORTH);
        direction_reverse_direction.put(Direction.SOUTHWEST, Direction.NORTHEAST);
        direction_reverse_direction.put(Direction.WEST, Direction.EAST);
        direction_reverse_direction.put(Direction.NORTHWEST, Direction.SOUTHEAST);
        return direction_reverse_direction;
    }
}

