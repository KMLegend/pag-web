/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import model.entidade.ERevista;
import model.persistencia.PRevista;

/**
 *
 * @author heube
 */
public class TRevista {

    public static void main(String[] args) {

        PRevista persistencia = new PRevista();
        ERevista entidade = new ERevista();

        entidade.setNome("Fulano de tal ALTERADO");
        entidade.setIdade(35);
        entidade.setId(3);

        System.out.println("Vai incluir!");
        persistencia.incluir(entidade);
        System.out.println("Terminou!");
        
        //System.out.println("Vai ALTERAR!");
        //persistencia.alterar(entidade);
        //System.out.println("Terminou!");
        //System.out.println("Vai EXCLUIR!");
        //persistencia.excluir(3);
        //System.out.println("Terminou!");
//        ERevista retorno = persistencia.consultar(1);
//        
//        System.out.println("Consultando a pessoa ");
//        System.out.println("ID.......: " + retorno.getId());
//        System.out.println("Nome.....: " + retorno.getNome());
//        System.out.println("Idade....: " + retorno.getIdade());
//     
        List<ERevista> lista = persistencia.listar();

        System.out.println("Listando as pessoas ");

        for (ERevista pessoa : lista) {
            
            System.out.println("ID.......: " + pessoa.getId());
            System.out.println("Nome.....: " + pessoa.getNome());
            System.out.println("Idade....: " + pessoa.getIdade());
            System.out.println("");
        }

    }

}
