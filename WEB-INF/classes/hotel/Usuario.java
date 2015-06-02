package hotel;

public class Usuario {
   
	private int id;
	private String tipo;

	private String nome;
	private String email;
	private String cpf;
	private String data_nascimento;
	private String sexo;
	private String estado_civil;

	private String cidade;
	private String estado;
	private String cep;

	private String senha;


	public Usuario(){
	}

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
