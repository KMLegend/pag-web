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
import model.entidade.EItemPedido;

import util.Conexao;

/**
 *
 * @author heube
 */
public class PItemPedido {

    private Connection cnn;

    public PItemPedido() {

        //Instancia o objeto CNN a partir da fábrica de conexões
        cnn = Conexao.getConnection();
    }

    public void incluir(EItemPedido parametro, Connection cnn) throws SQLException {

        String sql = "INSERT INTO itempedido (qtde, idpedido, idproduto) VALUES (?,?,?)";

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setDouble(1, parametro.getQtde());
        prd.setDouble(2, parametro.getPedido().getId());
        prd.setDouble(3, parametro.getProduto().getId());

        prd.executeUpdate();

    }

    public void excluirTodosPorPedido(int parametro, Connection cnn) {

        String sql = "DELETE FROM itempedido WHERE idpedido = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, parametro);

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public EItemPedido consultar(int iditempedido) {

        EItemPedido itempedido = new EItemPedido();

        String sql = "SELECT * FROM itempedido WHERE id = ?";

        try {

            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, iditempedido);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {

                itempedido.setId(rst.getInt("id"));
                itempedido.setQtde(rst.getDouble("qtde"));
                itempedido.getPedido().setId(rst.getInt("idpedido"));
                itempedido.setProduto(new PProduto().consultar(rst.getInt("idproduto")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itempedido;

    }

    public List<EItemPedido> listar(int idpedido) {

        List<EItemPedido> lista = new ArrayList<EItemPedido>();

        String sql = "SELECT * FROM itempedido WHERE idpedido = ?";
        try {
            
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, idpedido);
            ResultSet rst = prd.executeQuery();
            
            while (rst.next()) {
                EItemPedido itempedido = new EItemPedido();
                itempedido.setId(rst.getInt("id"));
                itempedido.setQtde(rst.getDouble("qtde"));
                itempedido.getPedido().setId(rst.getInt("idpedido"));
                itempedido.setProduto(new PProduto().consultar(rst.getInt("idproduto")));
                lista.add(itempedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
