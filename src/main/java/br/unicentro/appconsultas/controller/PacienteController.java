package br.unicentro.appconsultas.controller;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.model.Paciente;
import br.unicentro.appconsultas.service.PacienteService;
import br.unicentro.appconsultas.service.ServiceException;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class PacienteController {

    // Cadastrar
    @FXML private TextField txtNome;
    @FXML private TextField txtCPF;
    @FXML private TextField txtIdade;
    @FXML private TextField txtPeso;
    @FXML private TextField txtAltura;
    @FXML private TextField txtTelefone;
    @FXML private Label lblConfirmarCadastro;

    // Buscar
    @FXML private TextField txtBuscarCPF;
    @FXML private Label lblConfirmarBuscar;
    @FXML private Label lblCPFBuscar;
    @FXML private Label lblNomeBuscar;
    @FXML private Label lblIdadeBuscar;
    @FXML private Label lblPesoBuscar;
    @FXML private Label lblAlturaBuscar;
    @FXML private Label lblTelefoneBuscar;

    // Atualizar
    @FXML private TextField txtAtualizarNome;
    @FXML private TextField txtAtualizarCPF;
    @FXML private TextField txtAtualizarIdade;
    @FXML private TextField txtAtualizarPeso;
    @FXML private TextField txtAtualizarAltura;
    @FXML private TextField txtAtualizarTelefone;
    @FXML private Label lblConfirmarAtualizar;

    // Remover
    @FXML private TextField txtRemoverCPF;
    @FXML private Label lblConfirmarRemover;

    @FXML private Label lblConfirmarListar;
    @FXML private Pane painelLista;

    private final PacienteService pacienteService = new PacienteService();

    
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
    private void btnCadastrarPacienteOnAction(ActionEvent event) {
        String cpf = txtCPF.getText().trim();
        String nome = txtNome.getText().trim();
        String idadeTexto = txtIdade.getText().trim();
        String pesoTexto = txtPeso.getText().trim();
        String alturaTexto = txtAltura.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (cpf.isEmpty() || nome.isEmpty() || idadeTexto.isEmpty() || pesoTexto.isEmpty() || alturaTexto.isEmpty() || telefone.isEmpty()) {
            mostrarMensagem(lblConfirmarCadastro,
                    "Preencha todos os campos.", false);
            return;
        }
        try {
            int idade = Integer.parseInt(idadeTexto);
            double peso = Double.parseDouble(pesoTexto);
            double altura = Double.parseDouble(alturaTexto);

            Paciente paciente = new Paciente(cpf, nome, idade, peso, altura, telefone);
            pacienteService.cadastrar(paciente);
            mostrarMensagem(lblConfirmarCadastro, "Paciente cadastrado com sucesso!", true);
            limparCadastro();

        } catch (NumberFormatException ex) {
            mostrarMensagem(lblConfirmarCadastro, "Verifique o tipo dos valores.", false);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarCadastro, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnBuscarPacienteOnAction(ActionEvent event) {
        String cpf = txtBuscarCPF.getText().trim();
        
        if (cpf.isEmpty()) {
            mostrarMensagem(lblConfirmarBuscar, "Preencha o campo CPF para buscar.", false);
            limparResultadoBusca();
            return;
        }
        try {
            Paciente paciente = pacienteService.buscar(cpf);

            lblCPFBuscar.setText("CPF: " + paciente.getCpfPaciente());
            lblNomeBuscar.setText("Nome: " + paciente.getNome());
            lblIdadeBuscar.setText("Idade: " + paciente.getIdade());
            lblPesoBuscar.setText("Peso: " + paciente.getPeso());
            lblAlturaBuscar.setText("Altura: " + paciente.getAltura());
            lblTelefoneBuscar.setText("Telefone: " + paciente.getTelefone());
            
            mostrarMensagem(lblConfirmarBuscar, "Paciente encontrado.", true);

            
        } catch (ServiceException ex) {
            ex.printStackTrace();
            limparResultadoBusca();
            mostrarMensagem(lblConfirmarBuscar, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnAtualizarPacienteOnAction(ActionEvent event) {
        String cpf = txtAtualizarCPF.getText().trim();
        String nome = txtAtualizarNome.getText().trim();
        String idadeTexto = txtAtualizarIdade.getText().trim();
        String pesoTexto = txtAtualizarPeso.getText().trim();
        String alturaTexto = txtAtualizarAltura.getText().trim();
        String telefone = txtAtualizarTelefone.getText().trim();

        if (cpf.isEmpty() || nome.isEmpty() || idadeTexto.isEmpty()
                || pesoTexto.isEmpty() || alturaTexto.isEmpty() || telefone.isEmpty()) {
            mostrarMensagem(lblConfirmarAtualizar,
                    "Preencha todos os campos.", false);
            return;
        }

        try {
            int idade = Integer.parseInt(idadeTexto);
            double peso = Double.parseDouble(pesoTexto);
            double altura = Double.parseDouble(alturaTexto);

            Paciente paciente = new Paciente(cpf, nome, idade, peso, altura, telefone);
            pacienteService.atualizar(paciente);

            mostrarMensagem(lblConfirmarAtualizar, "Paciente atualizado com sucesso!", true);
        
        } catch (NumberFormatException ex) {
            mostrarMensagem(lblConfirmarAtualizar,
                    "Verifique os tipos dos valores.", false);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarAtualizar, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnRemoverPacienteOnAction(ActionEvent event) {
        String cpf = txtRemoverCPF.getText().trim();
        if (cpf.isEmpty()) {
            mostrarMensagem(lblConfirmarRemover, "Digite o CPF do paciente.", false);
            return;
        }

        try {
            pacienteService.remover(cpf);
            mostrarMensagem(lblConfirmarRemover, "Paciente removido com sucesso!", true);
            txtRemoverCPF.clear();
        } catch (ServiceException ex) {
            ex.printStackTrace();
            mostrarMensagem(lblConfirmarRemover, ex.getMessage(), false);
        }
    }

    @FXML
    private void btnListarPacienteOnAction(ActionEvent event) {
        try{

            TableView<Paciente> tabela = new TableView<>();
            
            TableColumn<Paciente, String> colunaCPF = new TableColumn("CPF");
            colunaCPF.setCellValueFactory(new  PropertyValueFactory<>("cpfPaciente"));
    
            TableColumn<Paciente, String> colunaNome = new TableColumn("Nome");
            colunaNome.setCellValueFactory(new  PropertyValueFactory<>("nome"));

            TableColumn<Paciente, Integer> colunaIdade = new TableColumn("Idade");
            colunaIdade.setCellValueFactory(new  PropertyValueFactory<>("idade"));
    
            TableColumn<Paciente, Double> colunaPeso = new TableColumn("Peso");
            colunaPeso.setCellValueFactory(new  PropertyValueFactory<>("peso"));

            TableColumn<Paciente, Double> colunaAltura = new TableColumn("Altura");
            colunaAltura.setCellValueFactory(new  PropertyValueFactory<>("altura"));
    
            colunaCPF.setPrefWidth(160);
            colunaNome.setPrefWidth(160);
            colunaIdade.setPrefWidth(160);
            colunaPeso.setPrefWidth(160);
            colunaAltura.setPrefWidth(160);


            tabela.getColumns().addAll(colunaCPF, colunaNome, colunaIdade, colunaPeso, colunaAltura);

            tabela.getItems().setAll(pacienteService.listarTodos());

            tabela.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
            );

            tabela.setPrefHeight(28 + (tabela.getItems().size() * 24) + 5);
            tabela.setMinHeight(28 + (tabela.getItems().size() * 24) + 5);
            tabela.setMaxHeight(8 + (tabela.getItems().size() * 24) + 5);

            painelLista.getChildren().clear();
            painelLista.getChildren().add(tabela);

            mostrarMensagem(lblConfirmarListar, "Listagem realizada.", true);
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

    private void limparCadastro() {
        txtNome.clear();
        txtCPF.clear();
        txtIdade.clear();
        txtPeso.clear();
        txtAltura.clear();
        txtTelefone.clear();
    }

    private void limparResultadoBusca() {
        lblCPFBuscar.setText("");
        lblNomeBuscar.setText("");
        lblIdadeBuscar.setText("");
        lblPesoBuscar.setText("");
        lblAlturaBuscar.setText("");
        lblTelefoneBuscar.setText("");
    }
}