package controller;

import java.sql.Connection;
import java.util.List;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

public class ReservaController {
    
    private ReservaDAO reservaDAO;

    public ReservaController(){
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservaDAO = new ReservaDAO(connection);
    }
    public void salvar(Reserva reserva){
        this.reservaDAO.salvar(reserva);;
    }
    public List<Reserva> listar(Integer idReserva) {
        return this.reservaDAO.listar(idReserva);
    }
    public void listarSimples(){
        this.reservaDAO.listarSimples();
    }
    public void alterarReserva(Reserva reserva) {
        this.reservaDAO.alterarReserva(reserva);
    }
    public void deletar(Integer idReserva) {
        this.reservaDAO.deletar(idReserva);
    }
}
