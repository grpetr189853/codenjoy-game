package com.codenjoy.dojo.plumber;

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


import com.codenjoy.dojo.plumber.model.BoardStartConfig;
import com.codenjoy.dojo.plumber.model.Field;
import com.codenjoy.dojo.plumber.model.Hero;
import com.codenjoy.dojo.plumber.model.Player;
import com.codenjoy.dojo.services.EventListener;
import com.codenjoy.dojo.services.multiplayer.Single;
import com.codenjoy.dojo.services.printer.PrinterFactory;
import com.codenjoy.dojo.services.printer.PrinterFactoryImpl;
import com.codenjoy.dojo.utils.TestUtils;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class SmokeTest {

    @Test
    public void boardCreatedSuccessfully() {
        PrinterFactory factory = new PrinterFactoryImpl();

        BoardStartConfig config = new BoardStartConfig("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼     ☼" +
                "╠     ╣" +
                "☼     ☼" +
                "☼     ☼" +
                "☼☼☼☼☼☼☼");

        Field startField = new Field(config);
        Player player = new Player(Mockito.mock(EventListener.class), null);
        Single game = new Single(startField, player, factory);

        String expectedBoardStart = TestUtils.injectN("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼     ☼" +
                "╠     ╣" +
                "☼     ☼" +
                "☼     ☼" +
                "☼☼☼☼☼☼☼");
        assertEquals(expectedBoardStart, game.getBoardAsString());
    }

    @Test
    public void pipeAddToBoard() {
        PrinterFactory factory = new PrinterFactoryImpl();

        BoardStartConfig config = new BoardStartConfig("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼     ☼" +
                "╠     ╣" +
                "☼     ☼" +
                "☼     ☼" +
                "☼☼☼☼☼☼☼");

        Field field = new Field(config);
        Player player = new Player(Mockito.mock(EventListener.class), new Hero());
        Single game = new Single(field, player, factory);
        field.newGame(player);

        player.getHero().act(0, 1, 3);
        field.tick();

        String expectedBoardStart = TestUtils.injectN("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼     ☼" +
                "╠═    ╣" +
                "☼     ☼" +
                "☼     ☼" +
                "☼☼☼☼☼☼☼");
        assertEquals(expectedBoardStart, game.getBoardAsString());
    }
}
