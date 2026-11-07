package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Consulta;
import br.unicentro.appconsultas.dao.implementacoes.ConsultaDAO;

public class ConsultaService {
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    public void cadastrar(Consulta consulta) throws ServiceException{

    }
    public Consulta buscar(String cpfPaciente, String crmMedico, String data) throws ServiceException{

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
