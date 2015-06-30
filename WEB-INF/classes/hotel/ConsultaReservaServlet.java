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

		try{

			ArrayList<Reserva> reservas = (ArrayList<Reserva>) sessionBD.createQuery("from Reserva reserva where lower(reserva.email) like lower('%"+user.getEmail()+"%') ").list();

			session.setAttribute("reservas",reservas);

			tx.commit();

			sessionBD.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/reserva/lista.jsp");
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