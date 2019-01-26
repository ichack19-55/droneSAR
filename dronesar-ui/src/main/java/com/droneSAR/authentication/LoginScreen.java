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

    private TextField campaign;
    private TextField username;
    private PasswordField password;
    private Button login;
    private AccessControl accessControl;

    private TextField crowdUsername;
    private PasswordField crowdPassword;
    private Button crowdLogin;
    private AccessControl crowdAccessControl;

    public LoginScreen() {
        accessControl = AccessControlFactory.anAccessControl().forAdmins();
        crowdAccessControl = AccessControlFactory.anAccessControl().forCrowds();

        buildUI();
        campaign.focus();
    }

    private void buildUI() {
        setSizeFull();
        setClassName("login-screen");

        // login form, centered in the available part of the screen
        Component loginForm = buildLoginForm();
        Component crowdLoginForm = buildCrowdLoginForm();

        // layout to center login form when there is sufficient screen space
        FlexLayout centeringLayout = new FlexLayout();
        centeringLayout.setSizeFull();
        centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        centeringLayout.setAlignItems(Alignment.CENTER);
        centeringLayout.add(loginForm);
        centeringLayout.add(crowdLoginForm);

        // information text about logging in
        Component loginInformation = buildLoginInformation();

        add(loginInformation);
        add(centeringLayout);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.setWidth("310px");

        loginForm.addFormItem(campaign = new TextField(), "Campaign");
        campaign.setWidth("15em");
        campaign.setValue("EmilianoSala");

        loginForm.addFormItem(username = new TextField(), "Username");
        username.setWidth("15em");
        username.setValue("admin");

        loginForm.add(new Html("<br/>"));

        loginForm.addFormItem(password = new PasswordField(), "Password");
        password.setWidth("15em");

        HorizontalLayout buttons = new HorizontalLayout();
        loginForm.add(new Html("<br/>"));
        loginForm.add(buttons);

        buttons.add(login = new Button("Login"));
        login.addClickListener(event -> login());
        login.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        return loginForm;
    }

    private Component buildCrowdLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.setWidth("310px");

        loginForm.addFormItem(crowdUsername = new TextField(), "Username");
        crowdUsername.setWidth("15em");
        crowdUsername.setValue("matt");

        loginForm.add(new Html("<br/>"));

        loginForm.addFormItem(crowdPassword = new PasswordField(), "Password");
        crowdPassword.setWidth("15em");
        crowdPassword.setValue("iscool");

        HorizontalLayout buttons = new HorizontalLayout();
        loginForm.add(new Html("<br/>"));
        loginForm.add(buttons);

        buttons.add(crowdLogin = new Button("Login"));
        crowdLogin.addClickListener(event -> crowdLogin());
        crowdLogin.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        return loginForm;
    }

    private Component buildLoginInformation() {
        VerticalLayout loginInformation = new VerticalLayout();
        loginInformation.setClassName("login-information");

        H1 loginInfoHeader = new H1("DroneSAR");
        Span loginInfoText = new Span("Log in using the left hand form if you are an "
            + "administrator to a campaign. Otherwise use the right hand form.");
        loginInformation.add(loginInfoHeader);
        loginInformation.add(loginInfoText);

        return loginInformation;
    }

    private void login() {
        login.setEnabled(false);
        try {
            if (accessControl.signIn(username.getValue(), password.getValue())) {
                getUI().get().navigate("");
            } else {
                showNotification(new Notification("Login failed."));
                campaign.focus();
            }
        } finally {
            login.setEnabled(true);
        }
    }

    private void crowdLogin() {
        crowdLogin.setEnabled(false);
        try {
            if (crowdAccessControl.signIn(crowdUsername.getValue(), crowdPassword.getValue())) {
                getUI().get().navigate("");
            } else {
                showNotification(new Notification("Login failed."));
                campaign.focus();
            }
        } finally {
            crowdLogin.setEnabled(true);
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDuration(2000);
        notification.open();
    }
}
