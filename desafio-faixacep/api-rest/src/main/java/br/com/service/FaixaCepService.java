package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.exception.FaixaCepIndisponivelException;
import br.com.exception.ResourceNotFoundException;
import br.com.model.FaixaCep;
import br.com.repository.FaixaCepRepository;
import br.com.vo.FaixaCepVO;

@Service
public class FaixaCepService {

	@Autowired
	private FaixaCepRepository repository;
	
	public FaixaCepVO insert(FaixaCepVO vo) {
		FaixaCep res = repository.availableRange(vo.getFaixaInicio(), vo.getFaixaFim());
		if(res != null)
			throw new FaixaCepIndisponivelException("(erro! essa faixa de CEP conflita com a faixa de CEP da " + res.getCodigoLoja());
		FaixaCep model = repository.save(getModel(vo));
		return getVO(model);
	}
	
	public Page<FaixaCepVO> findAll(Pageable pageable){
		var page = repository.findAll(pageable); 
		return page.map(o -> getVO(o));
	}
	
	public FaixaCepVO findByCep(int cep) {
		var model = repository.finByCep(cep); 
		if(model == null)
				throw new ResourceNotFoundException("Nenhuma Loja fisica encontrada para este CEP");
		return getVO(model);
	}
	
	public FaixaCepVO findById(Long id) {
		return getVO(repository.findById(id).get());
	}
	
	private FaixaCep getModel(FaixaCepVO vo) {
		FaixaCep model = new FaixaCep();
		model.setId(vo.getKey());
		model.setCodigoLoja(vo.getCodigoLoja());
		model.setFaixaInicio(vo.getFaixaInicio());
		model.setFaixaFim(vo.getFaixaFim());
		return model;
	}
	
	private FaixaCepVO getVO(FaixaCep model) {
		FaixaCepVO vo = new FaixaCepVO();
		vo.setKey(model.getId());
		vo.setCodigoLoja(model.getCodigoLoja());
		vo.setFaixaInicio(model.getFaixaInicio());
		vo.setFaixaFim(model.getFaixaFim());
		return vo;
	}
}
