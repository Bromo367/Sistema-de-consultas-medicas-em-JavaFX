package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.model.Paciente;
import br.unicentro.appconsultas.dao.implementacoes.MedicoDAO;


public class MedicoService {
    private final MedicoDAO medicoDAO = new MedicoDAO();

    public void cadastrar(Medico medico) throws ServiceException{
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
