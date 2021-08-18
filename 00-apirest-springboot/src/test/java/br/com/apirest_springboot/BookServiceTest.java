package br.com.apirest_springboot;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.apirest_springboot.model.Book;
import br.com.apirest_springboot.repository.BookRepository;
import br.com.apirest_springboot.services.BookServices;
import br.com.apirest_springboot.vo.BookVO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

	@InjectMocks
	BookServices service;
	@Mock
	BookRepository repository;

	@Before
	public void setUp() {
		BDDMockito.when(repository.findAll()).thenReturn(List.of(create()));
		BDDMockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Book()));
	}

	@Test
	public void list_ReturnListOfBooks() {
		String title = create().getTitle();
		List<BookVO> list = service.findAll();
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

	Book create() {
		Book vo = new Book();
		vo.setAuthor("author");
		vo.setId(Long.valueOf(1));
//		vo.setLaunchDate(new Date());
		vo.setPrice(Double.valueOf(1));
		vo.setTitle("title");
		return vo;
	}
}
