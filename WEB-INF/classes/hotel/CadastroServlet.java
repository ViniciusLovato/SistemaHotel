package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import java.lang.String.*;
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
		usuario.setTentativasAcesso(new ArrayList<Date>());

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

		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) session.getAttribute("usuarios");

		String detalhe = (String) request.getParameter("detalhe");
		String filtro = (String) request.getParameter("nameFilter");

		// See the selected user details
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

		// If the admin wants to see users using some filter
		else if(filtro != null){

			// Filtered array list that contains the required users
			ArrayList<Usuario> usuariosFiltrados = new ArrayList<Usuario>();

			// If no filter is requried
			if(filtro.equals("")){

				
				for(int i = 0; i < usuarios.size(); i++){
					usuariosFiltrados.add(usuarios.get(i));
				}
				session.setAttribute("usuariosFiltrados", usuariosFiltrados);
			}

			// If we are using a name as a filter
			else {

				System.out.println(filtro);

				for(int i = 0; i < usuarios.size(); i++){
					if(usuarios.get(i).getNome().contains(filtro)){
						usuariosFiltrados.add(usuarios.get(i));
						System.out.println(usuarios.get(i).getNome());
					}
				}

				session.setAttribute("filtro", filtro);
				session.setAttribute("usuariosFiltrados", usuariosFiltrados);
			}

			try{

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/usuarios.jsp");
				dispatcher.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
			}	
		}

		// Removing selected users
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
