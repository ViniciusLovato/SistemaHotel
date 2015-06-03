package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import static java.util.concurrent.TimeUnit.*;



public class Cadastro extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		
		String url = "login/index.html";

		/* se nao existe lista de usuarios na sessao, entao criar uma */				
		HttpSession session = request.getSession();
		if(session.getAttribute("usuarios") == null) {
			session.setAttribute("usuarios",new ArrayList());
		}

		ArrayList usuarios = (ArrayList) session.getAttribute("usuarios");

		Usuario usuario = new Usuario();

		// Seria necessario verificar do lado do servidor, mas ja verificamos no lado do cliente
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setData_nascimento(request.getParameter("dataNascimento"));
		usuario.setSexo(request.getParameter("sexo"));
		usuario.setEstado_civil(request.getParameter("estadoCivil"));
		usuario.setCidade(request.getParameter("cidade"));
		usuario.setEstado(request.getParameter("estado"));
		usuario.setCep(request.getParameter("cep"));
		usuario.setSenha(request.getParameter("senha"));

		usuarios.add(usuario);

		try{

			RequestDispatcher dispatcher = request.getRequestDispatcher("/" + url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}


	}
}
