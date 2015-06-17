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

		ArrayList<Reserva> reservas = (ArrayList<Reserva>) session.getAttribute("reservas");
		ArrayList<Reserva> reservasFiltradas = (ArrayList<Reserva>) session.getAttribute("reservasFiltradas");


		String detalhe = (String) request.getParameter("detalhe");
		String filtro = (String) request.getParameter("nameFilter");

		/* Retorna a página com as reservas - com paginação */
		if(request.getParameter("rid") != null){
			int rid = Integer.parseInt(request.getParameter("rid")); 
			Reserva reserva = reservas.get(rid);

			session.setAttribute("reserva", reserva);
		}


		else if(filtro != null){
			// Filtered array list that contains the required users
			reservasFiltradas = new ArrayList<Reserva>();

			// If no filter is requried
			if(filtro.equals("")){

				
				for(int i = 0; i < reservas.size(); i++){
					reservasFiltradas.add(reservas.get(i));
				}
				session.setAttribute("reservasFiltradas", reservasFiltradas);
				session.setAttribute("filtro_r", "");
			}

			// If we are using a name as a filter
			else {

				System.out.println(filtro);

				for(int i = 0; i < reservas.size(); i++){
					if(reservas.get(i).getNome().contains(filtro)){
						reservasFiltradas.add(reservas.get(i));
						System.out.println(reservas.get(i).getNome());
					}
				}

				session.setAttribute("filtro_r", filtro);
				session.setAttribute("reservas", reservasFiltradas);
			}

			try{

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/reservas.jsp");
				dispatcher.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		/* De acordo com os parametroes mostra cada página. */
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);

		/* Salva uma nova reserva */

	}

}