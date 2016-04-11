package br.com.fitponto.apontamento;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Apontamento {
	private int id;
	private int userID;
	private String user;
	private int tarefaID;
	private String tarefa;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime inicio;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fim;
	private String horaTotal;
	private String obs;
	private String status;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public int getTarefaID() {
		return tarefaID;
	}

	public void setTarefaID(int tarefaID) {
		this.tarefaID = tarefaID;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	
	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}

	public String getHoraTotal() {
		return horaTotal;
	}

	public void setHoraTotal(String horaTotal) {
		this.horaTotal = horaTotal;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
