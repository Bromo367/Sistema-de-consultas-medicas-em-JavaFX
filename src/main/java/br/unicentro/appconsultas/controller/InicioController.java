package br.unicentro.appconsultas.controller;

import java.io.IOException;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.service.BancoService;
import br.unicentro.appconsultas.service.ServiceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class InicioController {

    private final BancoService bancoService = new BancoService();

    @FXML
    private void btnInicioOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Inicio.fxml","Inicio");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnMedicosOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medico.fxml","Medico");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnConsultasOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Consultas.fxml","Consultas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnReceitasOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Receita.fxml","Receitas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnPacientesOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Paciente.fxml","Pacientes");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnMedicamentosOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medicamento.fxml","Medicamento");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnNovoPacienteOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Paciente.fxml","Pacientes");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnNovoMedicoOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medico.fxml","Medicos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnNovaConsultaOnAction(ActionEvent event) {
        try {
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Consultas.fxml","Consultas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnApagarTudoOnAction(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setTitle("Confirmar exclusão");
        alerta.setHeaderText("Deseja realmente apagar todos os dados?");
        
        alerta.showAndWait().ifPresent(escolha -> {

            if (escolha == ButtonType.OK) {
                try {
                    bancoService.apagarTudo();

                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("Concluido");
                    sucesso.setHeaderText("Dados apagados");
                    sucesso.setContentText("Todos os dados foram apagados.");
                    sucesso.showAndWait();

                } catch (ServiceException ex) {
                    ex.printStackTrace();

                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setTitle("Erro");
                    erro.setHeaderText("Ocorreu um erro ao apagar.");
                    erro.setContentText(ex.getMessage());
                    erro.showAndWait();
                }
            }

        });
    }
}

