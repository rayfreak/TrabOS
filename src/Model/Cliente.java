package Model;

public class Cliente {

	static private String nome, cpf, telefone, cep , email, telefone2;
	static private int id;
	
	public String getCpf(){
		return cpf;
	}
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	public String getNome(){
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getCep(){
		return cep;
	}
	public void setCep(String cep){
		this.cep = cep;
	}
	public String getTelefone(){
		return telefone;
	}
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

  public Cliente(){
	
  }
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getTelefone2() {
	return telefone2;
}
public void setTelefone2(String telefone2) {
	this.telefone2 = telefone2;
}

}
 