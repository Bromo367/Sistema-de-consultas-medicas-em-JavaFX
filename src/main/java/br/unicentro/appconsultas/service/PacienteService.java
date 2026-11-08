package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Paciente;
import br.unicentro.appconsultas.dao.implementacoes.PacienteDAO;
import br.unicentro.appconsultas.dao.interfaces.IPaciente;


public class PacienteService {
    private final IPaciente pacienteDAO = new PacienteDAO();
    private final VerificadorString vs = new VerificadorString();
    public void cadastrar(Paciente paciente) throws ServiceException{
        validar(paciente);

        try{
            pacienteDAO.insere(paciente);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public Paciente buscar(String cpf) throws ServiceException{
        try{
            Paciente paciente = pacienteDAO.buscaPorCodigo(cpf);
            
            if (paciente == null) {
                throw (new ServiceException("Nenhum paciente encontrado com esse CPF."));
            }
            return paciente;
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void atualizar(Paciente paciente) throws ServiceException{
        validar(paciente);

        try{
            if (pacienteDAO.buscaPorCodigo(paciente.getCpfPaciente()) == null) {
                throw (new ServiceException("Não existe paciente cadastrado com esse CPF."));
            }
            pacienteDAO.atualiza(paciente);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void remover(String cpf) throws ServiceException{
        try{
            Paciente paciente = pacienteDAO.buscaPorCodigo(cpf);
            if (paciente == null) {
                throw (new ServiceException("Não existe paciente cadastrado com esse CPF."));
            }
            pacienteDAO.remove(paciente);
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro ao remover. Verifique se o paciente possui consultas ou receitas associadas."));
        }
    }
    
    public List<Paciente> listarTodos() throws ServiceException{
        try{
            return pacienteDAO.listaTodos();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }

    public void validar(Paciente paciente) throws ServiceException{
        if(!vs.naoNumerico(paciente.getNome()) || paciente.getNome().length()>60){
            throw (new ServiceException("Insira um nome válido."));
        }
        if (paciente.getIdade() <= 0 || paciente.getPeso() <= 0 || paciente.getAltura() <= 0) {
            throw (new ServiceException("Insira valores válidos."));
        }
        if (paciente.getCpfPaciente().length()!=11|| !vs.numerico(paciente.getCpfPaciente())){
            throw (new ServiceException("O cpf deve conter somente números."));
        }
        if(!vs.numerico(paciente.getTelefone())){
            throw (new ServiceException("O telefone deve conter somente números."));
        }
    }
}
