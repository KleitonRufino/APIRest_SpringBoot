package json;

import java.util.GregorianCalendar;

import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.jayway.jsonpath.JsonPath;

/** Hello world! */
public class App {
	public static void main(String[] args) throws Exception {
		App app = new App();
		app.math();
		
	}

	public void helloWorld() throws Exception {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(((Expression) parser.parseExpression("'Hello World'.concat('!')")).getValue());
		System.out.println(((Expression) parser.parseExpression("'Hello World'.bytes.length")).getValue());
	}

	public void math(){
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("4*2").getValue(Integer.class));
		System.out.println(parser.parseExpression("4/2").getValue(Integer.class));
		System.out.println(parser.parseExpression("4-2").getValue(Integer.class));
		System.out.println(parser.parseExpression("4+2").getValue(Integer.class));
	}
	
	public void operator(){
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("4 == 2").getValue(Boolean.class));
		System.out.println(parser.parseExpression("4<2").getValue(Boolean.class));
		System.out.println(parser.parseExpression("4>2").getValue(Boolean.class));
		System.out.println(parser.parseExpression("!(4<2)").getValue(Boolean.class));
	}
	
	public void variables(){
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext(new Inventor());
		context.setVariable("newName", "new Name");
		System.out.println(parser.parseExpression("name = #newName").getValue(context));;
	}
	
	public void function() throws NoSuchMethodException, SecurityException{
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.registerFunction("reverseString", Inventor.class.getDeclaredMethod("reverseString", new Class[] {String.class}));
		System.out.println(parser.parseExpression("#reverseString('JSON')").getValue(context, String.class));
	}
	
	public void bean(){
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver((BeanResolver) new Inventor("Nikola Tesla", c.getTime(), "Serbian"));
		System.out.println(parser.parseExpression("@inventor").getValue(context));
	}
	
	public void elvisOperator(){
		ExpressionParser parser = new SpelExpressionParser();

		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		StandardEvaluationContext context = new StandardEvaluationContext(tesla);

		String name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);

		System.out.println(name); // Mike Tesla

		tesla.setName(null);

		name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);

		System.out.println(name); // Elvis Presley // 'Unknown'
	
	}
	
	public void object() {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("name");
		System.out.println((String) expression.getValue(tesla));
		
		EvaluationContext context = new StandardEvaluationContext(tesla);
		System.out.println((String) expression.getValue(context));
		
		expression = parser.parseExpression("name == 'Nikola Tesla'");
		context = new StandardEvaluationContext(tesla);
		System.out.println(expression.getValue(context, Boolean.class));
		
		expression = parser.parseExpression("print(name)");
		context = new StandardEvaluationContext(tesla);
		System.out.println((String) expression.getValue(context));
		System.out.println(parser.parseExpression("print(name)").getValue(context, String.class));
	}
	
	public void executeJsonPathWithSpringSpell(){
		String json = "{\"store\":{\"book\":[{\"category\":\"reference\", \"author\":\"Nigel Ress\", \"price\":8.95}]}}";
		TesteJson testeJson = new TesteJson();
		testeJson.setJson(json);
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("execute(json, '$.store.book[*].author')");	
		EvaluationContext context = new StandardEvaluationContext(testeJson);
		System.out.println(expression.getValue(context).toString());
	}
	
	class TesteJson{
		private String json;

		public Object execute(String json, String rule){
			return JsonPath.read(json, rule);
		}
		
		public TesteJson() {
			// TODO Auto-generated constructor stub
		}
		
		public String getJson() {
			return json;
		}

		public void setJson(String json) {
			this.json = json;
		}
		
		
	}


}
