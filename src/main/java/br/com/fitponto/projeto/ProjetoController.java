package br.com.fitponto.projeto;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fitponto.projeto.Projeto;
import br.com.fitponto.projeto.ProjetoDAO;

@Controller
public class ProjetoController {
	
	@RequestMapping("/adicionarProjeto")
	public ModelAndView adicionar(Projeto projeto){
		ProjetoDAO dao = new ProjetoDAO();
		dao.inserir(projeto);
		
		ModelAndView mv = new ModelAndView("forward:listaProjeto");
		mv.addObject("status","projetocadastrado");
		return mv;
	}
	
	@RequestMapping("/alterarProjeto")
	public ModelAndView alterar(Projeto projeto){
		ProjetoDAO dao = new ProjetoDAO();
		dao.atualizar(projeto);
		
		ModelAndView mv = new ModelAndView("forward:listaProjeto");
		mv.addObject("status","projetoalterado");
		return mv;
	}
	
	@RequestMapping("/removerProjeto")
	public ModelAndView remover(Projeto projeto){
		ProjetoDAO dao = new ProjetoDAO();
		dao.deletar(projeto);
		
		ModelAndView mv = new ModelAndView("forward:listaProjeto");
		mv.addObject("status","projetoremovido");
		return mv;
	}
	
	@RequestMapping("/listaProjeto")
	public ModelAndView listar(HttpSession session){
		if(session.getAttribute("sessionPerfil").equals(2)){
			ProjetoDAO dao = new ProjetoDAO();
			List<Projeto> projeto = dao.listar();
			return new ModelAndView("chefe/projetos","projetos",projeto);
		}
		
		if(session.getAttribute("sessionPerfil").equals(3)){
			ProjetoDAO dao = new ProjetoDAO();
			List<Projeto> projeto = dao.listar();
			return new ModelAndView("diretor/projetos","projetos",projeto);
		}
		return new ModelAndView("redirect:login");
	}
	
}
