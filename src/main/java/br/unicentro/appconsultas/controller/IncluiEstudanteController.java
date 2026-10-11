package br.unicentro.appconsultas.controller;
import java.io.IOException;
import java.sql.SQLException;

import br.unicentro.appconsultas.dao.implementacoes.EstudanteDAO;
import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.model.Estudante;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class IncluiEstudanteController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private void btnConfOnAction() {

        String nome = txtNome.getText().trim();
        String idadeTexto = txtIdade.getText().trim();

        if (nome.isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Informe o nome do estudante!",
                    ButtonType.OK
            ).showAndWait();

            txtNome.requestFocus();
            return;
        }

        Integer idade = null;

        if (!idadeTexto.isEmpty()) {
            try {
                idade = Integer.parseInt(idadeTexto);

                if (idade <= 0) {
                    new Alert(
                            Alert.AlertType.WARNING,
                            "Informe uma idade maior que zero!",
                            ButtonType.OK
                    ).showAndWait();

                    txtIdade.requestFocus();
                    return;
                }

            } catch (NumberFormatException e) {
                new Alert(
                        Alert.AlertType.WARNING,
                        "Informe uma idade válida!",
                        ButtonType.OK
                ).showAndWait();

                txtIdade.requestFocus();
                return;
            }
        }

        Estudante estudante = new Estudante();
        estudante.setNome(nome);
        estudante.setIdade(idade);

        EstudanteDAO estudanteDAO = new EstudanteDAO();

        try {
            estudanteDAO.inserir(estudante);

            new Alert(
                    Alert.AlertType.INFORMATION,
                    "Estudante cadastrado com sucesso!",
                    ButtonType.OK
            ).showAndWait();
            txtNome.clear();
            txtIdade.clear();
            txtNome.requestFocus();
        } catch (SQLException e) {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Erro ao cadastrar estudante!",
                    ButtonType.OK
            ).showAndWait();

            e.printStackTrace();
        }
    }

    @FXML
    private void btnVoltarOnAction() {
        try {
            // retorna para a tela de menu utilizando o mesmo Stage.
            GerenciadorTelas.trocarTela("/br/unicentro/appestudantes/view/Menus.fxml","Estudantes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
