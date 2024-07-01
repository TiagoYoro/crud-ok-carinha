package Modelo;

public class Cliente {

	private String CPF;
	private String Nome;
	private String Telefone;
	private String Email;
	
	public Cliente(String cPF, String nome, String telefone, String email) {
		super();
		CPF = cPF;
		Nome = nome;
		Telefone = telefone;
		Email = email;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "Cliente [CPF=" + CPF + ", Nome=" + Nome + ", Telefone=" + Telefone + ", Email=" + Email + "]";
	}
	
}
