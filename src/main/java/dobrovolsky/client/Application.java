package dobrovolsky.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {

    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("login-form");
        rootPanel.add(new AuthForm());
    }
}
