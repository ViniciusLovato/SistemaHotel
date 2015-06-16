package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.lang.Integer;

public class ConsultaReservaServlet extends HttpServlet {

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


		/* Retorna a página com as reservas - com paginação */
		if(request.getParameter("rid") != null && request.getParameter("json").equals("true") request.getParameter("rid") != null && request.getParameter("json").equals("true")){


		}
		/* De acordo com os parametroes mostra cada página. */

	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);

		/* Salva uma nova reserva */

	}

}