package br.com.apirest_springboot;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.apirest_springboot.model.Book;
import br.com.apirest_springboot.repository.BookRepository;
import br.com.apirest_springboot.vo.BookVO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private BookRepository repository;
	
	
	@Before
	public void start() {
		Book book  = new  Book();
		book.setAuthor("author");
		book.setLaunchDate(new Date());
		book.setPrice(Double.valueOf(1));
		book.setTitle("title");
		repository.save(book);
	}

	@After
	public void end() {
		repository.deleteAll();
	}
	
	@Test
	public void list_ReturnListOfBooks() {
		BookVO vo = create();
		String expectedTitle = vo.getTitle();
		ParameterizedTypeReference<List<BookVO>> parameterizedTypeReference = new ParameterizedTypeReference<List<BookVO>>() {
		};
	
		List<BookVO> response = restTemplate.exchange("/api/book/v1", HttpMethod.GET, null, parameterizedTypeReference).getBody();
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response).isNotEmpty().hasSize(1);
		Assertions.assertThat(response.get(0).getTitle()).isEqualTo(expectedTitle);
	}
	
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
