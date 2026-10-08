package br.unicentro.appestudantes.controller;

import java.io.IOException;

import br.unicentro.appestudantes.main.GerenciadorTelas;
import javafx.fxml.FXML;

public class TelaVaziaController {

    @FXML
    private void btnVoltarOnAction() {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appestudantes/view/Menus.fxml","Estudantes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
