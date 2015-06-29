package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;
import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class ContatoServlet extends HttpServlet {

	private static SessionFactory sessionFactory;


	public void init () {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public void destroy() {
		sessionFactory.close();
	}


	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		String url = "index.jsp";

		/* se nao existe lista de mensagens na sessao, entao criar uma */				
		/*HttpSession session = request.getSession();
		if(session.getAttribute("mensagens") == null) {
			session.setAttribute("mensagens", new ArrayList());
		}*/

		// ArrayList mensagens = (ArrayList) session.getAttribute("mensagens");

		// nova mensagem a ser gravada
		Mensagem mensagem = new Mensagem();

		// Seria necessario verificar do lado do servidor, mas ja verificamos no lado do cliente
		mensagem.setNome(request.getParameter("nome"));
		mensagem.setEmail(request.getParameter("email"));

		if(request.getParameter("celular") != null){
			mensagem.setCelular(request.getParameter("celular"));
		}

		mensagem.setMensagem(request.getParameter("mensagem"));


		mensagem.setDataEnvio(new Date());
		mensagem.setLida(false);


		try{

			Session sessionBD = sessionFactory.openSession();
			Transaction tx = sessionBD.beginTransaction();

			sessionBD.save(mensagem);
			tx.commit();

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

		// mensagens.add(mensagem);

		// coloca o arraylist de mensagens com a nova mensagem na sessao
		// Collections.sort(mensagens);
		// session.setAttribute("mensagens", mensagens);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){

		/* se nao existe lista de mensagens na sessao, entao criar uma */				
		HttpSession session = request.getSession();
		if(session.getAttribute("mensagens") == null) {
			session.setAttribute("mensagens", new ArrayList());
		}

		ArrayList mensagens = (ArrayList) session.getAttribute("mensagens");
		Mensagem mensagem;

		String detalhe = (String) request.getParameter("detalhe");

		String url;

		if(detalhe != null){

			url = "admin/mensagemDetalhe.jsp";
			int mId = Integer.parseInt(detalhe);

			mensagem = (Mensagem) mensagens.get(mId);
			mensagem.setLida(true);

			session.setAttribute("mensagens", mensagens);
			session.setAttribute("mensagem", mensagem);


		}

		else {

			/* */
			for(int i = mensagens.size() - 1; i >= 0; i--){
				String selected = (String) request.getParameter("checkbox" + i);
				System.out.println(i + " " +  selected + " -> selected\n");

				if(selected !=null && selected.equals("on")){
					mensagens.remove(i);
				}
			}

			session.setAttribute("mensagens", mensagens);
			url = "/admin/mensagens.jsp";

		}

		try{

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
