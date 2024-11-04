package com.example.Ch3.repository;

import com.example.Ch3.model.ConexaoBD;
import com.example.Ch3.model.Peca;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PecaRepository {

    private static final String INSERT_SQL = "INSERT INTO TB_PECA (id, nome, preco, quantidade) VALUES (seq_tb_peca.NEXTVAL, ?, ?, ?)";

    private static final String SELECT_ALL_SQL = "SELECT * FROM TB_PECA";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM TB_PECA WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM TB_PECA WHERE id = ?";

    public Peca inserirPeca(Peca peca) {
        String sql = "INSERT INTO TB_PECA (id, nome, preco, quantidade) VALUES (seq_tb_peca.NEXTVAL, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"id"})) {


            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getPreco());
            stmt.setInt(3, peca.getQuantidade());


            stmt.executeUpdate();


            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    peca.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peca;
    }


    public List<Peca> listarPecas() {
        List<Peca> pecas = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Peca peca = new Peca(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
                pecas.add(peca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pecas;
    }

    public Optional<Peca> buscarPorId(Long id) {
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Peca peca = new Peca(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
                return Optional.of(peca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void removerPeca(Long id) {
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
