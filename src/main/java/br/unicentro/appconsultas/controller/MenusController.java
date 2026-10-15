package br.unicentro.appconsultas.controller;

import java.io.IOException;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenusController {

    @FXML
    private void btnInicioOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Inicio.fxml","Inicio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMedicoOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medico.fxml","Medico");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnConsultasOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Consultas.fxml","Consultas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnReceitaOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Receita.fxml","Receita");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

