/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

/**
 *
 * @author heube
 */
public class EItemPedido {
    
    private int id;
    private double qtde;
    private EProduto produto;
    private EPedido pedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public EProduto getProduto() {
        return produto;
    }

    public void setProduto(EProduto produto) {
        this.produto = produto;
    }

    public EPedido getPedido() {
        return pedido;
    }

    public void setPedido(EPedido pedido) {
        this.pedido = pedido;
    }

    public EItemPedido() {
        
        pedido = new EPedido();
        produto = new EProduto();
        
    }
    
    
    
}
