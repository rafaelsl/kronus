package br.com.odara.app.agendou.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.odara.app.agendou.daos.EmpresaDAO;
import br.com.odara.app.agendou.models.Empresa;

@RestController
@Transactional
@RequestMapping("/")
public class IndexController {

	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	private List<Empresa> empresas;
	
	@Autowired
	private EmpresaDAO dao;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("public/index");
		empresas = dao.listAll();
		view.addObject("empresas", empresas);
		return view;
	}
	
	@RequestMapping(value="/cadastro", method=RequestMethod.GET)
	public ModelAndView cadastro() {
		ModelAndView view = new ModelAndView("public/cadastro");
		return view;
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvarEmpresa( @RequestBody Empresa empresa ) {
		ModelAndView view = new ModelAndView("public/cadastro");
		logger.info(empresa.getRazaoSocial());
		logger.info(empresa.getCnpj());
		dao.save(empresa);
		return view;
	}
	
}
