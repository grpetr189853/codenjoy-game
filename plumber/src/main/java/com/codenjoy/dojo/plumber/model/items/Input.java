package com.codenjoy.dojo.plumber.model.items;

import com.codenjoy.dojo.plumber.model.Elements;
import com.codenjoy.dojo.plumber.model.Player;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

public class Input extends PointImpl implements State<Elements, Player> {

    public Input(int x, int y) {
        super(x, y);
    }

    public Input(Point point) {
        super(point);
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.INPUT;
    }
}
