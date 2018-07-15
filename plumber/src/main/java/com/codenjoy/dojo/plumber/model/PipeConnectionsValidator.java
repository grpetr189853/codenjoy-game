package com.codenjoy.dojo.plumber.model;

import com.codenjoy.dojo.plumber.model.exceptions.CannotAddPipeException;
import com.codenjoy.dojo.plumber.model.items.Input;
import com.codenjoy.dojo.plumber.model.items.Pipe;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import static com.codenjoy.dojo.plumber.model.Elements.*;

public class PipeConnectionsValidator {
    private Integer boardSize;
    private Input boardInput;
    private final Set<Elements> couldBeConnectedToInput;

    public PipeConnectionsValidator() {
        this.couldBeConnectedToInput = EnumSet.of(HORIZONTAL_PIPE, UP_RIGHT_PIPE, DOWN_RIGHT_PIPE);
    }

    public void init(int boardSize, Input boardInput) {
        this.boardSize = boardSize;
        this.boardInput = boardInput;
    }

    public boolean validatePipesConnectivity(Pipe pipeToAdd, Collection<Pipe> pipes) {
        if (this.boardSize == null || this.boardInput == null) {
            throw new IllegalStateException("Validator has not been initialized. Please ensure init() called before very first validatePipesConnectivity()");
        }
        if (pipeToAdd.getX() < 0 || pipeToAdd.getY() < 0 || pipeToAdd.getX() >= this.boardSize || pipeToAdd.getY() >= this.boardSize) {
            throw new CannotAddPipeException("Impossible to add pipe " + pipeToAdd + " ouside of field");
        }

        if (pipes.contains(pipeToAdd)) {
            throw new CannotAddPipeException("Position already contains pipe");
        }

        if (cannotBeConnectedToInput(pipeToAdd, pipes)) {
            throw new CannotAddPipeException(pipeToAdd + " cannot be connected to Input");
        }


        return true;
    }

    private boolean cannotBeConnectedToInput(Pipe pipeToAdd, Collection<Pipe> pipes) {
        return pipes.isEmpty()
                && positionToTheRightFromInput(pipeToAdd, boardInput)
                && !couldBeConnectedToInput.contains(pipeToAdd.state(null, null));
    }

    private boolean positionToTheRightFromInput(Pipe pipeToAdd, Input boardInput) {
        return (pipeToAdd.getX() == boardInput.getX() + 1) && (pipeToAdd.getY() == boardInput.getY());
    }

    private boolean isVeryFirstPipeToAdd(Collection<Pipe> pipes) {
        return pipes.isEmpty();
    }
}
