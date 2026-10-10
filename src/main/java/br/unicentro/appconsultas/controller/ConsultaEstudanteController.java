package br.unicentro.appconsultas.controller;
import java.io.IOException;
import java.sql.SQLException;

import br.unicentro.appconsultas.dao.Implementacoes.EstudanteDAO;
import br.unicentro.appconsultas.dao.Interfaces.IEstudante;
import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.model.Estudante;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class ConsultaEstudanteController {

    @FXML
    private TextField txtChave;

    @FXML
    private void btnConfOnAction() {

        String chaveTexto = txtChave.getText().trim();

        if (chaveTexto.isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Informe a chave do estudante!",
                    ButtonType.OK
            ).showAndWait();

            txtChave.requestFocus();
            return;
        }

        Integer chave = null;

        try {
            chave = Integer.parseInt(chaveTexto);

            if (chave <= 0) {
                new Alert(
                        Alert.AlertType.WARNING,
                        "Informe uma chave maior que zero!",
                        ButtonType.OK
                ).showAndWait();

                txtChave.requestFocus();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Informe uma chave válida!",
                    ButtonType.OK
            ).showAndWait();

            txtChave.clear();
            txtChave.requestFocus();
            return;
        }
        try{
            IEstudante estudanteDAO = new EstudanteDAO();
            Estudante estudante=estudanteDAO.buscaPorCodigo(chave);
            if(estudante!=null){
                new Alert(
                        Alert.AlertType.INFORMATION,
                        ("Estudante encontrado!\nNome: "+estudante.getNome()+"\nIdade: "+estudante.getIdade()),
                        ButtonType.OK
                ).showAndWait();
            }
            else{
                 new Alert(
                    Alert.AlertType.WARNING,
                    "Nenhum estudante cadastrado com a chave inserida!",
                    ButtonType.OK
                ).showAndWait();

                txtChave.clear();
                txtChave.requestFocus();
            }
            
            txtChave.clear();
            txtChave.requestFocus();
        }
        catch(SQLException e){
            new Alert(
                    Alert.AlertType.ERROR,
                    "Erro na consulta",
                    ButtonType.OK
            ).showAndWait();

            e.printStackTrace();
            txtChave.clear();
            txtChave.requestFocus();
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
