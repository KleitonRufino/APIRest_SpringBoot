package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.converter.PessoaConverter;
import com.example.demo.model.PessoaModel;
import com.example.demo.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	 int count = 0;
	 @Autowired
	 PessoaService service;
	 @Autowired
	 PessoaConverter converter;
	 
	 @GetMapping("/")
	 public ModelAndView listarPessoas() {
		 add();
		 
		 List<PessoaModel> model = new ArrayList<PessoaModel>();
		 service.listar().forEach(o -> model.add(converter.entity2model(o)));
		 ModelAndView mv = new ModelAndView("pessoas");
		 mv.addObject("pessoas", model);
		 return mv;
	 }
	 
	 @GetMapping("/add")
	 public String add(Model model) {
		 model.addAttribute("pessoa", new PessoaModel());
		 return "pessoa";
	 }
	 
	 @PostMapping("/salvar")
	 public String salvar(Model model, @ModelAttribute(name = "pessoa") PessoaModel pessoaModel) {
		 service.save(converter.model2entity(pessoaModel));
		 return "redirect:/pessoas/";
	 }
	 
	 @GetMapping("/cancelar")
	 public String cancelar(Model model) {
		 return "redirect:/pessoas/";
	 }
	 
	 @GetMapping("/editar")
	 public String editar(Model model, @RequestParam(name = "id")int id) {
		 model.addAttribute("pessoa", converter.entity2model(service.encontrarPorId(id)));
		 return "pessoa";
	 }
	 
	 @GetMapping("/remover")
	 public ModelAndView remover(Model model, @RequestParam(name = "id") int id) {
		 service.remover(id);
		 return listarPessoas();
	 }
	 
	 public void add() {
		 if(count == 0) {
			PessoaModel m1 = new PessoaModel("Jose", "jose@email.com", "(22)2222-2222");
			PessoaModel m2 = new PessoaModel("Maria", "maria@email.com", "(22)2222-2222");
			service.save(converter.model2entity(m1));
			service.save(converter.model2entity(m2));
			count++;
		 }
	 }
	
}
