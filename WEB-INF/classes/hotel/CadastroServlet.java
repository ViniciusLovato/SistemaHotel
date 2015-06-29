package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import java.lang.String.*;
import static java.util.concurrent.TimeUnit.*;
import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class CadastroServlet extends HttpServlet {

	private static SessionFactory sessionFactory;


	public void init () {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public void destroy() {
		sessionFactory.close();
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		String url = "login/index.jsp";

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
		usuario.setData_cadastro(new Date());
		usuario.setTentativasAcesso(new HashSet<Date>());

		try{

			Session sessionBD = sessionFactory.openSession();
			Transaction tx = sessionBD.beginTransaction();

			sessionBD.save(usuario);
			tx.commit();

			Usuario usr = (Usuario) sessionBD.load(Usuario.class, "lovato@gmail.com");

			System.out.println("Usuario: " + usr.getNome());

			sessionBD.close();

			// session.setAttribute("usuarios", usuarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/" + url);
			dispatcher.forward(request, response);

		// This exception is throw by the request dispatcher
		}catch(ServletException se){
			se.printStackTrace();
		}

		// This exception is throw by the request dispatcher
		catch(IOException ioe){
			ioe.printStackTrace();
		}

		// Exception when there is some problem while saving the data
		catch(javax.persistence.RollbackException cve){
			// We have to check if the root cause of this exception
			System.out.println("Violacao de chave primaria - Email ja cadastrado!");
			// Deve redirecionar para uma pagina de erro ou algo do tipo			
		}


	}

	public void doGet (HttpServletRequest request, HttpServletResponse response){
		
		/* se nao existe lista de usuarios na sessao, entao criar uma */				
		HttpSession session = request.getSession();
	    ArrayList<Usuario> usuariosFiltrados = null;
	    ArrayList<Usuario> usuarios = null;

		/* if(session.getAttribute("usuarios") == null) {			
			session.setAttribute("usuarios",Dados.iniciaUsuarios());
		}*/

		// Recuperando usuarios do banco de dados pra listar para o admin
		try{

			Session sessionBD = sessionFactory.openSession();
			Transaction tx = sessionBD.beginTransaction();

			usuarios = (ArrayList<Usuario>) sessionBD.createQuery("from Usuario").list();

			sessionBD.close();

			// session.setAttribute("usuarios", usuarios);

		// Exception when there is some problem while saving the data
		}catch(javax.persistence.RollbackException cve){
			// We have to check if the root cause of this exception
			// System.out.println("Violacao de chave primaria - Email ja cadastrado!");
			// Deve redirecionar para uma pagina de erro ou algo do tipo			
		}

		String detalhe = (String) request.getParameter("detalhe");
		String filtro = (String) request.getParameter("nameFilter");

		// See the selected user details
		if(detalhe != null){
			System.out.println("User not null");

			int indice = (int) Integer.parseInt(detalhe);

			Usuario usuarioDetalhado = (Usuario) usuariosFiltrados.get(indice);

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
			usuariosFiltrados = new ArrayList<Usuario>();

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

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
				dispatcher.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
			}	
		}

	}
}
