package br.unicentro.appconsultas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.dao.implementacoes.ReceitaDAO;
import br.unicentro.appconsultas.model.Receita;

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

    private final ReceitaDAO receitaDAO = new ReceitaDAO();
    
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
    private void btnEmitirReceitaOnAction(ActionEvent e) {
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

            if (id <= 0 || dose <= 0) {
                mostraMensagem(
                    lblConfirmarEmitir,
                    "Código do medicamento e dose devem ser positivos.",
                    false
                );
                return;
            }

            Receita receita = new Receita(
                cpf, crm, data, id, dose, unidade, intervalo
            );

            receitaDAO.insere(receita);

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
        } catch (SQLException ex) {
            ex.printStackTrace();

            mostraMensagem(
                lblConfirmarEmitir,
                "Não foi possível cadastrar a receita. Verifique os dados informados.",
                false
            );
        }
    }


    @FXML 
    private void btnBuscarReceitaOnAction(ActionEvent e) {
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
            int id = idT.isEmpty() ? -1 : Integer.parseInt(idT); 
            List<Receita> lista = receitaDAO.busca(
                cpf.isEmpty() ? null : cpf, 
                crm.isEmpty() ? null : crm, 
                data.isEmpty() ? null : data, 
                id
            );
            if (lista.isEmpty()) { 
                mostraMensagem(lblConfirmarBuscar, "Nenhuma receita encontrada.", false); 
                limparBusca(); 
                return; 
            }
            Receita r = lista.get(0); 
            lblDataBuscar.setText("Data: " + r.getData()); 
            lblDoseBuscar.setText("Dose: " + r.getDose() + " " + r.getUnidadeMedida()); 
            lblIntervaloBuscar.setText("Intervalo: " + r.getIntervalo()); 
            mostraMensagem(lblConfirmarBuscar, lista.size() > 1 ? "Receitas encontradas: " + lista.size() + " (exibindo a primeira)." : "Receita encontrada.", true);
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarBuscar, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarBuscar, "Código do medicamento inválido.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarBuscar, "Erro ao consultar o banco.", false); 
            limparBusca(); 
        }
    }

    @FXML 
    private void btnAtualizarReceitaOnAction(ActionEvent e) {
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
            if (dose <= 0 || id <= 0) { 
                mostraMensagem(lblConfirmarAtualizar, "Código e dose devem ser positivos.", false); 
                return; 
            } 
            List<Receita> lista = receitaDAO.busca(cpf, crm, data, id); 
            if (lista.isEmpty()) { 
                mostraMensagem(lblConfirmarAtualizar, "Receita não encontrada.", false); 
                return; 
            }
            receitaDAO.atualiza(new Receita(cpf, crm, data, id, dose, unidade, intervalo)); 
            mostraMensagem(lblConfirmarAtualizar, "Receita atualizada com sucesso!", true); 
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarAtualizar, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarAtualizar, "Código ou dose inválidos.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarAtualizar, "Erro ao atualizar receita.", false); 
        }
    }

    @FXML 
    private void btnRemoverReceitaOnAction(ActionEvent e) {
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
            List<Receita> lista = receitaDAO.busca(cpf, crm, data, id); 
            if (lista.isEmpty()) { 
                mostraMensagem(lblConfirmarRemover, "Receita não encontrada.", false); 
                return; 
            } 
            receitaDAO.remove(lista.get(0)); 
            mostraMensagem(lblConfirmarRemover, "Receita removida com sucesso!", true); 
            txtRemoverCPF.clear();
            txtRemoverCRM.clear();
            txtRemoverData.clear();
            txtRemoverMedicamentoId.clear(); 
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarRemover, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (NumberFormatException ex) { 
            mostraMensagem(lblConfirmarRemover, "Código do medicamento inválido.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarRemover, "Erro ao remover receita.", false); 
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

