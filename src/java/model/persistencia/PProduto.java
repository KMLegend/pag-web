/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entidade.EProduto;
import util.Conexao;

/**
 *
 * @author heube
 */
public class PProduto {

    private Connection cnn;

    public PProduto() {

        //Instancia o objeto CNN a partir da fábrica de conexões
        cnn = Conexao.getConnection();
    }

    public void incluir(EProduto parametro) {

        String sql = "INSERT INTO produto (nome) VALUES (?)";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, parametro.getNome());

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void alterar(EProduto parametro) {

        String sql = "UPDATE produto SET nome = ? WHERE id = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, parametro.getNome());
            prd.setInt(2, parametro.getId());

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int parametro) {

        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, parametro);

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public EProduto consultar(int parametro) {

        EProduto produto = new EProduto();

        String sql = "SELECT * FROM produto WHERE id = ?";

        try {

            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, parametro);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {

                produto.setId(rst.getInt("id"));
                produto.setNome(rst.getString("nome"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produto;

    }

    public List<EProduto> listar() {
        
        List<EProduto> lista = new ArrayList<EProduto>();
        
        String sql = "SELECT * FROM produto";
        try {
            Statement prd = cnn.createStatement();
            ResultSet rst = prd.executeQuery(sql);
            while (rst.next()) {
                EProduto produto = new EProduto();
                produto.setId(rst.getInt("id"));
                produto.setNome(rst.getString("nome"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
