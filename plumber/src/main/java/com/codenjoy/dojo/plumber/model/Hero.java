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


import com.codenjoy.dojo.plumber.model.items.Pipes;
import com.codenjoy.dojo.services.multiplayer.GameField;
import com.codenjoy.dojo.services.multiplayer.PlayerHero;

/**
 * Это реализация героя. Обрати внимание, что он имплементит {@see Joystick}, а значит может быть управляем фреймворком
 * Так же он имплементит {@see Tickable}, что значит - есть возможность его оповещать о каждом тике игры.
 * Ну и конечно же он имплементит {@see State}, а значит может быть отрисован на поле.
 * Часть этих интерфейсов объявлены в {@see PlayerHero}, а часть явно тут.
 */
public class Hero extends PlayerHero<GameField<Player>> {

    @Override
    public void down() {

    }

    @Override
    public void up() {

    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void act(final int... ints) {

    }

    @Override
    public void tick() {
        this.field.addPipe();
    }

    public void addPipe(Pipes pipe, int x, int y){
        this.nextPipeToAdd = pipe;
        this.nextX = x;
        this.nextY = y;

    }
}
