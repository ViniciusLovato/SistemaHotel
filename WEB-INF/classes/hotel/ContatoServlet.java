package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;

public class ContatoServlet extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response){
	
		String url = "index.jsp";

		/* se nao existe lista de mensagens na sessao, entao criar uma */				
		HttpSession session = request.getSession();
		if(session.getAttribute("mensagens") == null) {
			session.setAttribute("mensagens", new ArrayList());
		}

		ArrayList mensagens = (ArrayList) session.getAttribute("mensagens");

		// nova mensagem a ser gravada
		Mensagem mensagem = new Mensagem();

		// Seria necessario verificar do lado do servidor, mas ja verificamos no lado do cliente
		mensagem.setNome(request.getParameter("nome"));
		mensagem.setEmail(request.getParameter("email"));

		if(request.getParameter("celular") != null){
			mensagem.setCelular(request.getParameter("celular"));
		}

		mensagem.setMensagem(request.getParameter("mensagem"));

		// mensagem.setConheceu(request.getParameter("indicacao[]"));
		System.out.println("Nome: " + mensagem.getNome() );
		System.out.println("Email: " + mensagem.getEmail() );
		System.out.println("mensagem: " + mensagem.getMensagem() );

		mensagens.add(mensagem);

		// coloca o arraylist de mensagens com a nova mensagem na sessao
		session.setAttribute("mensagens", mensagens);

		try{

			RequestDispatcher dispatcher = request.getRequestDispatcher("/" + url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){

	}
}
