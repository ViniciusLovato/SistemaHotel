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

public class ReservaServlet extends HttpServlet {

	private static SessionFactory sessionFactory;


	public void init () {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public void destroy() {
		sessionFactory.close();
	}

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

		Session sessionBD = sessionFactory.openSession();
		Transaction tx = sessionBD.beginTransaction();

		try{

			/* Mostra a pagina de nova reserva com os dias disponvíveis. */
			ArrayList<Reserva> reservas = (ArrayList<Reserva>) sessionBD.createQuery("from Reserva").list();

			//ArrayList<Reserva> reservas = (ArrayList<Reserva>) session.getAttribute("reservas");
			Hotel hotel = (Hotel) sessionBD.get(Hotel.class, 0);

			//Hotel hotel = (Hotel) sessionBD.createQuery("from Hotel");

			ArrayList<String> diasOcupados = new ArrayList<String>(); 

			Date hoje = new Date();
			Date max = obtemUltimaDataReserva(reservas);
			int numQuartos = hotel.getNumero_quartos();

			Calendar calendar = new GregorianCalendar();
		    calendar.setTime(hoje);

		    // Percorre todos os dias entre hoje e a data máxima
		    while (calendar.getTime().before(max) || stringFromDate(calendar.getTime()).equals(stringFromDate(max)))
		    {
		        Date dia = calendar.getTime();

		        // Verifica quantidade de reservas nesse dia. Caso seja maior
		        // que o numero de quartos, este dia é vermelho
		        if (obtemNumeroReservasNoDia(reservas,dia) >= numQuartos){

		        	// Insere o dia ocupado no formato dd/mm/aaaa 
		        	diasOcupados.add(stringFromDate(dia));
		        }

		        calendar.add(Calendar.DATE, 1);

		    }

		    /* Insere a lista com dias ocupados na sessão*/
		    session.setAttribute("diasOcupados",diasOcupados);


		    for (int i = 0; i < diasOcupados.size(); i++){
		    	System.out.println(diasOcupados.get(i));
		    }

		    sessionBD.close();


			RequestDispatcher dispatcher = request.getRequestDispatcher("/reserva/index.jsp");
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);

		

		// ArrayList<Reserva> reservas = (ArrayList<Reserva>) session.getAttribute("reservas");

		/* Salva uma nova reserva */
		Reserva reserva = new Reserva();

		// Seria necessario verificar do lado do servidor, mas ja verificamos no lado do cliente

		// Obtem as datas de checkin/checkout de acordo com o formato especificado
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Usuario user = (Usuario) session.getAttribute("usuario");

		try{

			reserva.setEmail(user.getEmail());
			reserva.setCheckin(format.parse(request.getParameter("dataEntrada")));
			reserva.setCheckout(format.parse(request.getParameter("dataSaida")));

			//Obtem os outros parametros da reserva
			reserva.setNumeroAdultos(Integer.parseInt(request.getParameter("adultos")));
			reserva.setNumeroBebes(Integer.parseInt(request.getParameter("criancasAte3")));
			reserva.setNumeroCriancas(Integer.parseInt(request.getParameter("criancasAte12")));

		
		
			//Savla a reserva
			/*reservas.add(reserva);*/

			session.setAttribute("reserva",reserva);
			session.setAttribute("checkin",stringFromDate(reserva.getCheckin()));
			session.setAttribute("checkout",stringFromDate(reserva.getCheckout()));
		
			
			Session sessionBD = sessionFactory.openSession();
			Transaction tx = sessionBD.beginTransaction();

			sessionBD.save(reserva);
			tx.commit();

			sessionBD.close();

			// session.setAttribute("usuarios", usuarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reserva/confirma.jsp");

			//RequestDispatcher dispatcher = request.getRequestDispatcher("/" + url);
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
		catch(ParseException e){
			e.printStackTrace();
		}

	}



	private Date obtemUltimaDataReserva(ArrayList<Reserva> reservas){

		Date max = new Date();

		for(Reserva reserva : reservas){
			if(reserva.getCheckout().after(max)){
				max = reserva.getCheckout();
			}
		}

		return max;

	}

	private int obtemNumeroReservasNoDia(ArrayList<Reserva> reservas, Date dia){
		int numReservas = 0;

		for(Reserva reserva : reservas){

			if( stringFromDate(dia).equals(stringFromDate(reserva.getCheckin())) || stringFromDate(dia).equals(stringFromDate(reserva.getCheckout()))){
				numReservas++;
			}else if(dia.after(reserva.getCheckin()) && dia.before(reserva.getCheckout())){
				numReservas++;
			}

		}

		return numReservas;
	}

	private String stringFromDate(Date dia){

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dia);
	}
}