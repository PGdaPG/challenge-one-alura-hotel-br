package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Hospede;

public class HospedeDAO {
    
    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Hospede hospede) {
        try {
            String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES(?, ?, ?, ?, ?, ?)";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.KEEP_CURRENT_RESULT)) {
                
                pstm.setString(1, hospede.getNome());
                pstm.setString(2, hospede.getSobreNome());
                pstm.setString(3, hospede.getDataNascionalidadeFormatada());
                pstm.setString(4, hospede.getNacionalidade());
                pstm.setString(5, hospede.getTelefone());
                pstm.setInt(6, hospede.getNumeroReserva());

                System.out.println(pstm.toString());
                pstm.execute();

             } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public List<Hospede> listar(String nome) {

        List<Hospede> hospedes = new ArrayList<Hospede>();
        try {
            String sql;
            if (nome == null) {
                sql = "SELECT * FROM hospedes";
            } else {
                sql = "SELECT id_hospede, nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva FROM hospedes WHERE nome = ?";
            }
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                if (nome != null) {
                    pstm.setString(1, nome);
                }
                pstm.execute();
                System.out.println(pstm.toString());
                transformarResultSetEmHospede(hospedes , pstm);
            } 
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void transformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException{
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
                hospede.toString();
                hospedes.add(hospede);
            }

        }
    }
}
