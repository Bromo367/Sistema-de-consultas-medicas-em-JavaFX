package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Consulta;
import br.unicentro.appconsultas.dao.implementacoes.ConsultaDAO;
import br.unicentro.appconsultas.dao.implementacoes.PacienteDAO;
import br.unicentro.appconsultas.dao.implementacoes.MedicoDAO;
import br.unicentro.appconsultas.dao.interfaces.IConsulta;
import br.unicentro.appconsultas.dao.interfaces.IPaciente;
import br.unicentro.appconsultas.dao.interfaces.IMedico;

public class ConsultaService {
    private final IConsulta consultaDAO = new ConsultaDAO();
    private final IPaciente pacienteDAO = new PacienteDAO();
    private final IMedico medicoDAO = new MedicoDAO();
    public void cadastrar(Consulta consulta) throws ServiceException{
        try{
            validar(consulta);
            consultaDAO.insere(consulta);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
    public List<Consulta> buscar(String cpfPaciente, String crmMedico, String data) throws ServiceException{
        try{
            List<Consulta> lista = consultaDAO.busca(cpfPaciente, crmMedico, data);
                
            if (lista.isEmpty()) { 
                throw (new ServiceException("Nenhuma consulta encontrada."));
            }

            return lista;
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
    public void remover(String cpfPaciente, String crmMedico, String data) throws ServiceException{
        try{
            List<Consulta> lista = consultaDAO.busca(cpfPaciente, crmMedico, data);
                
            if (lista.isEmpty()) { 
                throw (new ServiceException("Nenhuma consulta encontrada."));
            }
            consultaDAO.remove(lista.get(0));
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
    public List<Consulta> listarTodos() throws ServiceException{
        try{
            return consultaDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void validar(Consulta consulta) throws ServiceException, SQLException{
        if (pacienteDAO.buscaPorCodigo(consulta.getCpfPaciente()) == null) {
            throw (new ServiceException("Paciente não cadastrado. Verifique o CPF."));
        }
        if (medicoDAO.buscaPorCodigo(consulta.getCrmMedico()) == null) {
            throw (new ServiceException("Médico não cadastrado. Verifique o CRM."));
        }
    }
}
