package hotel;

import java.util.*; 

public class Reserva {
   
	private int id;
    private String nome;

    private Date checkin;
    private Date checkout;

    // Considerado pela aplicacao aqueles maiores de 12 anos
    private int numeroAdultos;

    // Considerado pela aplicacao aqueles entre 4 e 12 anos
    private int numeroCriancas;

    // Considerao menores de 3 anos
    private int numeroBebes;

    public Reserva(){
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCheckin(Date checkin){
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout){
        this.checkout = checkout;
    }

    public void setNumeroAdultos(int numeroAdultos){
        this.numeroAdultos = numeroAdultos;
    }

    public void setNumeroCriancas(int numeroCriancas){
        this.numeroCriancas = numeroCriancas;
    }

    public void setNumeroBebes(int numeroBebes){
        this.numeroBebes = numeroBebes;
    }

    public Date getCheckin(){
        return this.checkin;
    }
    
    public Date getCheckout(){
        return this.checkout;
    }

    public int getNumeroAdultos(){
        return this.numeroAdultos;
    }

    public int getNumeroCriancas(){
        return this.numeroCriancas;
    }

    public int getNumeroBebes(){
        return this.numeroBebes;
    }

    public String getNome(){
        return this.nome;
    }
}
