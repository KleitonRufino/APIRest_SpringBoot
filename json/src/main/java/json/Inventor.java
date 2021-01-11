package json;

import java.util.Date;

public class Inventor {
	private String name;
	private Date date;
	private String invetor;

	public Inventor() {
		// TODO Auto-generated constructor stub
	}

	public Inventor(String name, Date date, String invetor) {
		super();
		this.name = name;
		this.date = date;
		this.invetor = invetor;
	}
	
	public Inventor(String name, String invetor) {
		super();
		this.name = name;
		this.invetor = invetor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInvetor() {
		return invetor;
	}

	public void setInvetor(String invetor) {
		this.invetor = invetor;
	}

	public String print(String s) {
		return s;
	}

	public static String reverseString(String input) {
		StringBuilder backwards = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			backwards.append(input.charAt(input.length() - 1 - i));
		}
		return backwards.toString();
	}
}