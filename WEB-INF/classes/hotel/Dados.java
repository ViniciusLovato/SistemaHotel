package hotel;

import java.io.*;  
import java.util.*; 
import java.lang.Integer;



public class Dados {

	public static ArrayList iniciaUsuarios(){

		ArrayList usuarios = new ArrayList();

		Usuario u = new Usuario();
		u.setEmail("admin@admin.com");

		u.setSenha("admin");
		u.setNome("Mr. Admin");
		u.setTentativasAcesso(new ArrayList<Date>());

		usuarios.add(u);

		return usuarios;

	}
}