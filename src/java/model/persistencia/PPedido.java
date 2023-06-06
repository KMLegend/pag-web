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
import model.entidade.EPedido;
import util.Conexao;

/**
 *
 * @author heube
 */

public class PPedido {

    private Connection cnn;

    public PPedido() {

        //Instancia o objeto CNN a partir da fábrica de conexões
        cnn = Conexao.getConnection();
    }

    public void incluir(EPedido parametro) throws SQLException {
        try {
            cnn.setAutoCommit(false);

            String sql = "INSERT INTO pedido (valortotal, datapedido, idrevista ) VALUES (?,?,?)";

            PreparedStatement prd = cnn.prepareStatement(sql);

            int idPedido = 0;

            prd.setDouble(1, parametro.getValortotal());
            prd.setDate(2, new java.sql.Date(parametro.getDataPedido().getTime()));
            prd.setInt(3, parametro.getPessoa().getId());

            prd.executeUpdate();

            //Consulto o ID que foi gerado pela sequence no momento da gravação 
            //do pedido
            String sql2 = "SELECT currval('pedido_id_seq') as idatual ";

            Statement prd2 = cnn.createStatement();
            ResultSet rst = prd2.executeQuery(sql2);
            if (rst.next()) {
                idPedido = rst.getInt("idatual");
            }

            //===================================================================
            //===================================================================
            //===================================================================
            //Preciso percorrer a lista e gravar os itens de pedido
            for (EItemPedido item : parametro.getItensPedido()) {
                item.getPedido().setId(idPedido);
                new PItemPedido().incluir(item, cnn); //Grava o item pedido
            }
            //===================================================================
            //===================================================================
            //===================================================================

        } catch (SQLException ex) {
            cnn.rollback();
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cnn.setAutoCommit(true);
        }

    }

    public void alterar(EPedido parametro) throws SQLException {

        cnn.setAutoCommit(false);
        
        String sql = "UPDATE pedido SET valortotal = ?, datapedido = ?, idrevista = ? WHERE id = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setDouble(1, parametro.getValortotal());
            prd.setDate(2, new java.sql.Date(parametro.getDataPedido().getTime()));
            prd.setInt(3, parametro.getPessoa().getId());
            prd.setInt(4, parametro.getId());

            prd.executeUpdate();

            //Exclui todos os itens relacionados ao pedido pai
            new PItemPedido().excluirTodosPorPedido(parametro.getId(), cnn);

            //===================================================================
            //===================================================================
            //===================================================================
            //Preciso percorrer a lista e gravar os itens de pedido
            for (EItemPedido item : parametro.getItensPedido()) {
                item.getPedido().setId(parametro.getId());
                new PItemPedido().incluir(item, cnn); //Grava o item pedido
            }
            //===================================================================
            //===================================================================
            //===================================================================   

        } catch (SQLException ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int idpedido) {

        //Primeiro deverá excluir os ItensPedido - Os filhos
        new PItemPedido().excluirTodosPorPedido(idpedido, cnn);

        //Exclui o pedido
        String sql = "DELETE FROM pedido WHERE id = ?";

        try {
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, idpedido);

            prd.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public EPedido consultar(int parametro) {

        EPedido pedido = new EPedido();

        String sql = "SELECT * FROM pedido WHERE id = ?";

        try {

            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, parametro);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {

                pedido.setId(rst.getInt("id"));
                pedido.setValortotal(rst.getDouble("valortotal"));
                pedido.setDataPedido(rst.getDate("datapedido"));
                pedido.setPessoa(new PRevista().consultar(rst.getInt("idrevista")));
                pedido.setItensPedido(new PItemPedido().listar(rst.getInt("id")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedido;

    }

    public List<EPedido> listar() {

        List<EPedido> lista = new ArrayList<EPedido>();

        String sql = "SELECT * FROM pedido";
        try {
            Statement prd = cnn.createStatement();
            ResultSet rst = prd.executeQuery(sql);
            while (rst.next()) {
                EPedido pedido = new EPedido();
                pedido.setId(rst.getInt("id"));
                pedido.setValortotal(rst.getDouble("valortotal"));
                pedido.setDataPedido(rst.getDate("datapedido"));
                pedido.setPessoa(new PRevista().consultar(rst.getInt("idrevista")));
                lista.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
