package br.unicentro.appconsultas.controller;

import java.io.IOException;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReceitaController {
    
    @FXML
    private void btnInicioOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Inicio.fxml","Inicio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMedicosOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medico.fxml","Medicos");
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
    private void btnReceitasOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Receita.fxml","Receitas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPacientesOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Paciente.fxml","Pacientes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMedicamentosOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medicamento.fxml","Medicamentos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEmitirReceitaOnAction(ActionEvent event) {
        // Implementar o cadastro depois.
    }

    @FXML
    private void btnBuscarReceitaOnAction(ActionEvent event) {
        // Implementar a busca depois.
    }

    @FXML
    private void btnAtualizarReceitaOnAction(ActionEvent event) {
        // Implementar a atualização depois.
    }

    @FXML
    private void btnRemoverReceitaOnAction(ActionEvent event) {
        // Implementar a remoção depois.
    }
}

