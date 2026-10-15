package br.unicentro.appconsultas.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Guarda o Stage principal para que ele possa ser reutilizado.
        GerenciadorTelas.setStage(stage);

        stage.setResizable(false);

        // Abre a primeira tela da aplicação.
        GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Inicio.fxml","Inicio");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

