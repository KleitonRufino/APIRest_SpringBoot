//***********************************************************************
//
//	Cap�tulo 3 - Interfaces Funcionais
//
//**********************************************************************

package java8;

public class InterfaceFuncional {

	public static void main(String ... args) {

 		// Imprimir valores de 0 at� 1000 - Vers�o 1
		Runnable r1 = new Runnable() {
			public void run() {
				for(int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			}
		};

 		new Thread(r1).start();

 		// Imprimir valores de 0 at� 1000 - Vers�o 2
		Runnable r2 = () -> {
			for(int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		};

 		new Thread(r2).start();

 		// Imprimir valores de 0 at� 1000 - Vers�o 3
		new Thread(() -> {
			for(int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		}).start();

 		// M�todo respons�vel por validar CPF - Vers�o 1
		Validador<String> validatorCPF1 = new Validador<String>() {
			public boolean valida(String valor) {
				return valor.matches("[0-9]{5}-[0-9]{3}");
			}
		};

 		// M�todo respons�vel por validar CPF - Vers�o 2
		Validador<String> validatorCPF2 = valor -> {
			return valor.matches("[0-9]{5}-[0-9]{3}");
		};

 		// M�todo respons�vel por validar CPF - Vers�o 3
		Validador<String> validatorCPF3 = valor -> valor.matches("[0-9]{5}-[0-9]{3}");

 		// O que � o objeto retornado pela express�o?
		Runnable obj = () -> {
			System.out.println("O que sou eu? Que Lambda?");
		};

 		System.out.println(obj);
		System.out.println(obj.getClass());
	}
}
