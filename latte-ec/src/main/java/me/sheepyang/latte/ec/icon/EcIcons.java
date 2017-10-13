package me.sheepyang.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by SheepYang on 2017-10-13.
 */

public enum EcIcons implements Icon {
    ali_1('\ue501'),
    ali_2('\ue676'),
    ali_3('\ue823'),
    ali_4('\ue826');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
