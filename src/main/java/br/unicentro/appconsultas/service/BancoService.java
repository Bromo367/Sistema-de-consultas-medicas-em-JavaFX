package br.unicentro.appconsultas.service;

import java.sql.SQLException;
import br.unicentro.appconsultas.dao.implementacoes.BancoDAO;

public class BancoService {
    private final BancoDAO bancoDAO = new BancoDAO();

    public void apagarTudo(){
        try{
            bancoDAO.apagarTudo();
        }
        catch(SQLException ex){
            throw(new ServiceException("Erro na conexão com o banco de dados"));
        }
    }
}
