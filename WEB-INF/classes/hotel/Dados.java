package hotel;

import java.io.*;  
import java.util.*; 
import java.lang.Integer;



public class Dados {

	public static ArrayList iniciaUsuarios(){

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		Usuario u = new Usuario();
		u.setEmail("admin@admin.com");

		u.setSenha("admin");
		u.setNome("Mr. Admin");
		u.setData_cadastro(new Date());
		u.setTentativasAcesso(new HashSet<Date>());

		usuarios.add(u);

		return usuarios;

	}

	public static Hotel iniciaHotel(){
		Hotel hotel = new Hotel();
		hotel.setNumero_quartos(1);

		return hotel;
	}

	public static ArrayList iniciaReservas(){

		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		return reservas;

	}
}