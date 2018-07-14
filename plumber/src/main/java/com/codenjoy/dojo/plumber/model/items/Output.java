package com.codenjoy.dojo.plumber.model.items;

import com.codenjoy.dojo.plumber.model.Elements;
import com.codenjoy.dojo.plumber.model.Player;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

public class Output extends PointImpl implements State<Elements, Player> {

    public Output(int x, int y) {
        super(x, y);
    }

    public Output(Point point) {
        super(point);
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.OUTPUT;
    }
}
