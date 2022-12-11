import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.controller.ClienteController;
import br.com.service.ClienteService;
import br.com.vo.ClienteVO;
import br.com.vo.ClienteVOInsert;
import br.com.vo.ClienteVOUpdate;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTests {

	@Spy
	@InjectMocks
	private ClienteController api;
	@Mock
	private ClienteService service;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void insert() throws Exception {
		ClienteVOInsert vo = new ClienteVOInsert();
		Mockito.when(service.insert(Mockito.any(ClienteVOInsert.class))).thenReturn(new ClienteVO());
		ResponseEntity<?> result = api.insert(vo);
		assertNotNull(result);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		Mockito.verify(service).insert(vo);
	}
	
	@Test
	public void update() throws Exception {
		ClienteVOUpdate vo = new ClienteVOUpdate();
		Mockito.when(service.update(Mockito.any(ClienteVOUpdate.class))).thenReturn(new ClienteVO());
		ResponseEntity<?> result = api.update(vo);
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		Mockito.verify(service).update(vo);
	}
	
	@Test
	public void delete() throws Exception {
		Mockito.doNothing().when(service).delete(Mockito.anyLong());
		ResponseEntity<?> result = api.delete(1L);
		assertNotNull(result);
		Mockito.verify(service).delete(1L);
	}

	@Test
	public void findAll() throws Exception {
		Page<ClienteVO> page = new PageImpl<>(Arrays.asList(new ClienteVO()));
		Mockito.when(service.findAll(Mockito.any(Pageable.class))).thenReturn(page);
		ResponseEntity<?> result = api.findAll(0, 1, "asc");
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void search() throws Exception {
		Mockito.when(service.findByIdAndNome(Mockito.anyLong(), Mockito.anyString())).thenReturn(new ArrayList<>());
		ResponseEntity<?> result = api.search(1L, "joao");
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		Mockito.verify(service).findByIdAndNome(1L, "joao");
	}
	
	@Test
	public void findById() throws Exception {
		Mockito.when(service.findById(Mockito.anyLong())).thenReturn(new ClienteVO());
		ClienteVO result = api.findById(1L);
		assertNotNull(result);
		Mockito.verify(service).findById(1L);
	}
}
