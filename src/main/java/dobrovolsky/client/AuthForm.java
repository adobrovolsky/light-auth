package dobrovolsky.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class AuthForm extends Composite implements ClickHandler {

    private static final String ENDPOINT = "http://localhost:8080/lightauth";
    private static final String AUTH_METHOD = "/auth";

    private final PasswordTextBox passwordTextBox = new PasswordTextBox();
    private final TextBox usernameTextBox = new TextBox();

    public AuthForm() {
        usernameTextBox.setStyleName("input");
        passwordTextBox.setStyleName("input");
        Button sendBtn = new Button("Send");
        sendBtn.addClickHandler(this);
        Label formHeaderLabel = new Label("Login");
        formHeaderLabel.setStyleName("form-header");

        FlowPanel flowPanel = new FlowPanel();
        flowPanel.add(formHeaderLabel);
        flowPanel.add(usernameTextBox);
        flowPanel.add(passwordTextBox);
        flowPanel.add(sendBtn);

        FormPanel formPanel = new FormPanel();
        formPanel.add(flowPanel);

        initWidget(formPanel);
    }

    @Override
    public void onClick(ClickEvent event) {
        final String username = usernameTextBox.getText();
        final String password = passwordTextBox.getText();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", new JSONString(username));
        jsonObject.put("pass", new JSONString(password));

        final String url = ENDPOINT + AUTH_METHOD;
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-type", "application/json");
        try {
            builder.sendRequest(jsonObject.toString(), new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    JSONValue jsonValue = JSONParser.parseStrict(response.getText());
                    if (200 == response.getStatusCode()) {
                        JSONString message = jsonValue.isObject().get("token").isString();
                        Window.alert(message.stringValue());
                    } else if (401 == response.getStatusCode()) {
                        JSONString message = jsonValue.isObject().get("message").isString();
                        Window.alert(message.stringValue());
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    Window.alert(exception.getMessage());
                }
            });
        } catch (RequestException e) {
            Window.alert(e.getMessage());
        }
    }
}
