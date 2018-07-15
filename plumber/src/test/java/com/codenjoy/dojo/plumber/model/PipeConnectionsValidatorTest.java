package com.codenjoy.dojo.plumber.model;

import com.codenjoy.dojo.plumber.model.exceptions.CannotAddPipeException;
import com.codenjoy.dojo.plumber.model.items.Input;
import com.codenjoy.dojo.plumber.model.items.Pipe;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.LinkedHashSet;

import static com.codenjoy.dojo.plumber.model.Elements.HORIZONTAL_PIPE;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class PipeConnectionsValidatorTest {

    private Collection<Pipe> pipes = new LinkedHashSet<>();

    private int size = 42;

    private Input input = new Input(0, size / 2);

    private final PipeConnectionsValidator validator = new PipeConnectionsValidator();

    @Before
    public void setUp() {
        validator.init(size, input);
    }

    @Test(expected = CannotAddPipeException.class)
    public void cannotAddPipeBelowOfTheField() {
        validatePipeAtPosition(size / 2, -1);
    }

    @Test(expected = CannotAddPipeException.class)
    public void cannotAddPipeAboveOfTheField() {
        validatePipeAtPosition(size / 2, size + 1);
    }

    @Test(expected = CannotAddPipeException.class)
    public void cannotAddPipeLeftToTheField() {
        validatePipeAtPosition(-1, size / 2);
    }

    @Test(expected = CannotAddPipeException.class)
    public void cannotAddPipeRightToTheField() {
        validatePipeAtPosition(size + 1, size / 2);
    }

    @Test(expected = CannotAddPipeException.class)
    public void cannotAddPipeTwiceInTheSamePosition() {
        final Pipe pipeToAdd = new Pipe(HORIZONTAL_PIPE, 3, 3);

        assertTrue(validator.validatePipesConnectivity(pipeToAdd, pipes));
        assertTrue(pipes.add(pipeToAdd));
        validator.validatePipesConnectivity(pipeToAdd, pipes);
    }

    @Test
    @Parameters({"═", "╗", "╝"})
    public void pipeCouldBeConnectedToInput(Character ch) {
        Pipe pipeToAdd = new Pipe(Elements.valueOf(ch), input.getX() + 1, input.getY());

        assertTrue(validator.validatePipesConnectivity(pipeToAdd, pipes));
    }

    @Test(expected = CannotAddPipeException.class)
    @Parameters({"║", "╔", "╚"})
    public void pipeCouldNotBeConnectedToInput(Character ch) {
        Pipe pipeToAdd = new Pipe(Elements.valueOf(ch), input.getX() + 1, input.getY());

        validator.validatePipesConnectivity(pipeToAdd, pipes);
    }

    private void validatePipeAtPosition(int x, int y) {
        assertTrue(validator.validatePipesConnectivity(new Pipe(HORIZONTAL_PIPE, x, y), pipes));
    }
}