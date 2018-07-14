package com.codenjoy.dojo.plumber.model;

import org.junit.Test;
import org.mockito.Mockito;

public class FieldTest {

    @Test
    public void onePlayerAddedToFieldSuccessfully() {
        Player player = Mockito.mock(Player.class);

        Field field = new Field(Mockito.mock(BoardStartConfig.class));
        field.newGame(player);
    }

    @Test(expected = MoreThanSinglePlayerAddedException.class)
    public void cannotAddMoreThanOnePlayerToField() {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        Field field = new Field(Mockito.mock(BoardStartConfig.class));
        field.newGame(player1);
        field.newGame(player2);
    }
}