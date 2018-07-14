package com.codenjoy.dojo.plumber.model.items;

import com.codenjoy.dojo.services.printer.CharElements;

public enum Pipes implements CharElements {
    DOWN_LEFT('╚'),
    DOWN_RIGHT('╝'),
    HORIZONTAL('═'),
    UP_LEFT('╔'),
    UP_RIGHT('╗'),
    VERTICAL('║');

    final char ch;

    Pipes(char c) {
        this.ch = c;
    }

    @Override
    public char ch() {
        return this.ch;
    }

    public static Pipes valueOf(char ch) {
        for (Pipes p : Pipes.values()) {
            if (p.ch == ch) {
                return p;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }
}
