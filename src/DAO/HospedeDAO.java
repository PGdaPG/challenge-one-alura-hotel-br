package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.Hospede;
import model.Reserva;

public class HospedeDAO {
    
    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Hospede hospede) {
        try {
            String sql = "INSERT INTO reservas (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES(?, ?, ?, ?, ?, ?)";
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
}
