package br.unicentro.appconsultas.controller;

import java.io.IOException;
import java.sql.SQLException;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.dao.implementacoes.MedicamentoDAO;
import br.unicentro.appconsultas.model.Medicamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class MedicamentoController {

    @FXML private TextField txtNome, txtMedicamentoId;
    @FXML private Label lblConfirmarCadastro;
    @FXML private TextField txtBuscarMedicamentoId;
    @FXML private Label lblNomeBuscar, lblConfirmarBuscar;
    @FXML private TextField txtAtualizarNome, txtAtualizarMedicamentoId;
    @FXML private Label lblConfirmarAtualizar;
    @FXML private TextField txtRemoverMedicamentoId;
    @FXML private Label lblConfirmarRemover;

    @FXML private Label lblConfirmarListar;
    @FXML private Pane painelLista;

    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

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
    private void btnCadastrarMedicamentoOnAction(ActionEvent e) {
        String idTexto = txtMedicamentoId.getText().trim();
        String nome = txtNome.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            mostraMensagem(
                lblConfirmarCadastro,
                "Preencha o código e o nome do medicamento.",
                false
            );
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            if (id <= 0) {
                mostraMensagem(
                    lblConfirmarCadastro,
                    "O código deve ser um número positivo.",
                    false
                );
                return;
            }

            if (medicamentoDAO.buscaPorCodigo(id) != null) {
                mostraMensagem(
                    lblConfirmarCadastro,
                    "Já existe um medicamento com esse código.",
                    false
                );
                return;
            }

            medicamentoDAO.insere(new Medicamento(id, nome));

            mostraMensagem(
                lblConfirmarCadastro,
                "Medicamento cadastrado com sucesso!",
                true
            );

            txtMedicamentoId.clear();
            txtNome.clear();

        } catch (NumberFormatException ex) {
            mostraMensagem(
                lblConfirmarCadastro,
                "O código deve ser um número inteiro.",
                false
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            mostraMensagem(
                lblConfirmarCadastro,
                "Erro ao cadastrar medicamento. Verifique o banco de dados.",
                false
            );
        }
    }

    @FXML 
    private void btnBuscarMedicamentoOnAction(ActionEvent e) {
        String idTexto = txtBuscarMedicamentoId.getText().trim(); 
        
        if (idTexto.isEmpty()) { 
            mostraMensagem(lblConfirmarBuscar, "Digite o código do medicamento.", false); 
            lblNomeBuscar.setText(""); 
            return; 
        }
        
        try { 
            int id = Integer.parseInt(idTexto); 
            Medicamento m = medicamentoDAO.buscaPorCodigo(id); 
            
            if (m == null) { 
                mostraMensagem(lblConfirmarBuscar, "Medicamento não encontrado.", false); 
                lblNomeBuscar.setText(""); 
                return; 
            } 
            
            lblNomeBuscar.setText("Código: " + m.getMedicamentoId() + " | Nome: " + m.getNome()); 
            mostraMensagem(lblConfirmarBuscar, "Medicamento encontrado.", true); 
            
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarBuscar, "O código deve ser um número inteiro.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarBuscar, "Erro ao consultar o banco de dados.", false); 
        }
    }

    @FXML 
    private void btnAtualizarMedicamentoOnAction(ActionEvent e) {
        String idTexto = txtAtualizarMedicamentoId.getText().trim();
        String nome = txtAtualizarNome.getText().trim(); 
        
        if (idTexto.isEmpty() || nome.isEmpty()) { 
            mostraMensagem(lblConfirmarAtualizar, "Preencha o código e o nome.", false); 
            return; 
        }
        
        try { 
            int id = Integer.parseInt(idTexto); 
            
            if (medicamentoDAO.buscaPorCodigo(id) == null) { 
                mostraMensagem(lblConfirmarAtualizar, "Medicamento não encontrado.", false); 
                return; 
            } 
            
            medicamentoDAO.atualiza(new Medicamento(id, nome)); 
            mostraMensagem(lblConfirmarAtualizar, "Medicamento atualizado com sucesso!", true); 
            
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarAtualizar, "O código deve ser um número inteiro.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarAtualizar, "Erro ao atualizar medicamento.", false); 
        }
    }

    @FXML 
    private void btnRemoverMedicamentoOnAction(ActionEvent e) {
        String idTexto = txtRemoverMedicamentoId.getText().trim(); 
        
        if (idTexto.isEmpty()) { 
            mostraMensagem(lblConfirmarRemover, "Digite o código do medicamento.", false); 
            return; 
        }
        
        try { 
            int id = Integer.parseInt(idTexto); 
            Medicamento m = medicamentoDAO.buscaPorCodigo(id); 
            
            if (m == null) { 
                mostraMensagem(lblConfirmarRemover, "Medicamento não encontrado.", false); 
                return; 
            } 
            
            medicamentoDAO.remove(m); 
            mostraMensagem(lblConfirmarRemover, "Medicamento removido com sucesso!", true); 
            txtRemoverMedicamentoId.clear(); 
            
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarRemover, "O código deve ser um número inteiro.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarRemover, "Erro ao remover. Verifique se há receitas associadas.", false); 
        }
    }

    @FXML
    private void btnListarMedicamentoOnAction(ActionEvent event) {
        try{

            TableView<Medicamento> tabela = new TableView<>();
            
            TableColumn<Medicamento, Integer> colunaMedicamentoId = new TableColumn("Identificador");
            colunaMedicamentoId.setCellValueFactory(new  PropertyValueFactory<>("medicamentoId"));
    
            TableColumn<Medicamento, String> colunaNome = new TableColumn("Nome");
            colunaNome.setCellValueFactory(new  PropertyValueFactory<>("nome"));

            colunaMedicamentoId.setPrefWidth(160);
            colunaNome.setPrefWidth(160);


            tabela.getColumns().addAll(colunaMedicamentoId, colunaNome);

            tabela.getItems().setAll(medicamentoDAO.listaTodos());

            tabela.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
            );

            tabela.setPrefHeight(28 + (tabela.getItems().size() * 24) + 5);
            tabela.setMinHeight(28 + (tabela.getItems().size() * 24) + 5);
            tabela.setMaxHeight(8 + (tabela.getItems().size() * 24) + 5);

            painelLista.getChildren().clear();
            painelLista.getChildren().add(tabela);

            mostraMensagem(lblConfirmarListar, "Listagem realizada.", true);
        }
         catch (SQLException e) {
            e.printStackTrace();
            mostraMensagem(lblConfirmarListar, "Erro ao Listar.", false);
        }
    }

    private void mostraMensagem(Label label, String msg, boolean sucesso) {
        if (label == null) {
            return;
        }

        label.setText(msg);

        if(sucesso){
            label.setStyle("-fx-text-fill: #18733b;");
        }
        else{
            label.setStyle("-fx-text-fill: #E53935;");
        }
    }
}

