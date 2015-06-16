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

		ArrayList<Reserva>reservas = session.getAttribute("reservas");
		Hotel hotel = session.getAttribute("hotel");

		ArrayList<String> diasOcupados = new ArrayList<String>(); 

		hoje = new Date();
		max = obtemUltimaDataReserva(reservas);
		numQuartos = hotel.getNumeroQuartos();



		Calendar calendar = new GregorianCalendar();
	    calendar.setTime(hoje);

	    // Percorre todos os dias entre hoje e a data máxima
	    while (calendar.getTime().before(max) || calendar.getTime().equals(max))
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

		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reserva/index.jsp");
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void doPost (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		iniciaDados(session);

		ArrayList<Reserva>reservas = session.getAttribute("reservas");

		/* Salva uma nova reserva */
		Reserva reserva = new Reserva();

		// Seria necessario verificar do lado do servidor, mas ja verificamos no lado do cliente

		// Obtem as datas de checkin/checkout de acordo com o formato especificado
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		reserva.setCheckin(format.parse(request.getParameter("dataEntrada")));
		reserva.setCheckout(format.parse(request.getParameter("dataSaida")));

		//Obtem os outros parametros da reserva
		reserva.setNumeroAdultos(request.getParameter("adultos"));
		reserva.setNumeroBebes(request.getParameter("criancasAte3"));
		reserva.setNumeroCriancas(request.getParameter("criancasAte12"));

		//Savla a reserva
		reservas.add(reserva);

		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reserva/confirma.jsp" + url);
			dispatcher.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}

	}



	private Date obtemUltimaDataReserva(ArrayList<Reserva> reservas){

		Date max = new Date();

		for(Reserva reserva : reservas){
			if(reserva.getCheckout().compareTo(max)){
				max = reserva.getCheckin();
			}
		}

		return max;

	}

	private int obtemNumeroReservasNoDia(ArrayList<Reserva> reservas, Date dia){
		int numReservas = 0;

		for(Reserva reserva : reservas){

			if(dia.equals(reserva.getCheckin()) || dia.equals(reserva.getCheckout())){
				numReservas++;
			}else if(dia.after(reserva.getCheckin()) && dia.before(reserva.getCheckout())){
				numReservas++;
			}

		}

		return numReservas;
	}

	private String stringFromDate(date dia){

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(today);
	}
}