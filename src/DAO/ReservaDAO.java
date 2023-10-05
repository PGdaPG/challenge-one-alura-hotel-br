package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;

public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection){
        this.connection = connection;
    }
    public void salvar(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, ds_forma_pagamento) VALUES(?, ?, ?, ?)";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstm.setString(1, reserva.getDataEntradaFormatada());
                pstm.setString(2, reserva.getDataSaidaFormatada());
                pstm.setDouble(3, reserva.getValor());
                pstm.setString(4, reserva.getFormaPagamento());

                System.out.println(pstm.toString());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        reserva.setIdReserva(rst.getInt(1));
                        System.out.println(reserva.getIdReserva());
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }

             } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void listarSimples(){
        
        try {
            String sql = "SELECT id_reserva, data_entrada, data_saida, valor, ds_forma_pagamento FROM reservas WHERE id_reserva = 10";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        System.out.println(rst.getInt(1));
                        System.out.println(rst.getDate(3));
                    }
                } 
            } 
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }
    public List<Reserva> listar(Integer idReserva) {
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            String sql;
            if (idReserva == null) {
                sql = "SELECT id_reserva, data_entrada, data_saida, valor, ds_forma_pagamento FROM reservas";
            } else {
                sql = "SELECT id_reserva, data_entrada, data_saida, valor, ds_forma_pagamento FROM reservas WHERE id_reserva = ?";
            }
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                if (idReserva != null) {
                    pstm.setInt(1, idReserva);
                }
                pstm.execute();

                transformarResultSetEmReserva(reservas , pstm);
            } 
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void transformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException{
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getDouble(4) , rst.getString(5));
                reserva.toString();
                reservas.add(reserva);
            }
        }
    }
    public void alterarReserva(Reserva reserva) {
        String sql = "UPDATE reservas r SET r.data_entrada = ?, data_saida = ?, valor = ?, ds_forma_pagamento = ? WHERE id_reserva = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setDate(1, java.sql.Date.valueOf(reserva.getDataEntradaFormatada()));
            pstm.setDate(2, java.sql.Date.valueOf(reserva.getDataSaidaFormatada()));
            pstm.setDouble(3, reserva.getValor());
            pstm.setString(4, reserva.getFormaPagamento());
            pstm.setInt(5, reserva.getIdReserva());
            System.out.println(pstm.toString());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   
    }
    public void deletar(Integer idReserva) {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, idReserva);
            System.out.println(pstm.toString());
            pstm.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
