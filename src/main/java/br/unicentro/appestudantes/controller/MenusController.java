package br.unicentro.appestudantes.controller;

import java.io.IOException;
import br.unicentro.appestudantes.main.GerenciadorTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenusController {

    @FXML
    private void incluiOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appestudantes/view/IncluiEstudante.fxml","Incluir Estudante");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void alteraOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appestudantes/view/TelaVazia.fxml","Alterar Estudante");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void consultaOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appestudantes/view/ConsultaEstudante.fxml","Consultar Estudante");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void telaVaziaOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appestudantes/view/TelaVazia.fxml","Professor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

