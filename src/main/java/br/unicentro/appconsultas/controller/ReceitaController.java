package br.unicentro.appconsultas.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.model.Receita;
import br.unicentro.appconsultas.service.ReceitaService;
import br.unicentro.appconsultas.service.ServiceException;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReceitaController {
    @FXML private TextField txtCPFPaciente, txtCRMMedico, txtData, txtMedicamentoId, txtDose, txtUnidadeDeMedida, txtIntervalo;
    @FXML private Label lblConfirmarEmitir;
    @FXML private TextField txtBuscarMedicamentoId, txtBuscarCPF, txtBuscarCRM, txtBuscarData;
    @FXML private Label lblConfirmarBuscar, lblDataBuscar, lblDoseBuscar, lblIntervaloBuscar;
    @FXML private TextField txtAtualizarCPFPaciente, txtAtualizarCRMMedico, txtAtualizarData, txtAtualizarMedicamentoId, txtAtualizarDose, txtAtualizarUnidadeDeMedida, txtAtualizarIntervalo;
    @FXML private Label lblConfirmarAtualizar;
    @FXML private TextField txtRemoverMedicamentoId, txtRemoverCPF, txtRemoverCRM, txtRemoverData;
    @FXML private Label lblConfirmarRemover;

    @FXML
    private Label lblConfirmarListar;

    @FXML
    private Pane painelLista;

    private final ReceitaService receitaService = new ReceitaService();
    
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
    private void btnEmitirReceitaOnAction(ActionEvent event) {
        String cpf = txtCPFPaciente.getText().trim();
        String crm = txtCRMMedico.getText().trim();
        String data = txtData.getText().trim();
        String idT = txtMedicamentoId.getText().trim();
        String doseT = txtDose.getText().trim();
        String unidade = txtUnidadeDeMedida.getText().trim();
        String intervalo = txtIntervalo.getText().trim();

        if (cpf.isEmpty() || crm.isEmpty() || data.isEmpty()
                || idT.isEmpty() || doseT.isEmpty()
                || unidade.isEmpty() || intervalo.isEmpty()) {
            mostraMensagem(
                lblConfirmarEmitir,
                "Preencha todos os campos.",
                false
            );
            return;
        }

        try {
            LocalDate.parse(data);

            int id = Integer.parseInt(idT);
            double dose = Double.parseDouble(doseT.replace(',', '.'));

            

            Receita receita = new Receita(
                cpf, crm, data, id, dose, unidade, intervalo
            );

            receitaService.cadastrar(receita);

            mostraMensagem(
                lblConfirmarEmitir,
                "Receita cadastrada com sucesso!",
                true
            );

            txtCPFPaciente.clear();
            txtCRMMedico.clear();
            txtData.clear();
            txtMedicamentoId.clear();
            txtDose.clear();
            txtUnidadeDeMedida.clear();
            txtIntervalo.clear();

        } catch (DateTimeParseException ex) {
            mostraMensagem(
                lblConfirmarEmitir,
                "Data inválida. Use AAAA-MM-DD.",
                false
            );
        } catch (NumberFormatException ex) {
            mostraMensagem(
                lblConfirmarEmitir,
                "Código do medicamento ou dose inválidos.",
                false
            );
        } catch (ServiceException ex) {
            ex.printStackTrace();

            mostraMensagem(
                lblConfirmarEmitir,
                ex.getMessage(),
                false
            );
        }
    }


    @FXML 
    private void btnBuscarReceitaOnAction(ActionEvent event) {
        String cpf = txtBuscarCPF.getText().trim();
        String crm = txtBuscarCRM.getText().trim();
        String data = txtBuscarData.getText().trim();
        String idT = txtBuscarMedicamentoId.getText().trim();
        
        if (cpf.isEmpty() && crm.isEmpty() && data.isEmpty() && idT.isEmpty()) { 
            mostraMensagem(lblConfirmarBuscar, "Preencha pelo menos um campo para buscar.", false); 
            limparBusca(); 
            return; 
        }
        try { 
            if (!data.isEmpty()) {
                LocalDate.parse(data);
            }
            List<Receita> lista = receitaService.buscar(
                cpf.isEmpty() ? null : cpf, 
                crm.isEmpty() ? null : crm, 
                data.isEmpty() ? null : data, 
                idT.isEmpty() ? -1 : Integer.parseInt(idT)
            );
            Receita r = lista.get(0); 
            lblDataBuscar.setText("Data: " + r.getData()); 
            lblDoseBuscar.setText("Dose: " + r.getDose() + " " + r.getUnidadeMedida()); 
            lblIntervaloBuscar.setText("Intervalo: " + r.getIntervalo()); 
            mostraMensagem(lblConfirmarBuscar, lista.size() > 1 ? "Receitas encontradas: " + lista.size() + " (exibindo a primeira)." : "Receita encontrada.", true);
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarBuscar, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarBuscar, "Código do medicamento inválido.", false); 
        } catch (ServiceException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarBuscar, ex.getMessage(), false); 
            limparBusca(); 
        }
    }

    @FXML 
    private void btnAtualizarReceitaOnAction(ActionEvent evebt) {
        String cpf = txtAtualizarCPFPaciente.getText().trim();
        String crm = txtAtualizarCRMMedico.getText().trim();
        String data = txtAtualizarData.getText().trim();
        String idT = txtAtualizarMedicamentoId.getText().trim();
        String doseT = txtAtualizarDose.getText().trim();
        String unidade = txtAtualizarUnidadeDeMedida.getText().trim();
        String intervalo = txtAtualizarIntervalo.getText().trim();
        
        if (cpf.isEmpty() || crm.isEmpty() || data.isEmpty() || idT.isEmpty() || doseT.isEmpty() || unidade.isEmpty() || intervalo.isEmpty()) { 
            mostraMensagem(lblConfirmarAtualizar, "Preencha todos os campos.", false); 
            return; 
        }
        try { 
            LocalDate.parse(data); 
            int id = Integer.parseInt(idT); 
            double dose = Double.parseDouble(doseT.replace(',', '.')); 
            Receita receita = new Receita(cpf, crm, data, id, dose, unidade, intervalo);
            receitaService.atualizar(receita);
            mostraMensagem(lblConfirmarAtualizar, "Receita atualizada com sucesso!", true); 
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarAtualizar, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarAtualizar, "Código ou dose inválidos.", false); 
        } catch (ServiceException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarAtualizar, ex.getMessage(), false); 
        }
    }

    @FXML 
    private void btnRemoverReceitaOnAction(ActionEvent event) {
        String cpf = txtRemoverCPF.getText().trim();
        String crm = txtRemoverCRM.getText().trim();
        String data = txtRemoverData.getText().trim();
        String idT = txtRemoverMedicamentoId.getText().trim();
        
        if (cpf.isEmpty() || crm.isEmpty() || data.isEmpty() || idT.isEmpty()) { 
            mostraMensagem(lblConfirmarRemover, "Preencha CPF, CRM, data e código do medicamento.", false); 
            return; 
        }
        try { 
            LocalDate.parse(data); 
            int id = Integer.parseInt(idT);
            receitaService.remover(cpf, crm, data, id);
            mostraMensagem(lblConfirmarRemover, "Receita removida com sucesso!", true); 
            txtRemoverCPF.clear();
            txtRemoverCRM.clear();
            txtRemoverData.clear();
            txtRemoverMedicamentoId.clear(); 
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarRemover, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarRemover, "Código do medicamento inválido.", false); 
        } catch (ServiceException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarRemover, ex.getMessage(), false); 
        }
    }

    @FXML
    private void btnListarReceitaOnAction(ActionEvent event) {
        try{

            TableView<Receita> tabela = new TableView<>();
            
            TableColumn<Receita, String> colunaCPF = new TableColumn("Paciente");
            colunaCPF.setCellValueFactory(new  PropertyValueFactory<>("cpfPaciente"));
    
            TableColumn<Receita, String> colunaCRM = new TableColumn("Médico");
            colunaCRM.setCellValueFactory(new  PropertyValueFactory<>("crmMedico"));

            TableColumn<Receita, String> colunaData = new TableColumn("Data");
            colunaData.setCellValueFactory(new  PropertyValueFactory<>("data"));
    
            TableColumn<Receita, Integer> colunaMedicamento = new TableColumn("Medicamento");
            colunaMedicamento.setCellValueFactory(new  PropertyValueFactory<>("medicamentoId"));

            TableColumn<Receita, Integer> colunaDose = new TableColumn("Dosagem");
            colunaDose.setCellValueFactory(new  PropertyValueFactory<>("dose"));

            TableColumn<Receita, Integer> colunaUnidade = new TableColumn("Unidade Medida");
            colunaUnidade.setCellValueFactory(new  PropertyValueFactory<>("unidadeMedida"));

            TableColumn<Receita, Integer> colunaIntervalo = new TableColumn("Intervalo");
            colunaIntervalo.setCellValueFactory(new  PropertyValueFactory<>("intervalo"));

    
            colunaCRM.setPrefWidth(92);
            colunaCPF.setPrefWidth(92);
            colunaData.setPrefWidth(92);
            colunaMedicamento.setPrefWidth(92);
            colunaDose.setPrefWidth(92);
            colunaUnidade.setPrefWidth(92);
            colunaIntervalo.setPrefWidth(92);
            

            tabela.getColumns().addAll(colunaCRM, colunaCPF, colunaData, colunaMedicamento, colunaDose, colunaUnidade, colunaIntervalo);

            tabela.getItems().setAll(receitaService.listarTodos());

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

    private void limparBusca(){ 
        lblDataBuscar.setText("");
        lblDoseBuscar.setText("");
        lblIntervaloBuscar.setText("");
    }
}

