package br.unicentro.appconsultas.controller;

import java.io.IOException;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.model.Medicamento;
import br.unicentro.appconsultas.service.MedicamentoService;
import br.unicentro.appconsultas.service.ServiceException;

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

    private final MedicamentoService medicamentoService = new MedicamentoService();

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
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medico.fxml","Medicos");
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
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medicamento.fxml","Medicamentos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML 
    private void btnCadastrarMedicamentoOnAction(ActionEvent event) {
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

        int id=0;
        try {
            id = Integer.parseInt(idTexto);

        } catch (NumberFormatException ex) {
            mostraMensagem(
                lblConfirmarCadastro,
                "O código deve ser um número inteiro.",
                false
            );
        } 
        Medicamento medicamento = new Medicamento(id, nome);
        try{
            medicamentoService.cadastrar(medicamento);
            
            mostraMensagem(
                lblConfirmarCadastro,
                "Medicamento cadastrado com sucesso!",
                true
            );
        }
        catch(ServiceException ex){
            mostraMensagem(
            lblConfirmarCadastro,
            ex.getMessage(),
            false
            );
        }

        txtMedicamentoId.clear();
        txtNome.clear();
    }

    @FXML 
    private void btnBuscarMedicamentoOnAction(ActionEvent event) {
        String idTexto = txtBuscarMedicamentoId.getText().trim(); 
        
        if (idTexto.isEmpty()) { 
            mostraMensagem(lblConfirmarBuscar, "Digite o código do medicamento.", false); 
            lblNomeBuscar.setText(""); 
            return; 
        }
        
        try { 
            int id = Integer.parseInt(idTexto); 
            Medicamento m = medicamentoService.buscar(id);
            mostraMensagem(lblConfirmarBuscar, "Medicamento encontrado.", true); 
            lblNomeBuscar.setText("Código: " + m.getMedicamentoId() + " | Nome: " + m.getNome());    
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarBuscar, "O código deve ser um número inteiro.", false); 
        }
        catch (ServiceException ex){
            lblNomeBuscar.setText(""); 
            mostraMensagem(lblConfirmarBuscar, ex.getMessage(), false);
        }
    }

    @FXML 
    private void btnAtualizarMedicamentoOnAction(ActionEvent event) {
        String idTexto = txtAtualizarMedicamentoId.getText().trim();
        String nome = txtAtualizarNome.getText().trim(); 
        
        if (idTexto.isEmpty() || nome.isEmpty()) { 
            mostraMensagem(lblConfirmarAtualizar, "Preencha o código e o nome.", false); 
            return; 
        }
        
        try { 
            int id = Integer.parseInt(idTexto); 
            Medicamento medicamento = new Medicamento(id, nome);
            medicamentoService.atualizar(medicamento);
            mostraMensagem(lblConfirmarAtualizar, "Medicamento atualizado com sucesso!", true); 
            
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarAtualizar, "O código deve ser um número inteiro.", false); 
        } catch (ServiceException ex) { 
            mostraMensagem(lblConfirmarAtualizar, ex.getMessage(), false); 
        }
    }

    @FXML 
    private void btnRemoverMedicamentoOnAction(ActionEvent event) {
        String idTexto = txtRemoverMedicamentoId.getText().trim(); 
        
        if (idTexto.isEmpty()) { 
            mostraMensagem(lblConfirmarRemover, "Digite o código do medicamento.", false); 
            return; 
        }
        
        try { 
            int id = Integer.parseInt(idTexto);
            medicamentoService.remover(id);
            mostraMensagem(lblConfirmarRemover, "Medicamento removido com sucesso!", true); 
            txtRemoverMedicamentoId.clear(); 
            
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarRemover, "O código deve ser um número inteiro.", false); 
        } catch (ServiceException ex) { 
            mostraMensagem(lblConfirmarRemover, ex.getMessage(), false); 
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

            tabela.getItems().setAll(medicamentoService.listarTodos());

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
         catch (ServiceException ex) {
            ex.printStackTrace();
            mostraMensagem(lblConfirmarListar, ex.getMessage(), false);
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

