package com.codenjoy.dojo.plumber.model.items;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 - 2018 Codenjoy
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


import com.codenjoy.dojo.plumber.model.Elements;
import com.codenjoy.dojo.plumber.model.Player;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

import java.util.HashMap;
import java.util.Map;

import static com.codenjoy.dojo.plumber.model.Elements.*;

public class Pipe extends PointImpl implements State<Elements, Player> {

    private final Elements typePipe;

    public Pipe(Elements pipe, int x, int y) {
        super(x, y);
        this.typePipe = pipe;

    }

    public Pipe(Elements pipe, Point point) {
        super(point);
        this.typePipe = pipe;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return typePipe;
    }

    public static class Builder {
        private final static Map<Integer, Elements> typeMapping = new HashMap<>();

        static {
            Builder.typeMapping.put(0, HORIZONTAL_PIPE);
            Builder.typeMapping.put(1, VERTICAL_PIPE);
            Builder.typeMapping.put(2, UP_LEFT_PIPE);
            Builder.typeMapping.put(3, UP_RIGHT_PIPE);
            Builder.typeMapping.put(4, DOWN_LEFT_PIPE);
            Builder.typeMapping.put(5, DOWN_RIGHT_PIPE);
        }

        public static Pipe build(int code, int x, int y) {
            final Elements type = typeMapping.computeIfAbsent(code, c -> {
                throw new IllegalArgumentException("Unknown pipe code: " + c + ". Available codes: " + typeMapping.keySet());
            });

            return new Pipe(type, x, y);
        }

        public static Map<Integer, Elements> getTypeMapping() {
            return typeMapping;
        }
    }
}