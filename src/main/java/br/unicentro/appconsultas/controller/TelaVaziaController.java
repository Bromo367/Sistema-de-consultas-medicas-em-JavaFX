package br.unicentro.appconsultas.controller;

import java.io.IOException;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import javafx.fxml.FXML;

public class TelaVaziaController {

    @FXML
    private void btnVoltarOnAction() {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Menus.fxml","Estudantes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
