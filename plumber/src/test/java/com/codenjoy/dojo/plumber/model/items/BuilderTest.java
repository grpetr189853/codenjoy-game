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
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codenjoy.dojo.plumber.model.items.Pipe.Builder.build;
import static com.codenjoy.dojo.plumber.model.items.Pipe.Builder.getTypeMapping;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BuilderTest {

    private final Player playerMock = null;
    private final Object alsoAtPointMock = null;

    @Test
    public void pipeWithGivenCoordinatesCreated() {
        int x = 2;
        int y = 3;

        Pipe pipe = build(1, x, y);

        assertEquals(x, pipe.getX());
        assertEquals(y, pipe.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionThrowForUnknownPipeCode() {
        build(6, 1, 1);
    }

    @Test
    @Parameters(method = "parameters")
    public void assertPipeForCodeCreated(int code, Elements pipeType){
        // when
        Pipe build = build(code, 1, 1);

        // then
        assertEquals(pipeType, build.state(playerMock, alsoAtPointMock));
    }

    private List<List<Object>> parameters() {
        final List<List<Object>> params = new ArrayList<>();
        getTypeMapping().forEach((k, v) -> params.add(Arrays.asList(k, v)));

        return params;
    }
}