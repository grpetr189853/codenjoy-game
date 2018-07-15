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
import com.codenjoy.dojo.plumber.model.items.Pipe;
import com.codenjoy.dojo.plumber.model.items.Wall;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.multiplayer.GameField;
import com.codenjoy.dojo.services.printer.BoardReader;

import java.util.*;

/**
 * О! Это самое сердце игры - борда, на которой все происходит.
 * Если какой-то из жителей борды вдруг захочет узнать что-то у нее, то лучше ему дать интефейс {@see Field}
 * Борда реализует интерфейс {@see Tickable} чтобы быть уведомленной о каждом тике игры. Обрати внимание на {Sample#tick()}
 */
public class Field implements GameField<Player> {

    private final List<Input> input;
    private final List<Output> output;
    private final List<Wall> walls;
    private final Collection<Pipe> pipes;

    private Player player;

    private final int size;

    public Field(BoardStartConfig startConfig) {
        walls = startConfig.getWalls();
        input = startConfig.getInput();
        output = startConfig.getOutput();
        size = startConfig.getSize();
        pipes = new LinkedHashSet<>();
    }

    @Override
    public void tick() {
        /*
        if (this.player != null) {
            if (this.player.getHero() != null) {
                this.player.getHero().tick();
            }
        }
        */
        Optional.ofNullable(this.player)
                .map(Player::getHero)
                .ifPresent(Hero::tick);
    }

    @Override
    public void newGame(Player player) {
        if (this.player == null) {
            this.player = player;
            this.player.newHero(this);
        } else {
            throw new MoreThanSinglePlayerAddedException();
        }
    }

    @Override
    public void remove(Player player) {
        if (Objects.equals(this.player, player)) {
            this.player = null;
        }
    }

    @Override
    public BoardReader reader() {
        return new BoardReader() {
            private int size = Field.this.size;

            @Override
            public int size() {
                return size;
            }

            @Override
            public Iterable<? extends Point> elements() {
                return new LinkedList<Point>() {{
                    addAll(Field.this.walls);
                    addAll(Field.this.input);
                    addAll(Field.this.output);
                    addAll(Field.this.pipes);
                }};
            }
        };
    }

    public boolean addPipe(Pipe pipe) {
        if(validatePipesConnectivity(pipe)){
            return pipes.add(pipe);
        } else {
            return false;
        }
    }

    private boolean validatePipesConnectivity(Pipe pipe) {
        return false;
    }
}
