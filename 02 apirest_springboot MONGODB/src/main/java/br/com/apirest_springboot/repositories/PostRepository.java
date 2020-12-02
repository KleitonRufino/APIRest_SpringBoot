package br.com.apirest_springboot.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apirest_springboot.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{'title': {$regex: ?0, $options: 'i'}}")
	public List<Post> searchTitle(String text);

	public List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i'} } , { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0,  $options: 'i'} } ] } ] }")
	public List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
