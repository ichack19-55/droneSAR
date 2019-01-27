package com.droneSAR.authentication;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * UI content when the user is not logged in yet.
 */
@Route("Login")
@PageTitle("Login")
@HtmlImport("css/shared-styles.html")
public class LoginScreen extends FlexLayout {

    private TextField username;
    private PasswordField password;
    private Button login;
    private Button signup;
    private AccessControl userAccessControl;

    public LoginScreen() {
        userAccessControl = AccessControlFactory.getUAC();
        buildUI();
        username.focus();
    }

    private void buildUI() {
        setSizeFull();
        setClassName("login-screen");

        // login form, centered in the available part of the screen
        Component crowdLoginForm = buildCrowdLoginForm();

        // layout to center login form when there is sufficient screen space
        FlexLayout centeringLayout = new FlexLayout();
        centeringLayout.setSizeFull();
        centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        centeringLayout.setAlignItems(Alignment.CENTER);
        centeringLayout.add(crowdLoginForm);

        // information text about logging in
        Component loginInformation = buildLoginInformation();

        add(loginInformation);
        add(centeringLayout);
    }

    private Component buildCrowdLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.setWidth("310px");

        loginForm.addFormItem(username = new TextField(), "Username");
        username.setWidth("15em");
        username.setValue("matt");

        loginForm.add(new Html("<br/>"));

        loginForm.addFormItem(password = new PasswordField(), "Password");
        password.setWidth("15em");
        password.setValue("iscool");

        HorizontalLayout buttons = new HorizontalLayout();
        loginForm.add(new Html("<br/>"));
        loginForm.add(buttons);

        buttons.add(signup = new Button("Sign Up"));
        signup.addClickListener(event -> signup());
        signup.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        buttons.add(login = new Button("Login"));
        login.addClickListener(event -> login());
        login.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        return loginForm;
    }

    private Component buildLoginInformation() {
        VerticalLayout loginInformation = new VerticalLayout();
        loginInformation.setClassName("login-information");

        H1 loginInfoHeader = new H1("DroneSAR");
        Span loginInfoText = new Span("Crowdsourced search and rescue "
            + "enabling rescue crews around the world to save lives quicker.");
        loginInformation.add(loginInfoHeader);
        loginInformation.add(loginInfoText);

        return loginInformation;
    }

    private void signup() {
        signup.setEnabled(false);
        try {
            if (userAccessControl.createUser(username.getValue(), password.getValue())) {
                login();
            } else {
                showNotification(new Notification("Username already exists."));
                username.focus();
            }
        } finally {
            signup.setEnabled(true);
        }
    }

    private void login() {
        login.setEnabled(false);
        try {
            if (userAccessControl.signIn(username.getValue(), password.getValue())) {
                getUI().get().navigate("MyCampaigns");
            } else {
                showNotification(new Notification("Login failed."));
                username.focus();
            }
        } finally {
            login.setEnabled(true);
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDuration(2000);
        notification.open();
    }
}
