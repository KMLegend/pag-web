/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.ERevista;
import model.negocio.NRevista;

/**
 *
 * @author heube
 */
@WebServlet(name = "CRevista", urlPatterns = {"/CRevista"})
public class CRevista extends HttpServlet {

    private NRevista nRevista;
    private static final String MANTER = "manterRevista.jsp";
    private static final String LISTAR = "listarRevista.jsp";

    public CRevista() {
        super();
        nRevista = new NRevista();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String avancar = "";

        if (acao.equalsIgnoreCase("listar")) {

            List<ERevista> lista = nRevista.listar();
            request.setAttribute("lista", lista);

            avancar = LISTAR;

        } else if (acao.equalsIgnoreCase("excluir")) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            nRevista.excluir(codigo);

            List<ERevista> lista = nRevista.listar();
            request.setAttribute("lista", lista);

            avancar = LISTAR;

        } else if (acao.equalsIgnoreCase("alterar")) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            ERevista pessoa = nRevista.consultar(codigo);

            request.setAttribute("pessoa", pessoa);
            avancar = MANTER;

        } else if (acao.equalsIgnoreCase("incluir")) {
            avancar = MANTER;

        } else {
            avancar = LISTAR;
        }

        RequestDispatcher pagina = request.getRequestDispatcher(avancar);
        pagina.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String idade = request.getParameter("idade");

        ERevista pessoa = new ERevista();

        if (id != null && !id.isEmpty()) {
            pessoa.setId(Integer.parseInt(id));
        }
        pessoa.setNome(nome);
        if (idade != null && !idade.isEmpty()) {
            pessoa.setIdade(Integer.parseInt(idade));
        }

        try {
            nRevista.salvar(pessoa);
        } catch (Exception ex) {
            Logger.getLogger(CRevista.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("lista", nRevista.listar());

        RequestDispatcher pagina = request.getRequestDispatcher(LISTAR);
        pagina.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
