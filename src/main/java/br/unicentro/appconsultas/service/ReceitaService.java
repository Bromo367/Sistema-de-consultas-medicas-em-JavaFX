package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Receita;
import br.unicentro.appconsultas.dao.implementacoes.ReceitaDAO;


public class ReceitaService {
    private final ReceitaDAO receitaDAO = new ReceitaDAO();

    public void cadastrar(Receita receita) throws ServiceException{

    }
    public Receita buscar(String cpfPaciente, String crmMedico, String data, int medicamentoId) throws ServiceException{
        return null;
    }
    public void atualizar(Receita receita) throws ServiceException{

    }
    public void remover(String cpfPaciente, String crmMedico, String data, int medicamentoId) throws ServiceException{

    }
    public List<Receita> listarTodos() throws ServiceException{
        try{
            return receitaDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
}
