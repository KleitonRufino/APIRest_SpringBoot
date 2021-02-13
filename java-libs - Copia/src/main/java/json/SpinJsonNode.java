package json;

import static org.camunda.spin.Spin.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.camunda.spin.impl.util.SpinIoUtil;
import org.camunda.spin.json.SpinJsonPathQuery;

public class SpinJsonNode {
	public static void main(String[] args){
		example2();
	}

	public static void example1() {
		InputStream is = SpinJsonNode.class.getClassLoader().getResourceAsStream("json2.json");
		String s = SpinIoUtil.inputStreamAsString(is);
		org.camunda.spin.json.SpinJsonNode json = JSON(s);
		if (json.hasProp("ordem") && json.prop("ordem").hasProp("item-ordem")) {
			org.camunda.spin.json.SpinJsonNode list = json.prop("ordem").prop("item-ordem");
			SpinJsonPathQuery ofertas = list.jsonPath("$..servico.oferta");
			List<Object> nodes = new ArrayList<Object>();
			for (int i = 0; i < ofertas.elementList().size(); i++) {
				org.camunda.spin.json.SpinJsonNode node = ofertas.elementList().get(i);
				if (node.isArray()) {
					for (Object spinJsonNode : node.elements().toArray()) {
						org.camunda.spin.json.SpinJsonNode obj = (org.camunda.spin.json.SpinJsonNode) spinJsonNode;
						nodes.add(obj);
					}
				}

			}
			json.prop("ofertasAssinate", nodes);
			String res = json.toString();
			System.out.println(res);
		}
	}
	
	public static void example2() {
		InputStream is = SpinJsonNode.class.getClassLoader().getResourceAsStream("json2.json");
		String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1)).lines()
				.collect(Collectors.joining("\n"));
		// String result = IOUtils.toString(is, StandardCharsets.ISO_8859_1);

		// String s = SpinIoUtil.inputStreamAsString(is);
		org.camunda.spin.json.SpinJsonNode json = JSON(text);
		org.camunda.spin.json.SpinJsonNode itemOrdems = json.prop("ordem").prop("item-ordem");
		List<Object> nodes = new ArrayList<Object>();
		for (int i = 0; i < itemOrdems.elements().size(); i++) {
			org.camunda.spin.json.SpinJsonNode comandoAprovisionamentos = itemOrdems.elements().get(i)
					.prop("perfil-aprovisionamento").prop("comando-aprovisionamento");
			for (int j = 0; j < comandoAprovisionamentos.elements().size(); j++) {
				if (comandoAprovisionamentos.elements().get(j).hasProp("servico")) {
					org.camunda.spin.json.SpinJsonNode ofertas = comandoAprovisionamentos.elements().get(j)
							.prop("servico").prop("oferta");
					for (int k = 0; k < ofertas.elements().size(); k++) {
						nodes.add(ofertas.elements().get(k));
					}
				}
			}
		}
		json.prop("ofertasAssinate", nodes);
		String res = json.toString();
		System.out.println(res);
	}

	public static void getProp() {
		org.camunda.spin.json.SpinJsonNode json = JSON("{\"customer\": \"Kermit\"}");
		org.camunda.spin.json.SpinJsonNode customerProperty = json.prop("customer");
		String customerName = customerProperty.stringValue();
		System.out.println(customerName);
	}

	public static void addProp() {
		org.camunda.spin.json.SpinJsonNode json = JSON("{\"customer\": [\"Kermit\", \"Waldo\"]}");

		ArrayList<Object> list = new ArrayList<Object>();
		list.add("new entry");
		list.add("new entry2");
		json.prop("new_array", list);

		Map<String, Object> object = new HashMap<String, Object>();
		object.put("new_entry", 42);
		object.put("new_entry2", "Yeah!");
		json.prop("new_object", object);

		System.out.println(json);
		System.out.println(json.prop("new_array"));
		System.out.println(json.prop("new_array").isArray());
	}

	public static void getFieldNames() {
		org.camunda.spin.json.SpinJsonNode json = JSON("{\"customer\": [\"Kermit\", \"Waldo\"]}");
		ArrayList<String> fieldNames = (ArrayList<String>) json.fieldNames();
		String fieldName1 = (String) fieldNames.get(0);
		System.out.println(fieldName1);

	}
}
