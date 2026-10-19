package br.unicentro.appconsultas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.unicentro.appconsultas.main.GerenciadorTelas;
import br.unicentro.appconsultas.dao.implementacoes.ConsultaDAO;
import br.unicentro.appconsultas.model.Consulta;
import br.unicentro.appconsultas.dao.implementacoes.PacienteDAO;
import br.unicentro.appconsultas.dao.implementacoes.MedicoDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConsultaController {

    @FXML private TextField txtCPFPaciente, txtCRMMedico, txtData;
    @FXML private Label lblConfirmarCadastro;
    @FXML private TextField txtBuscarCPFPaciente, txtBuscarCRMMedico, txtBuscarData;
    @FXML private Label lblConfirmarBuscar;
    @FXML private TextField txtAtualizarCPFPaciente, txtAtualizarCRMMedico, txtAtualizarData;
    @FXML private Label lblConfirmarAtualizar;
    @FXML private TextField txtRemoverCPFPaciente, txtRemoverCRMMedico, txtRemoverData;
    @FXML private Label lblConfirmarRemover;

    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();

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
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Medico.fxml","Medico");
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
            GerenciadorTelas.trocarTela("/br/unicentro/appconsultas/view/Receita.fxml","Receita");
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
    private void btnCadastrarConsultaOnAction(ActionEvent e) {
    String cpf = txtCPFPaciente.getText().trim();
    String crm = txtCRMMedico.getText().trim();
    String data = txtData.getText().trim();
    if (cpf.isEmpty() || crm.isEmpty() || data.isEmpty()) {
        mostraMensagem(
            lblConfirmarCadastro,
            "Preencha todos os campos.",
            false
        );
        return;
    }
    try {
        validarData(data);
        if (pacienteDAO.buscaPorCodigo(cpf) == null) {
            mostraMensagem(
                lblConfirmarCadastro,
                "Paciente não cadastrado. Verifique o CPF.",
                false
            );
            return;
        }
        if (medicoDAO.buscaPorCodigo(crm) == null) {
            mostraMensagem(
                lblConfirmarCadastro,
                "Médico não cadastrado. Verifique o CRM.",
                false
            );
            return;
        }
        consultaDAO.insere(new Consulta(cpf, crm, data));
        mostraMensagem(
            lblConfirmarCadastro,
            "Consulta cadastrada com sucesso!",
            true
        );
        txtCPFPaciente.clear();
        txtCRMMedico.clear();
        txtData.clear();
    } catch (DateTimeParseException ex) {
        mostraMensagem(
            lblConfirmarCadastro,
            "Data inválida. Use o formato DD/MM/AAAA.",
            false
        );
    } catch (SQLException ex) {
        ex.printStackTrace();
        mostraMensagem(
            lblConfirmarCadastro,
            "Erro ao acessar o banco ou cadastrar a consulta.",
            false
        );
    }
}
    
    @FXML 
    private void btnBuscarConsultaOnAction(ActionEvent e) {
        String cpf = txtBuscarCPFPaciente.getText().trim();
        String crm = txtBuscarCRMMedico.getText().trim();
        String data = txtBuscarData.getText().trim();
        
        if (cpf.isEmpty() && crm.isEmpty() && data.isEmpty()) { 
            mostraMensagem(lblConfirmarBuscar, "Preencha pelo menos um campo para buscar.", false); 
            return; 
        }
        
        try { 
            if (!data.isEmpty()) {
                validarData(data);
            }
            
            List<Consulta> lista = consultaDAO.busca(
                cpf.isEmpty() ? null : cpf, 
                crm.isEmpty() ? null : crm, 
                data.isEmpty() ? null : data
            );
            
            if (lista.isEmpty()) { 
                mostraMensagem(lblConfirmarBuscar, "Nenhuma consulta encontrada.", false); 
                return; 
            }
            
            StringBuilder sb = new StringBuilder("Consultas encontradas: "); 
            for (Consulta c : lista) {
                sb.append("[CPF: ").append(c.getCpfPaciente())
                .append(", CRM: ").append(c.getCrmMedico())
                .append(", Data: ").append(c.getData()).append("] "); 
            }
            
            mostraMensagem(lblConfirmarBuscar, sb.toString(), true);
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarBuscar, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarBuscar, "Erro ao consultar o banco de dados.", false); 
        }
    }
    
    @FXML 
    private void btnAtualizarConsultaOnAction(ActionEvent e) { 
        mostraMensagem(lblConfirmarAtualizar, "Consulta usa CPF, CRM e data como chave. Para alterar, remova e cadastre novamente.", false); 
    }
    
    @FXML 
    private void btnRemoverConsultaOnAction(ActionEvent e) {
        String cpf = txtRemoverCPFPaciente.getText().trim();
        String crm = txtRemoverCRMMedico.getText().trim();
        String data = txtRemoverData.getText().trim();
        
        if (cpf.isEmpty() || crm.isEmpty() || data.isEmpty()) { 
            mostraMensagem(lblConfirmarRemover, "Preencha CPF, CRM e data.", false); 
            return; 
        }
        
        try { 
            validarData(data); 
            List<Consulta> lista = consultaDAO.busca(cpf, crm, data); 
            
            if (lista.isEmpty()) { 
                mostraMensagem(lblConfirmarRemover, "Consulta não encontrada.", false); 
                return; 
            } 
            
            consultaDAO.remove(lista.get(0)); 
            mostraMensagem(lblConfirmarRemover, "Consulta removida com sucesso!", true); 
            txtRemoverCPFPaciente.clear();
            txtRemoverCRMMedico.clear();
            txtRemoverData.clear(); 
            
        } catch (DateTimeParseException ex) { 
            mostraMensagem(lblConfirmarRemover, "Data inválida. Use AAAA-MM-DD.", false); 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            mostraMensagem(lblConfirmarRemover, "Erro ao remover. Verifique se há receitas associadas.", false); 
        }
    }
    
    private String validarData(String data) {
        LocalDate.parse(data); 
        return data;
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

