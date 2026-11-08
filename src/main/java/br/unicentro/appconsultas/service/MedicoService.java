package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.dao.implementacoes.MedicoDAO;
import br.unicentro.appconsultas.dao.interfaces.IMedico;



public class MedicoService {
    private final IMedico medicoDAO = new MedicoDAO();
    private final VerificadorString vs = new VerificadorString();
    public void cadastrar(Medico medico) throws ServiceException{
        validar(medico);
        try{
            medicoDAO.insere(medico);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public Medico buscar(String crm) throws ServiceException{
        try{
            Medico medico = medicoDAO.buscaPorCodigo(crm);
            
            if (medico == null) {
                throw (new ServiceException("Nenhum médico encontrado com esse CRM."));
            }
            return medico;
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void atualizar(Medico medico) throws ServiceException{
        validar(medico);
        try{
            if(medicoDAO.buscaPorCodigo(medico.getCrmMedico()) == null){
                throw (new ServiceException("Nenhum médico encontrado com esse CRM."));
            }
            medicoDAO.atualiza(medico);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void remover(String crm) throws ServiceException{
        try{
            Medico medico = medicoDAO.buscaPorCodigo(crm);
            if(medico == null){
                throw (new ServiceException("Nenhum médico encontrado com esse CRM."));
            }
            medicoDAO.remove(medico);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public List<Medico> listarTodos() throws ServiceException{
        try{
            return medicoDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void validar(Medico medico) throws ServiceException{
        if(!vs.naoNumerico(medico.getNome())|| medico.getNome().length()>60){
            throw (new ServiceException("Insira um nome válido."));
        }
        if(!vs.numerico(medico.getTelefone())){
            throw (new ServiceException("O telefone deve conter somente números."));
        }
    }
}
