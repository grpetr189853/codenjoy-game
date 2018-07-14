package com.codenjoy.dojo.plumber.model;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */



import com.codenjoy.dojo.plumber.model.items.Input;
import com.codenjoy.dojo.plumber.model.items.Output;
import com.codenjoy.dojo.plumber.model.items.Wall;
import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.Point;

import java.util.LinkedList;
import java.util.List;

import static com.codenjoy.dojo.plumber.model.Elements.INPUT;
import static com.codenjoy.dojo.plumber.model.Elements.OUTPUT;
import static com.codenjoy.dojo.plumber.model.Elements.WALL;
import static java.util.stream.Collectors.toList;

/**
 * Полезный утилитный класс для получения объектов на поле из текстового вида.
 */
public class BoardStartConfig {
    private final LengthToXY xy;

    private String map;

    public BoardStartConfig(String map) {
        this.map = map;
        xy = new LengthToXY(getSize());
    }

    public int getSize() {
        return (int) Math.sqrt(map.length());
    }

    public List<Input> getInput() {
        return pointsOf(INPUT).stream()
                .map(Input::new)
                .collect(toList());
    }
    public List<Output> getOutput() {
        return pointsOf(OUTPUT).stream()
                .map(Output::new)
                .collect(toList());
    }

    public List<Wall> getWalls() {
        return pointsOf(WALL).stream()
                .map(Wall::new)
                .collect(toList());
    }

    private List<Point> pointsOf(Elements element) {
        List<Point> result = new LinkedList<>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == element.ch) {
                result.add(xy.getXY(index));
            }
        }
        return result;
    }
}
