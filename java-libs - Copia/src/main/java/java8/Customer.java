package java8;

class Customer {

	private String name;
	
	public Customer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
