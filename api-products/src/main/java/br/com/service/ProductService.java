package br.com.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exception.ResourceEmptyException;
import br.com.exception.ResourceNotFoundException;
import br.com.model.Product;
import br.com.repository.ProductRepository;
import br.com.vo.ProductHateoasVO;
import br.com.vo.ProductVO;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public ProductVO insert(ProductVO vo) {
		if (vo.getName() == null || vo.getName().isBlank() || vo.getDescription() == null
				|| vo.getDescription().isBlank() || vo.getPrice() == null)
			throw new ResourceEmptyException("Preencha os valores obrigatorios: name, description e price");
		Product model = repository.save(getModel(vo));
		return getVO(model);
	}

	public ProductVO put(String id, ProductVO vo) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum produto encontrado"));
		vo.setId(entity.getId());
		return insert(vo);
	}

//	public Page<ProductVO> findAll(Pageable pageable){
//		var page = repository.findAll(pageable); 
//		return page.map(o -> getVO(o));
//	}

	public List<ProductVO> findAll() {
		var products = repository.findAll();
		return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
	}

	public List<ProductVO> findAll(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		if (q != null && !q.isBlank() && minPrice != null && maxPrice != null) {
			var products = repository.finByNameOrDescriptionAndMinPriceAndMaxPrice(q.toUpperCase(), minPrice, maxPrice);
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		} else if (q != null && !q.isBlank() && minPrice != null && maxPrice == null) {
			var products = repository.finByNameOrDescriptionAndMinPrice(q.toUpperCase(), minPrice);
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		} else if (q != null && !q.isBlank() && minPrice == null && maxPrice != null) {
			var products = repository.finByNameOrDescriptionAndMaxPrice(q.toUpperCase(), maxPrice);
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		} else if (q != null && !q.isBlank() && minPrice == null && maxPrice == null) {
			var products = repository.findByNameOrDescription(q.toUpperCase());
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		} else if ((q == null || q.isBlank()) && minPrice != null && maxPrice != null) {
			var products = repository.finByMinPriceAndMaxPrice(minPrice, maxPrice);
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		} else if ((q == null || q.isBlank()) && minPrice != null && maxPrice == null) {
			var products = repository.finByMinPrice(minPrice);
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		} else if ((q == null || q.isBlank()) && minPrice == null && maxPrice != null) {
			var products = repository.finByMaxPrice(maxPrice);
			return products.stream().map(o -> getVO(o)).collect(Collectors.toList());
		}
		return new ArrayList<ProductVO>();
	}

	public ProductVO findById(String id) {
		var model = repository.findById(id);
		if (!model.isPresent())
			throw new ResourceNotFoundException("Nenhum produto encontrado");
		return getVO(model.get());
	}

	public void delete(String id) {
		var model = repository.findById(id);
		if (!model.isPresent())
			throw new ResourceNotFoundException("Nenhum produto encontrado");
		repository.deleteById(id);
	}

	private Product getModel(ProductVO vo) {
		Product model = new Product();
		model.setId(vo.getId());
		model.setName(vo.getName());
		model.setDescription(vo.getDescription());
		model.setPrice(vo.getPrice());
		return model;
	}

	public static ProductVO getVO(Product model) {
		ProductVO vo = new ProductVO();
		vo.setId(model.getId());
		vo.setName(model.getName());
		vo.setDescription(model.getDescription());
		vo.setPrice(model.getPrice());
		return vo;
	}

	public static ProductHateoasVO getReadVO(ProductVO vo) {
		ProductHateoasVO readVo = new ProductHateoasVO();
		readVo.setId(vo.getId());
		readVo.setName(vo.getName());
		readVo.setDescription(vo.getDescription());
		readVo.setPrice(vo.getPrice());
		return readVo;
	}
}
