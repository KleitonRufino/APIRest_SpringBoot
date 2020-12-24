package br.com.apirest_springboot.migrations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@ChangeLog
public class Migrations {

	@ChangeSet(order = "001", id = "001", author = "kleitonrufino")
	public void createinitialdatas(MongoDatabase db)
			throws ParseException {
		MongoCollection<Document> userCollection = db.getCollection("user");
		MongoCollection<Document> postCollection = db.getCollection("post");
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Document maria = new Document().append("name", "Maria Brown").append("email", "maria@gmail.com");
		Document alex = new Document().append("name", "Alex Green").append("email", "alex@gmail.com");
		Document bob = new Document().append("name", "Bob Grsey").append("email", "bob@gmail.com");
		userCollection.insertOne(maria);
		userCollection.insertOne(alex);
		userCollection.insertOne(bob);
			
		
		Document comentario1PostMaria1 = new Document().append("text","Boa viagem mano!").append("date", sdf.parse("21/03/2018")).append("author", alex);
		Document comentario2PostMaria1 = new Document().append("text", "Aproveite").append("date", sdf.parse("22/03/2018")).append("author", bob);
		Document postMaria1 = new Document().append("date", sdf.parse("21/03/2018"))
				.append("title", "Partiu viagem").append("body", "Vou viajar para São Paulo. Abraços!").append("author", maria).append("comments", Arrays.asList(comentario1PostMaria1, comentario2PostMaria1)); 
		postCollection.insertOne(postMaria1);
		
		
		Document comentario1PostMaria2 = new Document().append("text", "Tenha um ótimo dia!").append("date",  sdf.parse("23/03/2018")).append("author", alex);
		Document postMaria2 = new Document().append("date", sdf.parse("21/03/2018"))
				.append("title", "Bom dia").append("body", "Acordei feliz hoje!").append("author", maria).append("comments", Arrays.asList(comentario1PostMaria2)); 
		
		postCollection.insertOne(postMaria2);
		
		Document mariaUpdate = new Document("$set", new Document("posts", Arrays.asList(new DBRef("post", postMaria1.get("_id")), new DBRef("post", postMaria2.get("_id")))));	
		
		Document filter = new Document("_id", (ObjectId)maria.get("_id" ));
		userCollection.updateOne(filter, mariaUpdate);
		
	}

}
