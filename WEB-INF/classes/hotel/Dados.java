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
		u.setDataCadastro(new Date());
		u.setTentativasAcesso(new ArrayList<Date>());

		usuarios.add(u);

		return usuarios;

	}

	public static Hotel iniciaHotel(){
		Hotel hotel = new Hotel();
		hotel.setNumeroQuartos(5);

		return hotel;
	}

	public static ArrayList iniciaReservas(){

		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		return reservas;

	}
}