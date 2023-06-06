/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import java.util.List;
import model.entidade.ERevista;
import model.persistencia.PRevista;

public class NRevista {

    PRevista persistencia;

    public NRevista() {
        persistencia = new PRevista();
    }

    public void salvar(ERevista revista) throws Exception {

        if (revista.getIdade() < 18) {
            throw new Exception("Não é permitido revistas menores de idade.");
        }

        PRevista persistencia = new PRevista();

        if (revista.getId() == 0) {
            //Incluir
            persistencia.incluir(revista);
        } else {
            //alterar
            persistencia.alterar(revista);

        }

    }

    public void excluir(int id){
        persistencia.excluir(id);
    }
    
    public ERevista consultar(int id){
        return persistencia.consultar(id);
    }
    
    public List<ERevista> listar(){
        return persistencia.listar();
    }
}
