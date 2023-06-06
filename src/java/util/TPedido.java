/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entidade.EItemPedido;
import model.entidade.EPedido;
import model.persistencia.PPedido;
import model.persistencia.PRevista;
import model.persistencia.PProduto;

/**
 *
 * @author heube
 */
public class TPedido {

    public static void main(String[] args) throws ParseException {

        //Inclui um pedido
        System.out.println("Incluindo um pedido");
        PPedido ppedido = new PPedido();
        EPedido pedido = new EPedido();

        pedido.setPessoa(new PRevista().consultar(1));
        pedido.setDataPedido(util.Data.formatarDataSQL("05/06/2023"));
        pedido.setValortotal(100.00);

        List<EItemPedido> listaItens = new ArrayList<EItemPedido>();

        EItemPedido item1 = new EItemPedido();
        item1.setProduto(new PProduto().consultar(1));
        item1.setQtde(1);

        EItemPedido item2 = new EItemPedido();
        item2.setProduto(new PProduto().consultar(2));
        item2.setQtde(2);

        listaItens.add(item1);
        listaItens.add(item2);

        pedido.setItensPedido(listaItens);

        try {
            new PPedido().incluir(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(TPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        System.out.println("Alterando pedido 3");
        
        //Alterar um pedido
        pedido.setId(3);
        pedido.setPessoa(new PRevista().consultar(2));
        pedido.setDataPedido(util.Data.formatarDataSQL("04/06/2023"));
        pedido.setValortotal(123.00);

        List<EItemPedido> listaItensAlteracao = new ArrayList<EItemPedido>();

        EItemPedido item1a = new EItemPedido();
        item1a.setProduto(new PProduto().consultar(3));
        item1a.setQtde(123);

        EItemPedido item2a = new EItemPedido();
        item2a.setProduto(new PProduto().consultar(4));
        item2a.setQtde(456);

        listaItensAlteracao.add(item1a);
        listaItensAlteracao.add(item2a);

        pedido.setItensPedido(listaItensAlteracao);

        try {
            new PPedido().alterar(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(TPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        System.out.println("Consultando o pedido 3");
        
        EPedido ped = new PPedido().consultar(3);
        
        System.out.println("ID pedido: " + ped.getId());
        System.out.println("Pessoa...: " + ped.getPessoa().getNome());
        System.out.println("Dt pedido: " + ped.getDataPedido());
        System.out.println("Vlr Total: " + ped.getValortotal());
        
        System.out.println("");
        
        for (EItemPedido item : ped.getItensPedido()) {
            
            System.out.println("ID Item..: " + item.getId());
            System.out.println("Qtde Item: " + item.getQtde());
            System.out.println("Produto..: " + item.getProduto().getNome());
            System.out.println("");
        }
        
        
        
        
    }

}
