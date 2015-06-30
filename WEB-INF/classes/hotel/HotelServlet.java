package hotel;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.*; 
import java.text.*;
import java.lang.Integer;
import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelServlet extends HttpServlet {

	private static SessionFactory sessionFactory;


	public void init () {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public void destroy() {
		sessionFactory.close();
	}

	public void doGet (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();

		Session sessionBD = sessionFactory.openSession();
		Transaction tx = sessionBD.beginTransaction();

		try{

			Hotel hotel = (Hotel) sessionBD.get(Hotel.class, 0);

			session.setAttribute("hotel",hotel);

		    sessionBD.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/hotel.jsp");
			dispatcher.forward(request, response);

		}
		catch(Exception e){
			e.printStackTrace();
		}	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();

		Session sessionBD = sessionFactory.openSession();
		Transaction tx = sessionBD.beginTransaction();

		try{

			Hotel hotel = (Hotel) sessionBD.get(Hotel.class, 0);

			String numQuartos = request.getParameter("numQuartos");

			if(numQuartos != null){
				hotel.setNumero_quartos(Integer.parseInt(numQuartos));
			}

			sessionBD.update(hotel);

			tx.commit();
		    sessionBD.close();

			session.setAttribute("hotel",hotel);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/hotel.jsp");
			dispatcher.forward(request, response);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}