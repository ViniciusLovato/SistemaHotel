package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import static java.util.concurrent.TimeUnit.*;



public class CadastroServlet extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		
		String url = "login/index.html";

		/* se nao existe lista de usuarios na sessao, entao criar uma */				
		HttpSession session = request.getSession();
		if(session.getAttribute("usuarios") == null) {			
			session.setAttribute("usuarios",Dados.iniciaUsuarios());
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
		usuario.setDataCadastro(new Date());

		usuarios.add(usuario);

		session.setAttribute("usuarios", usuarios);
		

		try{

			RequestDispatcher dispatcher = request.getRequestDispatcher("/" + url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doGet (HttpServletRequest request, HttpServletResponse response){
		
		/* se nao existe lista de usuarios na sessao, entao criar uma */				
		HttpSession session = request.getSession();
		if(session.getAttribute("usuarios") == null) {			
			session.setAttribute("usuarios",Dados.iniciaUsuarios());
		}

		ArrayList usuarios = (ArrayList) session.getAttribute("usuarios");


		String detalhe = (String) request.getParameter("detalhe");
		if(detalhe != null){
			System.out.println("User not null");
			
			int indice = (int) Integer.parseInt(detalhe);
			Usuario usuarioDetalhado = (Usuario) usuarios.get(indice);

			session.setAttribute("usuarioDetalhado", usuarioDetalhado);

			try{

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/usuarioDetalhe.jsp");
				dispatcher.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
			}		

		}
		else {

			/* */
			for(int i = usuarios.size() - 1; i >= 0; i--){
				String selected = (String) request.getParameter("checkbox" + i);
				System.out.println(i + " " +  selected + " -> selected\n");

				if(selected !=null && selected.equals("on")){
					usuarios.remove(i);
				}
				session.setAttribute("usuarios", usuarios);
			}

			try{

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/usuarios.jsp");
				dispatcher.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
			}	
		}

	}
}
