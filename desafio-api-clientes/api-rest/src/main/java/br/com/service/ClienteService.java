package br.com.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.exception.BadRequestException;
import br.com.model.Cliente;
import br.com.repository.ClienteRepository;
import br.com.vo.ClienteVO;
import br.com.vo.ClienteVOInsert;
import br.com.vo.ClienteVOUpdate;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public ClienteVO insert(ClienteVOInsert vo) {
		Cliente model = repository.save(getModel(vo));
		return getVO(model);
	}

	public void delete(Long id) {
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Nenhum cliente encontrado"));
		repository.delete(cliente);
	}

	public ClienteVO update(ClienteVOUpdate vo) {
		Cliente cliente = repository.findById(vo.getId())
				.orElseThrow(() -> new BadRequestException("Nenhum cliente encontrado"));
		cliente.setNascimento(vo.getNascimento());
		cliente.setNome(vo.getNome());
		Cliente model = repository.save(cliente);
		return getVO(model);
	}

	public Page<ClienteVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(o -> getVO(o));
	}

	public List<ClienteVO> findByIdAndNome(Long id, String nome) {
		if (id != null) {
			Cliente cliente = repository.findById(id)
					.orElseThrow(() -> new BadRequestException("Nenhum cliente encontrado"));
			ClienteVO clienteVO = getVO(cliente);
			return Arrays.asList(clienteVO);
		} else if (nome != null && !nome.isEmpty()) {
			var clientes = repository.findByNome(nome);
			if (clientes == null || clientes.isEmpty())
				throw new BadRequestException("Nenhum cliente encontrado");
			return clientes.stream().map(o -> getVO(o)).collect(Collectors.toList());
		}
		throw new BadRequestException("Nenhum parametro passado");
	}

	public ClienteVO findById(Long id) {
		return getVO(repository.findById(id).get());
	}

	private Cliente getModel(ClienteVOInsert vo) {
		Cliente model = new Cliente();
		model.setNome(vo.getNome());
		model.setNascimento(vo.getNascimento());
		return model;
	}

	private ClienteVO getVO(Cliente model) {
		ClienteVO vo = new ClienteVO();
		vo.setId(model.getId());
		vo.setNome(model.getNome());
		vo.setNascimento(model.getNascimento());
		vo.setIdade(getIdade(model.getNascimento()));
		return vo;
	}

	public static Integer getIdade(LocalDate nascimento) {
		Integer idade = 0;
		LocalDate dataHoje = LocalDate.now();
		if (nascimento != null) {
			Period dif = nascimento.until(dataHoje);
			idade = dif.getYears();
		}
		return idade;
	}

}
