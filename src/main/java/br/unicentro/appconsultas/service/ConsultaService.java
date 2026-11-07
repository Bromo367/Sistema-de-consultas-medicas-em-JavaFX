package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Consulta;
import br.unicentro.appconsultas.dao.implementacoes.ConsultaDAO;
import br.unicentro.appconsultas.dao.implementacoes.PacienteDAO;
import br.unicentro.appconsultas.dao.implementacoes.MedicoDAO;

public class ConsultaService {
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    public void cadastrar(Consulta consulta) throws ServiceException{
        try{
            if (pacienteDAO.buscaPorCodigo(consulta.getCpfPaciente()) == null) {
                throw (new ServiceException("Paciente não cadastrado. Verifique o CPF."));
            }
            if (medicoDAO.buscaPorCodigo(consulta.getCrmMedico()) == null) {
                throw (new ServiceException("Médico não cadastrado. Verifique o CRM."));
            }
            consultaDAO.insere(consulta);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
    public Consulta buscar(String cpfPaciente, String crmMedico, String data) throws ServiceException{
        return null;
    }
    public void atualizar(Consulta consulta) throws ServiceException{

    }
    public void remover(String cpfPaciente, String crmMedico, String data) throws ServiceException{

    }
    public List<Consulta> listarTodos() throws ServiceException{
        try{
            return consultaDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
}
