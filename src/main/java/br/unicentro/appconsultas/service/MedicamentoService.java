package br.unicentro.appconsultas.service;

import java.sql.SQLException;

import java.util.List;

import br.unicentro.appconsultas.dao.implementacoes.MedicamentoDAO;
import br.unicentro.appconsultas.model.Medicamento;
import br.unicentro.appconsultas.dao.interfaces.IMedicamento;

public class MedicamentoService {
    private final IMedicamento medicamentoDAO = new MedicamentoDAO();

    public void cadastrar(Medicamento medicamento) throws ServiceException{
        

        try{
            if (medicamentoDAO.buscaPorCodigo(medicamento.getMedicamentoId()) != null) {
                throw(new ServiceException("Já existe um medicamento com esse código."));
            }

            medicamentoDAO.insere(medicamento);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public Medicamento buscar(int medicamentoId) throws ServiceException{
        Medicamento medicamento=null;
        try{
            medicamento = medicamentoDAO.buscaPorCodigo(medicamentoId);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
        
        if (medicamento == null) { 
            throw(new ServiceException("Medicamento não encontrado."));
        } 
         
        return medicamento;
    }

    public void atualizar(Medicamento medicamento) throws ServiceException{
        validar(medicamento);
        try{
            medicamentoDAO.atualiza(medicamento);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void remover(int id) throws ServiceException{
        try{
            Medicamento m = medicamentoDAO.buscaPorCodigo(id); 
            
            if (m == null) { 
                throw (new ServiceException("Medicamento não encontrado.")); 
            } 
            
            medicamentoDAO.remove(m);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public List<Medicamento> listarTodos() throws ServiceException{
        try{
            return medicamentoDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void validar(Medicamento medicamento) throws ServiceException{
        if (medicamento.getMedicamentoId() <= 0) {
            throw(new ServiceException("O código deve ser um número positivo."));
        }
    }
}
