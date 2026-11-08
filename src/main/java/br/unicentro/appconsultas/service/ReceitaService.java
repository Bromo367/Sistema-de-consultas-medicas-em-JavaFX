package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Receita;
import br.unicentro.appconsultas.dao.implementacoes.ReceitaDAO;
import br.unicentro.appconsultas.dao.interfaces.IReceita;

public class ReceitaService {
    private final IReceita receitaDAO = new ReceitaDAO();

    public void cadastrar(Receita receita) throws ServiceException{
        try{
            if (receita.getMedicamentoId() <= 0 || receita.getDose() <= 0) {
                throw (new ServiceException("Código do medicamento e dose devem ser positivos."));
            }
            receitaDAO.insere(receita);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
    public List<Receita> buscar(String cpfPaciente, String crmMedico, String data, int medicamentoId) throws ServiceException{
        try{
            List<Receita> lista = receitaDAO.busca(cpfPaciente, crmMedico, data, medicamentoId);
            if (lista.isEmpty()) { 
                throw (new ServiceException("Nenhuma receita encontrada.")); 
            }
            return lista;
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
        
    }
    public void atualizar(Receita receita) throws ServiceException{
        try{
            if (receita.getDose() <= 0 || receita.getMedicamentoId() <= 0) { 
                throw (new ServiceException("Código e dose devem ser positivos."));
            }
            List<Receita> lista = receitaDAO.busca(receita.getCpfPaciente(), 
                                                    receita.getCrmMedico(),
                                                    receita.getData(),
                                                    receita.getMedicamentoId()); 
            if (lista.isEmpty()) { 
                throw (new ServiceException("Receita não encontrada."));
            }
            receitaDAO.atualiza(receita);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
    public void remover(String cpfPaciente, String crmMedico, String data, int medicamentoId) throws ServiceException{
        try{
            List<Receita> lista = receitaDAO.busca(cpfPaciente, crmMedico, data, medicamentoId); 
            if (lista.isEmpty()) { 
                throw (new ServiceException("Receita não encontrada."));
            }
            receitaDAO.remove(lista.get(0));
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
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
