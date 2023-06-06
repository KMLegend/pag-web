/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author heube
 */
public class EPedido {

    private int id;
    private double valortotal;
    private Date dataPedido;
    private ERevista pessoa;
    private List<EItemPedido> itensPedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public ERevista getPessoa() {
        return pessoa;
    }

    public void setPessoa(ERevista pessoa) {
        this.pessoa = pessoa;
    }

    public List<EItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<EItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public EPedido() {
        pessoa = new ERevista();
        itensPedido = new ArrayList<EItemPedido>();
    }

}
