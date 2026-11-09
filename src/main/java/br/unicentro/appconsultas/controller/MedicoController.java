package br.unicentro.appconsultas.controller;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.service.MedicoService;
import br.unicentro.appconsultas.service.ServiceException;
import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.build.MedicoBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
public class MedicoController {

    @FXML private TextField txtNome, txtCRM, txtEspecializacao, txtTelefone;
    @FXML private Label lblConfirmarCadastro;
    @FXML private TextField txtBuscarCRM;
    @FXML private Label lblNomeBuscar, lblEspecializacaoBuscar, lblConfirmarBuscar, lblCRMBuscar, lblTelefoneBuscar;
    @FXML private TextField txtAtualizarNome, txtAtualizarCRM, txtAtualizarEspecializacao, txtAtualizarTelefone;
    @FXML private Label lblConfirmarAtualizar;
    @FXML private TextField txtRemoverCRM;
    @FXML private Label lblConfirmarRemover;

    @FXML
    private Label lblConfirmarListar;

    @FXML
    private Pane painelLista;

    private final MedicoService medicoService = new MedicoService();

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
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Receita.fxml","Receita");
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
    private void btnCadastrarMedicoOnAction(ActionEvent event) {
        String crm = txtCRM.getText().trim();
        String nome = txtNome.getText().trim();
        String esp = txtEspecializacao.getText().trim();
        String tel = txtTelefone.getText().trim();

        if (crm.isEmpty() || nome.isEmpty() || esp.isEmpty() || tel.isEmpty()) {
            mostrarMensagem(lblConfirmarCadastro, "Preencha todos os campos.", false);
            return;
        }

        try {
            Medico medico = new MedicoBuilder()
                                .crmMedico(crm)
                                .nome(nome)
                                .especializacao(esp)
                                .telefone(tel)
                                .build();
            medicoService.cadastrar(medico);
            mostrarMensagem(lblConfirmarCadastro, "Médico cadastrado com sucesso!", true);
            txtCRM.clear();
            txtNome.clear();
            txtEspecializacao.clear();
            txtTelefone.clear();
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarCadastro, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnBuscarMedicoOnAction(ActionEvent event) {
        String crm = txtBuscarCRM.getText().trim();
        
        if (crm.isEmpty()) {
            mostrarMensagem(lblConfirmarBuscar, "Digite o CRM para buscar.", false);
            limparBusca();
            return;
        }

        try {
            Medico m = medicoService.buscar(crm);

            lblCRMBuscar.setText("CRM: " + m.getCrmMedico());
            lblNomeBuscar.setText("Nome: " + m.getNome());
            lblEspecializacaoBuscar.setText("Especialização: " + m.getEspecializacao());
            lblTelefoneBuscar.setText("Telefone: " + m.getTelefone());
            mostrarMensagem(lblConfirmarBuscar, "Médico encontrado.", true);
            
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarBuscar, ex.getMessage(), false);
            limparBusca();
        }
    }

    @FXML
    private void btnAtualizarMedicoOnAction(ActionEvent event) {
        String crm = txtAtualizarCRM.getText().trim();
        String nome = txtAtualizarNome.getText().trim();
        String esp = txtAtualizarEspecializacao.getText().trim();
        String tel = txtAtualizarTelefone.getText().trim();

        if (crm.isEmpty() || nome.isEmpty() || esp.isEmpty() || tel.isEmpty()) {
            mostrarMensagem(lblConfirmarAtualizar, "Preencha todos os campos.", false);
            return;
        }

        try {
            Medico medico =     new MedicoBuilder()
                                .crmMedico(crm)
                                .nome(nome)
                                .especializacao(esp)
                                .telefone(tel)
                                .build();
            medicoService.atualizar(medico);
            mostrarMensagem(lblConfirmarAtualizar, "Médico atualizado com sucesso!", true);
            
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarAtualizar, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnRemoverMedicoOnAction(ActionEvent event) {
        String crm = txtRemoverCRM.getText().trim();
        
        if (crm.isEmpty()) {
            mostrarMensagem(lblConfirmarRemover, "Digite o CRM do médico.", false);
            return;
        }

        try {
            medicoService.remover(crm);
            mostrarMensagem(lblConfirmarRemover, "Médico removido com sucesso!", true);
            txtRemoverCRM.clear();
            
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarRemover, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnListarMedicoOnAction(ActionEvent event) {
        try{

            TableView<Medico> tabela = new TableView<>();
            
            TableColumn<Medico, String> colunaCRM = new TableColumn("CRM");
            colunaCRM.setCellValueFactory(new  PropertyValueFactory<>("crmMedico"));
    
            TableColumn<Medico, String> colunaNome = new TableColumn("Nome");
            colunaNome.setCellValueFactory(new  PropertyValueFactory<>("nome"));

            TableColumn<Medico, String> colunaEspecializacao = new TableColumn("Especializacao");
            colunaEspecializacao.setCellValueFactory(new  PropertyValueFactory<>("especializacao"));
    
            TableColumn<Medico, String> colunaTelefone = new TableColumn("Telefone");
            colunaTelefone.setCellValueFactory(new  PropertyValueFactory<>("telefone"));

    
            colunaCRM.setPrefWidth(160);
            colunaNome.setPrefWidth(160);
            colunaEspecializacao.setPrefWidth(160);
            colunaTelefone.setPrefWidth(160);
            

            tabela.getColumns().addAll(colunaCRM, colunaNome, colunaEspecializacao, colunaTelefone);

            tabela.getItems().setAll(medicoService.listarTodos());

            tabela.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
            );

            tabela.setPrefHeight(28 + (tabela.getItems().size() * 24) + 5);
            tabela.setMinHeight(28 + (tabela.getItems().size() * 24) + 5);
            tabela.setMaxHeight(8 + (tabela.getItems().size() * 24) + 5);

            painelLista.getChildren().clear();
            painelLista.getChildren().add(tabela);

            mostrarMensagem(lblConfirmarListar, "Listagem Realizada.", true);
        }
         catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarListar, ex.getMessage(), false);
        }
    }

    private void mostrarMensagem(Label label, String msg, boolean sucesso) {
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

    private void limparBusca() {
        lblCRMBuscar.setText("");
        lblNomeBuscar.setText("");
        lblEspecializacaoBuscar.setText("");
        lblTelefoneBuscar.setText("");
    }

}

