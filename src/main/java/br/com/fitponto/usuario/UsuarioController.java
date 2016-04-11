package br.com.fitponto.usuario;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fitponto.usuario.Usuario;
import br.com.fitponto.usuario.UsuarioDAO;

@Controller
public class UsuarioController {
	@RequestMapping("/adicionarUsuario")
	public ModelAndView adicionar(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		dao.inserir(usuario);
		
		ModelAndView mv = new ModelAndView("forward:listaUsuario");
		mv.addObject("status","usuariocadastrado");
		return mv;
		
	}
	
	@RequestMapping("/alterarUsuario")
	public ModelAndView alterar(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		dao.atualizar(usuario);
		
		ModelAndView mv = new ModelAndView("forward:listaUsuario");
		mv.addObject("status","usuarioalterado");
		return mv;
	}
	
	@RequestMapping("/removerUsuario")
	public ModelAndView remover(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		dao.deletar(usuario);
		
		ModelAndView mv = new ModelAndView("forward:listaUsuario");
		mv.addObject("status","usuarioremovido");
		return mv;
	}
	
	@RequestMapping("/listaUsuario")
	public ModelAndView listar(HttpSession session){
		if(session.getAttribute("sessionPerfil").equals(2)){
			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> usuario = dao.listar();
			List<Usuario> supervisor = dao.listaSupervisores();
			
			ModelAndView mv = new ModelAndView("chefe/usuarios");
			mv.addObject("usuarios", usuario);
			mv.addObject("supervisores", supervisor);
			
			return mv;
		}
		
		if(session.getAttribute("sessionPerfil").equals(3)){
			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> usuario = dao.listar();
			List<Usuario> supervisor = dao.listaSupervisores();
			
			ModelAndView mv = new ModelAndView("diretor/usuarios");
			mv.addObject("usuarios", usuario);
			mv.addObject("supervisores", supervisor);
			
			return mv;
		}
		return new ModelAndView("redirect:login");
		
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView retornaDash(HttpSession session){
		if(session.getAttribute("sessionPerfil").equals(2)){
			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> usuariohoras = dao.listaHorasTrabalhadas();
			List<Usuario> usuarioaprovacoes = dao.listaAprovacoes();
			
			ModelAndView mv = new ModelAndView("chefe/dashboard");
			mv.addObject("usuariosHoras", usuariohoras);
			mv.addObject("usuariosAprovacoes", usuarioaprovacoes);
			
			return mv;
		}
		
		if(session.getAttribute("sessionPerfil").equals(3)){
			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> usuariohoras = dao.listaHorasTrabalhadas();
			List<Usuario> usuarioaprovacoes = dao.listaAprovacoes();
			
			ModelAndView mv = new ModelAndView("diretor/dashboard");
			mv.addObject("usuariosHoras", usuariohoras);
			mv.addObject("usuariosAprovacoes", usuarioaprovacoes);
			
			return mv;
		}
		return new ModelAndView("redirect:login");
		
	}
}
