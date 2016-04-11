package br.com.fitponto.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fitponto.login.User;
import br.com.fitponto.login.LoginDAO;

@Controller
public class LoginController {
	@RequestMapping("/login") //Direciona o usu�rio para a p�gina de login
	public ModelAndView formLogin(){
		//System.out.println("Pagina de login");
		System.out.println("Pagina 1");
		ModelAndView mv = new ModelAndView("login"); //Direciona para a p�gina JSP login
		return mv;
	}
	
	@RequestMapping("/loginWrong") //Direciona o usu�rio para a p�gina de login
	public ModelAndView formLoginWrong(){
		//System.out.println("Pagina de login");
		System.out.println("Pagina 2");
		ModelAndView mv = new ModelAndView("login"); //Direciona para a p�gina JSP login
		mv.addObject("status","loginreprovado");
		return mv;
	}
	
	@RequestMapping("/doLogin") //Autentica o usuario
	public ModelAndView doLogin (User user, HttpSession session){
		//Teste de parametros
		//System.out.println(user.getUsername());
		//System.out.println(user.getPassword());
		
		//Realiza a autentica��o do usu�rio e caso exista armazena em user
		user = new LoginDAO().doLogin(user);
		
		//Registra as informa��es do usuario na sess�o.
		if(user.getPerfil() != 0){
			//System.out.println("Usu�rio autenticado criando sess�o");
			session.setAttribute("sessionID", user.getId()); //Atribui o valor sessionID na sess�o
			session.setAttribute("sessionUserID", user.getId()); //Atribui o valor sessionName na sess�o
			session.setAttribute("sessionName", user.getNome()); //Atribui o valor sessionName na sess�o
			session.setAttribute("sessionPosition", user.getPosition()); //Atribui o valor sessionPosition na sess�o
			session.setAttribute("sessionPerfil", user.getPerfil()); //Atribui o valor sessionPerfil na sess�o
		}
		
		//System.out.println(user.getPerfil());
		switch (user.getPerfil()){
		case 1:
			//Faz o direcionamento para o controlador pageController
			System.out.println("P�gina do funcion�rio");
			return new ModelAndView("redirect:funcionario"); //Redireciona para o controlador pageController
		case 2:
			//Faz o direcionamento para o controlador pageController
			System.out.println("P�gina do chefe");
			return new ModelAndView("redirect:chefe"); //Redireciona para o controlador pageController
		case 3:
			//Faz o direcionamento para o controlador pageController
			System.out.println("P�gina do diretor");
			return new ModelAndView("redirect:diretor"); //Redireciona para o controlador pageController
		default:
			//System.out.println("N�o foi poss�vel identificar o n�vel do privil�gio.");
			return new ModelAndView("redirect:loginWrong"); //Redireciona para o controlador loginController
		}
	}
	
	@RequestMapping("/doLogout") //Realiza o logout do usu�rio
	private ModelAndView doLogout (HttpSession session){
		//System.out.println("Realiza o Logout");
		session.invalidate(); //Destroi a sess�o
		
		return new ModelAndView("redirect:login"); //Redireciona para o controlador loginController
	}
	
	
}