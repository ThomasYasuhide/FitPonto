package br.com.fitponto.apontamento;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fitponto.apontamento.Apontamento;
import br.com.fitponto.apontamento.ApontamentoDAO;
import br.com.fitponto.projeto.Projeto;
import br.com.fitponto.projeto.ProjetoDAO;
import br.com.fitponto.tarefa.Tarefa;
import br.com.fitponto.tarefa.TarefaDAO;

@Controller
public class ApontamentoController {
	
	//#Funcionário
	
	@RequestMapping("/adicionarApontamento") //Adiciona um novo apontamento
	public ModelAndView adicionar(Apontamento apontamento){
		System.out.println("Adicionar um novo apontamento");
		ApontamentoDAO dao = new ApontamentoDAO();
		dao.inserir(apontamento);
		
		
		ModelAndView mv = new ModelAndView("forward:apontamentos"); //Redireciona para o controlador ApontamentoController
		mv.addObject("status","apontamentocadastrado");
		return mv;
		
	}
	
	@RequestMapping("/alterarApontamento") //Altera um apontamento
	public ModelAndView alterar(Apontamento apontamento){
		//System.out.println("Alterar um apontamento");
		ApontamentoDAO dao = new ApontamentoDAO();
		dao.atualizar(apontamento);
		
		ModelAndView mv = new ModelAndView("forward:apontamentos"); //Redireciona para o controlador ApontamentoController
		mv.addObject("status","apontamentoalterado");
		return mv;
	}
	
	@RequestMapping("/removerApontamento") //Remove um apontamento
	public ModelAndView remover(Apontamento apontamento){
		//System.out.println("Remover um apontamento");
		ApontamentoDAO dao = new ApontamentoDAO();
		dao.deletar(apontamento);
		
		ModelAndView mv = new ModelAndView("forward:apontamentos"); //Redireciona para o controlador ApontamentoController
		mv.addObject("status","apontamentoremovido");
		return mv;
	}
	
	@RequestMapping("/apontamentos")
	public ModelAndView listar(HttpSession session){
		//System.out.println("Lista os apontamentos");
		
		ApontamentoDAO apontamentoDAO = new ApontamentoDAO();
		List<Apontamento> apontamento = apontamentoDAO.listar(session);
		
		ProjetoDAO projetoDAO = new ProjetoDAO();
		List<Projeto> projeto = projetoDAO.listar();
		
		TarefaDAO tarefaDAO = new TarefaDAO();
		List<Tarefa> tarefa = tarefaDAO.listar();
		
		ModelAndView mv = new ModelAndView("funcionario/apontamentos");
		mv.addObject("apontamentos", apontamento);
		mv.addObject("projetos", projeto);
		mv.addObject("tarefas", tarefa);
		
		return mv;
	}
	
	//#Chefe / Diretor
	
	@RequestMapping("/listarApontamentoPendente")
	public ModelAndView listarApontamento(HttpSession session){
		if(session.getAttribute("sessionPerfil").equals(2)){
			ApontamentoDAO apontamentoDAO = new ApontamentoDAO();
			List<Apontamento> apontamento = apontamentoDAO.listarPendentes(session);
			
			ModelAndView mv = new ModelAndView("chefe/apontamentosPendentes");
			mv.addObject("apontamentos", apontamento);
			
			return mv;
		}
		
		if(session.getAttribute("sessionPerfil").equals(3)){
			ApontamentoDAO apontamentoDAO = new ApontamentoDAO();
			List<Apontamento> apontamento = apontamentoDAO.listarPendentes(session);
			
			ModelAndView mv = new ModelAndView("diretor/apontamentosPendentes");
			mv.addObject("apontamentos", apontamento);
			
			return mv;
		}
		return new ModelAndView("redirect:login");
		
	}
	
	@RequestMapping("/aprovarApondamentoPendente")
	public ModelAndView aprovarApontamento(Apontamento apontamento){
		apontamento.setStatus("Aprovado");
		ApontamentoDAO dao = new ApontamentoDAO();
		dao.aprovarPendentes(apontamento);
		
		ModelAndView mv = new ModelAndView("forward:listarApontamentoPendente"); //Redireciona para o controlador ApontamentoController
		mv.addObject("status","apontamentoaprovado");
		return mv;
	}
	
	@RequestMapping("/reprovarApondamentoPendente")
	public ModelAndView reprovarApontamento(Apontamento apontamento){
		apontamento.setStatus("Reprovado");
		ApontamentoDAO dao = new ApontamentoDAO();
		dao.aprovarPendentes(apontamento);
		
		ModelAndView mv = new ModelAndView("forward:listarApontamentoPendente"); //Redireciona para o controlador ApontamentoController
		mv.addObject("status","apontamentoreprovado");
		return mv;
	}
	

}