package main.application;

import main.ui.app.SystemUI;

public class Sistema {
    private final SystemUI ui;

    public Sistema() {
        this.ui = new SystemUI();
    }

    public void iniciar() {
        ui.login();
    }
}
