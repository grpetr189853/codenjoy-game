package com.codenjoy.dojo.plumber.model;

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


import com.codenjoy.dojo.plumber.model.exceptions.MoreThanSinglePlayerAddedException;
import com.codenjoy.dojo.plumber.model.items.Pipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.codenjoy.dojo.plumber.model.Elements.HORIZONTAL_PIPE;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FieldTest {

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @Mock
    private Hero heroMock;

    @Mock
    private BoardStartConfig boardStartConfig;

    @Mock
    private PipeConnectionsValidator validator;

    @InjectMocks
    private Field field;

    @Before
    public void setUp() {
        when(player1.getHero()).thenReturn(heroMock);
        when(validator.validatePipesConnectivity(any(), any())).thenReturn(true);
    }

    @Test
    public void onePlayerAddedToFieldSuccessfully() {
        field.newGame(player1);
    }

    @Test(expected = MoreThanSinglePlayerAddedException.class)
    public void cannotAddMoreThanOnePlayerToField() {
        field.newGame(player1);
        field.newGame(player2);
    }

    @Test
    public void pipeAddedSuccessfully() {
        Pipe pipe = new Pipe(HORIZONTAL_PIPE, 1, 3);

        assertTrue(field.addPipe(pipe));
    }

    @Test
    public void heroTickWhenFieldTick() {
        field.newGame(player1);

        field.tick();

        verify(heroMock).tick();
    }

    @Test
    public void newHeroCalledWhenNewGameCalled() {
        field.newGame(player1);

        verify(player1).newHero(field);
    }

    // pipe could be added only if it is connected to last pipe in already built pipeline

}