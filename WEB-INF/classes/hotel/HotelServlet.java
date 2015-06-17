package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.text.*;
import java.lang.Integer;

public class HotelServlet extends HttpServlet {

	private void iniciaDados(HttpSession session){

		if(session.getAttribute("hotel") == null) {			
			session.setAttribute("hotel",Dados.iniciaHotel());
		}
	}


	public void doGet (HttpServletRequest request, HttpServletResponse response){


	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		iniciaDados(session);

		try{

			Hotel hotel = (Hotel) session.getAttribute("hotel");
			String numQuartos = request.getParameter("numQuartos");

			if(numQuartos != null){
				hotel.setNumeroQuartos(Integer.parseInt(numQuartos));
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
			dispatcher.forward(request, response);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}