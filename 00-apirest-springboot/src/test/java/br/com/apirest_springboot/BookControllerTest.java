package br.com.apirest_springboot;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.apirest_springboot.controller.BookController;
import br.com.apirest_springboot.services.BookServices;
import br.com.apirest_springboot.vo.BookVO;

@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
public class BookControllerTest {

	@InjectMocks
	private BookController controller;
	@Mock
	private BookServices service;

	@Before
	public void setUp() {
		BDDMockito.when(service.findAll()).thenReturn(List.of(create()));
		BDDMockito.when(service.findById(ArgumentMatchers.any())).thenReturn(new BookVO());
	}

	@Test
	public void list_ReturnListOfBooks() {
		String title = create().getTitle();
		List<BookVO> list = controller.findAll();
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list).isNotEmpty().hasSize(1);
		Assertions.assertThat(list.get(0).getTitle()).isEqualTo(title);
	}

//	private List<BookVO> createList(){
//		List<BookVO> list = new ArrayList<BookVO>();
//		BookVO vo= create();
//		list.add(vo);
//		return list;
//	}

	BookVO create() {
		BookVO vo = new BookVO();
		vo.setAuthor("author");
		vo.setKey(Long.valueOf(1));
//		vo.setLaunchDate(new Date());
		vo.setPrice(Double.valueOf(1));
		vo.setTitle("title");
		return vo;
	}
}
