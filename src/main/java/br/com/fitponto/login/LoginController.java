package br.com.fitponto.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fitponto.login.User;
import br.com.fitponto.login.LoginDAO;

@Controller
public class LoginController {
	@RequestMapping("/login") //Direciona o usuário para a página de login
	public ModelAndView formLogin(){
		//System.out.println("Pagina de login");
		System.out.println("Pagina 1");
		ModelAndView mv = new ModelAndView("login"); //Direciona para a página JSP login
		return mv;
	}
	
	@RequestMapping("/loginWrong") //Direciona o usuário para a página de login
	public ModelAndView formLoginWrong(){
		//System.out.println("Pagina de login");
		System.out.println("Pagina 2");
		ModelAndView mv = new ModelAndView("login"); //Direciona para a página JSP login
		mv.addObject("status","loginreprovado");
		return mv;
	}
	
	@RequestMapping("/doLogin") //Autentica o usuario
	public ModelAndView doLogin (User user, HttpSession session){
		//Teste de parametros
		//System.out.println(user.getUsername());
		//System.out.println(user.getPassword());
		
		//Realiza a autenticação do usuário e caso exista armazena em user
		user = new LoginDAO().doLogin(user);
		
		//Registra as informações do usuario na sessão.
		if(user.getPerfil() != 0){
			//System.out.println("Usuário autenticado criando sessão");
			session.setAttribute("sessionID", user.getId()); //Atribui o valor sessionID na sessão
			session.setAttribute("sessionUserID", user.getId()); //Atribui o valor sessionName na sessão
			session.setAttribute("sessionName", user.getNome()); //Atribui o valor sessionName na sessão
			session.setAttribute("sessionPosition", user.getPosition()); //Atribui o valor sessionPosition na sessão
			session.setAttribute("sessionPerfil", user.getPerfil()); //Atribui o valor sessionPerfil na sessão
		}
		
		//System.out.println(user.getPerfil());
		switch (user.getPerfil()){
		case 1:
			//Faz o direcionamento para o controlador pageController
			System.out.println("Página do funcionário");
			return new ModelAndView("redirect:funcionario"); //Redireciona para o controlador pageController
		case 2:
			//Faz o direcionamento para o controlador pageController
			System.out.println("Página do chefe");
			return new ModelAndView("redirect:chefe"); //Redireciona para o controlador pageController
		case 3:
			//Faz o direcionamento para o controlador pageController
			System.out.println("Página do diretor");
			return new ModelAndView("redirect:diretor"); //Redireciona para o controlador pageController
		default:
			//System.out.println("Não foi possível identificar o nível do privilégio.");
			return new ModelAndView("redirect:loginWrong"); //Redireciona para o controlador loginController
		}
	}
	
	@RequestMapping("/doLogout") //Realiza o logout do usuário
	private ModelAndView doLogout (HttpSession session){
		//System.out.println("Realiza o Logout");
		session.invalidate(); //Destroi a sessão
		
		return new ModelAndView("redirect:login"); //Redireciona para o controlador loginController
	}
	
	
}