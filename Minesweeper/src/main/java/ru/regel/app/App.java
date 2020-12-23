package ru.regel.app;

import ru.regel.model.Model;
import ru.regel.presenter.Presenter;
import ru.regel.view.View;

public class App {

    public static void main(String... args) {
        View view = new View();
        Model model = new Model();
        new Presenter(view, model);
    }
}
