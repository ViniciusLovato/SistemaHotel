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

public class ConsultaReservaServlet extends HttpServlet {

	private static SessionFactory sessionFactory;


	public void init () {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public void destroy() {
		sessionFactory.close();
	}

	private void iniciaDados(HttpSession session){
		if(session.getAttribute("hotel") == null) {			
			session.setAttribute("hotel",Dados.iniciaHotel());
		}
	}

	
	public void doGet (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);

		Usuario user = (Usuario) session.getAttribute("usuario");

		Session sessionBD = sessionFactory.openSession();
		Transaction tx = sessionBD.beginTransaction();

		ArrayList<Reserva> reservas = null;
		ArrayList<Reserva> reservasFiltradas = new ArrayList<Reserva>();

		String url = null;

		try{

			if(user.getTipo() != null && user.getTipo().equals("admin")){
				reservas = (ArrayList<Reserva>) sessionBD.createQuery("from Reserva").list();
				session.setAttribute("reservas", reservas);
				url = "/admin/reservas.jsp";

			}
			else {
				reservas = (ArrayList<Reserva>) sessionBD.createQuery("from Reserva reserva where lower(reserva.email) like lower('%"+user.getEmail()+"%') ").list();

				session.setAttribute("reservas", reservas);
				url = "/reserva/lista.jsp";

				System.out.println("Usuario Comum!\n");
			}

			System.out.println("Numero de reservas" + reservas.size());


		}catch(Exception e){
			e.printStackTrace();
		}


		if(request.getParameter("nameFilter") != null){

			String filter = request.getParameter("nameFilter");

			// If no filter is requried
			if(filter.equals("")){
				filter = "";

				for(int i = 0; i < reservas.size(); i++){
					reservasFiltradas.add(reservas.get(i));
				}


			}
			else{

				for(int i = 0; i < reservas.size(); i++){
					if(reservas.get(i).getEmail().contains(filter)){
						reservasFiltradas.add(reservas.get(i));
						}
				}
			}

			session.setAttribute("filter", filter);
			session.setAttribute("reservasFiltradas", reservasFiltradas);

		}
		// Removing selected reservations
		else if(request.getParameter("remover") != null && request.getParameter("remover").equals("true")) {

			/* */
			reservasFiltradas = (ArrayList<Reserva>) session.getAttribute("reservasFiltradas");
			for(int i = reservasFiltradas.size() - 1; i >= 0; i--){
				
				String selected = (String) request.getParameter("checkbox" + i);
			
				if(selected !=null && selected.equals("on")){
					
					int id = reservasFiltradas.get(i).getId();
					sessionBD.createQuery("delete from Reserva where id='" + Integer.toString(id) + "'").executeUpdate();

				}
				// session.setAttribute("usuarios", usuarios);
			}

			try{

				reservas = (ArrayList<Reserva>) sessionBD.createQuery("from Reserva").list();
				session.setAttribute("reservasFiltradas", reservasFiltradas);

				tx.commit();
				sessionBD.close();
		

				url = "/admin/index.jsp";

			}catch(Exception e){
				e.printStackTrace();
			}
		}

		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}

		// De acordo com os  mostra cada página.
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		try{

			HttpSession session = request.getSession();


		Usuario user = (Usuario) session.getAttribute("usuario");

			Session sessionBD = sessionFactory.openSession();
			Transaction tx = sessionBD.beginTransaction();


			if (request.getParameter("acao").equals("remover")){

				String id = request.getParameter("id");

				Reserva reserva = (Reserva) sessionBD.get(Reserva.class, Integer.parseInt(id));

				System.out.println("\n\nRemoção\n\n");
				System.out.println(reserva);
				System.out.println(id);
				System.out.println(reserva.getCheckin());

				sessionBD.delete(reserva);

			}

			ArrayList<Reserva> reservas = (ArrayList<Reserva>) sessionBD.createQuery("from Reserva reserva where lower(reserva.email) like lower('%"+user.getEmail()+"%') ").list();

			session.setAttribute("reservas",reservas);

			tx.commit();
			sessionBD.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/reserva/lista.jsp");
			dispatcher.forward(request, response);


		}catch(Exception e){
			e.printStackTrace();
		}	

	}

}