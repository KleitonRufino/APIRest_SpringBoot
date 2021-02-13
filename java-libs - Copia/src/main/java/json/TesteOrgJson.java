package json;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

import net.minidev.json.JSONArray;

public class TesteOrgJson {

	public static void main(String[] args) throws IOException {
		readJson();
	}
	
	public static void createJson() {
		JSONObject myObj = new JSONObject();
		myObj.put("nome", "Kleiton");
		myObj.put("sobrenome", "Rufino");
		myObj.put("email", "kleiton@email.com");
		
		JSONArray telefones = new JSONArray();
		JSONObject telefone1 = new JSONObject();
		telefone1.put("ddd", "00");
		telefone1.put("numero", "9999999999");
		telefones.add(telefone1);
		
		myObj.put("telefones", telefones);
		
		System.out.println(myObj.toString());
		
	}
	
	public static void readJson() throws IOException {
		InputStream is = TesteOrgJson.class.getClassLoader().getResourceAsStream("json.json");
		JSONTokener tokener = new JSONTokener(is);
		JSONObject obj = new JSONObject(tokener);
		System.out.println(obj.toString());
		System.out.println(obj.getJSONArray("phoneNumbers").toString());
		
		JSONObject phone = new JSONObject();
		phone.put("type", "Xiaomi");
		phone.put("number", "1231231231");
		obj.getJSONArray("phoneNumbers").put(phone);
		
		System.out.println(obj.getJSONArray("phoneNumbers").toString());
	}
	
}
