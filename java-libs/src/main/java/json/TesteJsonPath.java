package json;

import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.jayway.jsonpath.JsonPath;

public class TesteJsonPath {

	public static void main(String[] args) {
		jsonPath();
	}
	
	public static void getNumbers() {
		//String json = "{\"store\":{\"book\":[{\"category\":\"reference\", \"author\":\"Nigel Ress\", \"price\":8.95}]}}";
		String json = getJson();
		List<String> numbers = JsonPath.read(json, "$.phoneNumbers[*].number");
		System.out.println(numbers.toString());
	}
	
	public static void jsonPath() {
		String json = getStore();
		System.out.println("The authors of all books: " + JsonPath.read(json, "$.store.book[*].author"));
		System.out.println("All authors: " +JsonPath.read(json, "$..author"));
		System.out.println("All things, both books and bicycles: " + JsonPath.read(json, "$.store.*"));
		System.out.println("The price of everything: " + JsonPath.read(json, "$.store..price"));
		System.out.println("The third book: " + JsonPath.read(json, "$..book[2]"));
		System.out.println("The second to last book: " + JsonPath.read(json, "$..book[-2]"));
		System.out.println("The first two books: " + JsonPath.read(json, "$..book[0,1]"));
		System.out.println("All books from index 0 (inclusive) until index 2 (exclusive): " + JsonPath.read(json, "$..book[:2]"));
		System.out.println("All books from index 1 (inclusive) until index 2 (exclusive): " + JsonPath.read(json, "$..book[1:2]"));
		System.out.println("Last two books: " + JsonPath.read(json, "$..book[-2]"));
		System.out.println("Book number two from tail: " + JsonPath.read(json, "$..book[2:]"));
		System.out.println("All books with an ISBN number: " + JsonPath.read(json, "$..book[?(@.isbn)]"));
		System.out.println("All books in store cheaper than 10: " + JsonPath.read(json, "$.store.book[?(@.price < 10)]"));
		System.out.println("All books in store that are not \"expensive\": " + JsonPath.read(json, "$..book[?(@.price <= $['expensive'])]"));
		System.out.println("All books matching regex (ignore case): " + JsonPath.read(json, "$..book[?(@.author =~ /.*REES/i)]"));
		System.out.println("Give me every thing: " + JsonPath.read(json, "$..*"));
		System.out.println("The number of books: " + JsonPath.read(json, "$..book.length()"));
	}
	
	
	
	public static String getJson() {
		InputStream is = TesteOrgJson.class.getClassLoader().getResourceAsStream("json.json");
		JSONTokener tokener = new JSONTokener(is);
		JSONObject obj = new JSONObject(tokener);
		return obj.toString();
	}
	
	public static String getStore() {
		InputStream is = TesteOrgJson.class.getClassLoader().getResourceAsStream("store.json");
		JSONTokener tokener = new JSONTokener(is);
		JSONObject obj = new JSONObject(tokener);
		return obj.toString();
	}
}
