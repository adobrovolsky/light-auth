package dobrovolsky.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {

    public void onModuleLoad() {
        Label label = new Label("It's the LightAuth app");
        RootPanel rootPanel = RootPanel.get();
        rootPanel.add(label);
    }
}
