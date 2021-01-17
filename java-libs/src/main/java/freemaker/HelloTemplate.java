package freemaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateException;

public class HelloTemplate {
	 public static void main(String[] args) throws IOException,TemplateException {

	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("data", new Date());
	 map.put("usuario", "Ricardo");

	 List<Produto> produtos = new ArrayList<Produto>();
	 produtos.add(new Produto("Produto A1",300.14));
	 produtos.add(new Produto("Produto B2",401.56));
	 produtos.add(new Produto("Produto C3",555.77));
	 map.put("produtos", produtos);

	 String s = FreemarkerUtils.parseTemplate(map, "teste.ftl");
	 System.out.println(s);
	 }
	}