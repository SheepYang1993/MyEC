package me.sheepyang.myec;

import me.sheepyang.latte.activities.ProxyActivity;
import me.sheepyang.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
