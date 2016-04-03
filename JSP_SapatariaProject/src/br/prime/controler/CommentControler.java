package br.prime.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.prime.model.Usuario;
import br.prime.model.dao.ConectaBanco;
import br.prime.model.dao.UsuarioDAO;

@WebServlet("/CommentControler")
public class CommentControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CommentControler() {
        super();
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("txtNome").equals("") && !request.getParameter("txtEmail").equals("") && (!request.getParameter("txtEmail").equals("") || !request.getParameter("txtEmail").equals("Digite aqui a sua mensagem"))){
				Usuario user = new Usuario();
				user.setNome(request.getParameter("txtNome"));
				user.setEmail(request.getParameter("txtEmail"));
				user.setTipoComment(Integer.parseInt(request.getParameter("op")));
				user.setRecebeAnuncio(request.getParameter("checkYes"));
				user.setMensagem(request.getParameter("txtMensagem"));				
				System.out.println(new UsuarioDAO().cadastraComment(user));
				try{
					ConectaBanco.getConnection().close();
					System.out.println("Conexão Encerrada!");
				}catch(SQLException ex){
					System.out.println("Conexão não encerrada!");
				}	
				request.setAttribute("sucesso", true);
				request.getRequestDispatcher("conteudoContato.jsp").forward(request, response);				
		}else{
			try{
				ConectaBanco.getConnection().close();
				System.out.println("Conexão Encerrada!");
			}catch(SQLException ex){
				System.out.println("Conexão não encerrada!");
			}	
			request.setAttribute("erro", "Por favor, preencha todos os campos obrigatórios.");
			request.getRequestDispatcher("erro.jsp").forward(request, response);	
		}
	}
}
