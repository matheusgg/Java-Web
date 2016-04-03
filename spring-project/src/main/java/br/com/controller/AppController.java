package br.com.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.model.Cliente;

@Controller
public class AppController implements Serializable {

	private static final long serialVersionUID = 6905846846149822769L;

	@RequestMapping(value = "preparaCadastro", method = RequestMethod.GET)
	public ModelAndView preparaCadastro() {
		return new ModelAndView("cadastro", "command", new Cliente());
	}

	@RequestMapping(value = "cadastraCliente", method = RequestMethod.POST)
	public String cadastraCliente(@Valid Cliente cliente, BindingResult result) {
		return "index";
	}

}
