package br.prime.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.prime.model.dao.ConectaBanco;
import br.prime.model.dao.UsuarioDAO;

@WebServlet("/ProdutosControler")
public class ProdutosControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProdutosControler() {
        super();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("produtos", new UsuarioDAO().consultaProdutos());	
		try{
			ConectaBanco.getConnection().close();
			System.out.println("Conexão Encerrada!");
		}catch(SQLException ex){
			System.out.println("Conexão não encerrada!");
		}		
	}

}
