package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.dao.implementacoes.MedicoDAO;


public class MedicoService {
    private final MedicoDAO medicoDAO = new MedicoDAO();

    public void cadastrar(Medico medico) throws ServiceException{

    }
    public Medico buscar(String crm) throws ServiceException{

    }
    public void atualizar(Medico medico) throws ServiceException{

    }
    public void remover(String crm) throws ServiceException{

    }
    public List<Medico> listarTodos() throws ServiceException{
        try{
            return medicoDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
}
