package controller;

import java.sql.Connection;
import java.util.List;

import DAO.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospede;

public class HospedeController {

    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }
    public void salvar(Hospede hospede) {
        this.hospedeDAO.salvar(hospede);
    }
    public List<Hospede> listar(String nome) {
        return this.hospedeDAO.listar(nome);
    }
    public void deletar(Integer idHospede) {
        this.hospedeDAO.deletar(idHospede);
    }
    public void alterar(Hospede hospede) {
        this.hospedeDAO.alterar(hospede);
    }

    
}
