package br.com.fitponto.usuario;

public class Usuario {
	int id,perfil,chefeid;
	int numAprovados,numReprovados,numPendentes;
	
	String login,senha,nome,cargo,cpf,tituloPerfil;
	String horasTrabForm,horasTrabPerc;
	String percAprovados,percReprovados,percPendentes;
	
	
	public int getNumAprovados() {
		return numAprovados;
	}
	public void setNumAprovados(int numAprovados) {
		this.numAprovados = numAprovados;
	}
	public int getNumReprovados() {
		return numReprovados;
	}
	public void setNumReprovados(int numReprovados) {
		this.numReprovados = numReprovados;
	}
	public int getNumPendentes() {
		return numPendentes;
	}
	public void setNumPendentes(int numPendentes) {
		this.numPendentes = numPendentes;
	}
	public String getPercAprovados() {
		return percAprovados;
	}
	public void setPercAprovados(String percAprovados) {
		this.percAprovados = percAprovados;
	}
	public String getPercReprovados() {
		return percReprovados;
	}
	public void setPercReprovados(String percReprovados) {
		this.percReprovados = percReprovados;
	}
	public String getPercPendentes() {
		return percPendentes;
	}
	public void setPercPendentes(String percPendentes) {
		this.percPendentes = percPendentes;
	}
	public String getHorasTrabPerc() {
		return horasTrabPerc;
	}
	public void setHorasTrabPerc(String horasTrabPerc) {
		this.horasTrabPerc = horasTrabPerc;
	}
	public String getHorasTrabForm() {
		return horasTrabForm;
	}
	public void setHorasTrabForm(String horasTrabForm) {
		this.horasTrabForm = horasTrabForm;
	}
	public String getTituloPerfil() {
		return tituloPerfil;
	}
	public void setTituloPerfil(String tituloPerfil) {
		this.tituloPerfil = tituloPerfil;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPerfil() {
		return perfil;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	public int getChefeid() {
		return chefeid;
	}
	public void setChefeid(int chefeid) {
		this.chefeid = chefeid;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
