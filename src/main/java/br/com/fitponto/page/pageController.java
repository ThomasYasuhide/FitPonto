package br.com.fitponto.page;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pageController {
	
	@RequestMapping("/funcionario")
	private ModelAndView menuFuncionario (HttpSession session){
		System.out.println(session.getAttribute("sessionPerfil"));
		if(session.getAttribute("sessionPerfil").equals(1)){
			System.out.println("Controlador Funcionario");
			return new ModelAndView("funcionario/home");
		}
		return new ModelAndView("redirect:login");
	}
	
	@RequestMapping("/chefe")
	private ModelAndView menuChefe (HttpSession session){
		
		if(session.getAttribute("sessionPerfil").equals(2)){
			//System.out.println("Controlador Chefe de sessão");
			return new ModelAndView("chefe/home");
		}
		return new ModelAndView("redirect:login");
	}
	
	@RequestMapping("/diretor")
	private ModelAndView menuDiretor (HttpSession session){
		
		if(session.getAttribute("sessionPerfil").equals(3)){
			//System.out.println("Controlador Diretor");
			return new ModelAndView("diretor/home");
		}
		return new ModelAndView("redirect:login");
	}
}