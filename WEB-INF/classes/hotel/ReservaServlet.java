package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;

public class ReservaServlet extends HttpServlet {

	private void iniciaDados(HttpSession session){
		if(session.getAttribute("reservas") == null) {			
			session.setAttribute("reservas",Dados.iniciaReservas());
		}
		if(session.getAttribute("hotel") == null) {			
			session.setAttribute("hotel",Dados.iniciaHotel());
		}
	}

	public void doGet (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);


		/* Mostra a pagina de nova reserva com os dias disponvíveis. */
		// reservas = session.getAttribute("reservas");

	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);

		/* Salva uma nova reserva */

	}

}