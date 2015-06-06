package hotel;

import java.util.*; 

public class Mensagem implements java.io.Serializable{
   
	private int id;

	private String nome;
	private String email;
    private String celular;
    private String mensagem;

	private ArrayList conheceu;

    private boolean lida;

	public Mensagem(){
	}

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public String getMensagem() {
        return mensagem;
    }

    public ArrayList getConheceu() {
        return conheceu;
    }

    public boolean isLida(){
        return lida;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setConheceu(ArrayList conheceu) {
        this.conheceu = conheceu;
    }

    public void setLida(boolean lida){
        this.lida = lida;
    }

}
