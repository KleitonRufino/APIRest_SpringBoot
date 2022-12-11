import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.model.Cliente;
import br.com.repository.ClienteRepository;
import br.com.service.ClienteService;
import br.com.vo.ClienteVO;
import br.com.vo.ClienteVOInsert;
import br.com.vo.ClienteVOUpdate;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

	@Spy
	@InjectMocks
	private ClienteService service;
	@Mock
	private ClienteRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void insert() throws Exception {
		Cliente c = new Cliente();
		Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(c);
		ClienteVO result = service.insert(new ClienteVOInsert());
		assertNotNull(result);
		Mockito.verify(repository).save(Mockito.any(Cliente.class));
	}
	
	@Test
	public void update() throws Exception {
		Cliente c = new Cliente();
		c.setId(1L);
		ClienteVOUpdate vo = new ClienteVOUpdate();
		vo.setId(1L);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(c));
		Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(c);
		ClienteVO result = service.update(vo);
		assertNotNull(result);
		Mockito.verify(repository).save(c);
	}

	@Test
	public void delete() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente));
		Mockito.doNothing().when(repository).delete(Mockito.any(Cliente.class));
		service.delete(1L);
		Mockito.verify(repository).delete(cliente);
	}

	@Test
	public void findAll() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("asc", "nome"));
		Page<Cliente> page = new PageImpl<>(Arrays.asList(new Cliente()));
		Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
		Page<ClienteVO> result = service.findAll(pageable);
		assertNotNull(result);
	}

	@Test
	public void search_ID() throws Exception {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Cliente()));
		Mockito.when(repository.findByNome(Mockito.anyString())).thenReturn(Arrays.asList(new Cliente()));
		List<ClienteVO> result = service.findByIdAndNome(1L, null);
		assertNotNull(result);
		Mockito.verify(service).findByIdAndNome(1L, null);
	}

	@Test
	public void search_Nome() throws Exception {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Cliente()));
		Mockito.when(repository.findByNome(Mockito.anyString())).thenReturn(Arrays.asList(new Cliente()));
		List<ClienteVO> result = service.findByIdAndNome(null, "joao");
		assertNotNull(result);
		Mockito.verify(service).findByIdAndNome(null, "joao");
	}

	@Test
	public void findById() throws Exception {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Cliente()));
		Optional<Cliente> result = repository.findById(1L);
		assertTrue(result.isPresent());
	}
}
