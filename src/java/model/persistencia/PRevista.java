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
import model.entidade.ERevista;
import util.Conexao;

/**
 *
 * @author heube
 */
public class PRevista {

    private Connection cnn;

    public PRevista() {

        //Instancia o objeto CNN a partir da fábrica de conexões
        cnn = Conexao.getConnection();
    }

    public void incluir(ERevista parametro) {

        String sql = "INSERT INTO revista (nome, idade) VALUES (?,?)";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, parametro.getNome());
            prd.setInt(2, parametro.getIdade());

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PRevista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void alterar(ERevista parametro) {

        String sql = "UPDATE revista SET nome = ?, idade = ? WHERE id = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, parametro.getNome());
            prd.setInt(2, parametro.getIdade());
            prd.setInt(3, parametro.getId());

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PRevista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int parametro) {

        String sql = "DELETE FROM revista WHERE id = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, parametro);

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PRevista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ERevista consultar(int parametro) {

        ERevista revista = new ERevista();

        String sql = "SELECT * FROM revista WHERE id = ?";

        try {

            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, parametro);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {

                revista.setId(rst.getInt("id"));
                revista.setNome(rst.getString("nome"));
                revista.setIdade(rst.getInt("idade"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PRevista.class.getName()).log(Level.SEVERE, null, ex);
        }

        return revista;

    }

    public List<ERevista> listar() {
        
        List<ERevista> lista = new ArrayList<ERevista>();
        
        String sql = "SELECT * FROM revista";
        try {
            Statement prd = cnn.createStatement();
            ResultSet rst = prd.executeQuery(sql);
            while (rst.next()) {
                ERevista revista = new ERevista();
                revista.setId(rst.getInt("id"));
                revista.setNome(rst.getString("nome"));
                revista.setIdade(rst.getInt("idade"));
                lista.add(revista);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PRevista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
