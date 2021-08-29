package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotfoundException;
import com.example.demo.service.ProductService;

@RestController
public class RestProductServiceController {

	@Autowired
	ProductService service;
	
	
	@RequestMapping(value = "/products")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Object> getProducts(){
		return new ResponseEntity<>(service.getProducts(),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Object> getProducts(@PathVariable String id){
		Product p = service.getProduct(id);
		if(p != null)
			return new ResponseEntity<>(p,
				HttpStatus.OK);
		else
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		service.createProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id")String id,@RequestBody Product product){
		boolean notFound = true;
		for (Product p : service.getProducts()) {
			if(id.equals(p.getId()))
				notFound = false;
		}
		if(notFound) throw new ProductNotfoundException();
		service.updateProduct(id, product);
		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable("id")String id) {
		service.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
	}
	
//	private static Map<String, Product> productRepo = new HashMap<String, Product>();
//	
//	static {
//		Product honey = new Product();
//		honey.setId("1");
//		honey.setName("Honey");
//		productRepo.put(honey.getId(), honey);
//		Product almond = new Product();
//		almond.setId("2");
//		almond.setName("Almond");
//		productRepo.put(almond.getId(), almond);
//	}
//	
//	@RequestMapping(value = "/products")
//	public ResponseEntity<Object> getProducts(){
//		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/products", method = RequestMethod.POST)
//	public ResponseEntity<Object> createProduct(@RequestBody Product product){
//		productRepo.put(product.getId(), product);
//		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
//	}
//	
//	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Object> updateProduct(@PathVariable("id")String id,@RequestBody Product product){
//		if(!productRepo.containsKey(id)) throw new ProductNotfoundException();
//		productRepo.remove(id);
//		product.setId(id);
//		productRepo.put(product.getId(), product);
//		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Object> deleteProduct(@PathVariable("id")String id) {
//		productRepo.remove(id);
//		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
//	}
//	
}
