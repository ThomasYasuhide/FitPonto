package br.com.fitponto.tarefa;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fitponto.projeto.Projeto;
import br.com.fitponto.projeto.ProjetoDAO;
import br.com.fitponto.tarefa.Tarefa;
import br.com.fitponto.tarefa.TarefaDAO;

@Controller
public class TarefaController {

	@RequestMapping("/adicionarTarefa")
	public ModelAndView adicionar(Tarefa tarefa){
		TarefaDAO dao = new TarefaDAO();
		dao.inserir(tarefa);
		
		ModelAndView mv = new ModelAndView("forward:listaTarefa");
		mv.addObject("status","tarefacadastrada");
		return mv;
	}
	
	@RequestMapping("/alterarTarefa")
	public ModelAndView alterar(Tarefa tarefa){
		TarefaDAO dao = new TarefaDAO();
		dao.atualizar(tarefa);
		
		ModelAndView mv = new ModelAndView("forward:listaTarefa");
		mv.addObject("status","tarefaalterada");
		return mv;
	}
	
	@RequestMapping("/removerTarefa")
	public ModelAndView remover(Tarefa tarefa){
		TarefaDAO dao = new TarefaDAO();
		dao.deletar(tarefa);
		
		ModelAndView mv = new ModelAndView("forward:listaTarefa");
		mv.addObject("status","tarefaremovida");
		return mv;
	}
	
	@RequestMapping("/listaTarefa")
	public ModelAndView listar(HttpSession session){
		if(session.getAttribute("sessionPerfil").equals(2)){
			TarefaDAO dao = new TarefaDAO();
			List<Tarefa> tarefa = dao.listar();
			
			ProjetoDAO projetoDAO = new ProjetoDAO();
			List<Projeto> projeto = projetoDAO.listar();
			
			ModelAndView mv = new ModelAndView("chefe/tarefas");
			mv.addObject("tarefas", tarefa);
			mv.addObject("projetos",projeto);
			
			return mv;
		}
		
		if(session.getAttribute("sessionPerfil").equals(3)){
			TarefaDAO dao = new TarefaDAO();
			List<Tarefa> tarefa = dao.listar();
			
			ProjetoDAO projetoDAO = new ProjetoDAO();
			List<Projeto> projeto = projetoDAO.listar();
			
			ModelAndView mv = new ModelAndView("diretor/tarefas");
			mv.addObject("tarefas", tarefa);
			mv.addObject("projetos",projeto);
			
			return mv;
		}
		
		return new ModelAndView("redirect:login");
	}
	
}
