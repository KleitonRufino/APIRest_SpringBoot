package java8;

class Usuario {

	private String nome;
	private int pontos;
	private boolean moderador;

	public Usuario(String nome) {
		this.nome = nome;
	}
	
 	public Usuario(String nome, int pontos) {
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = false;
	}
 	
 	public Usuario(String nome, int pontos, boolean moderador) {
 		this.nome = nome;
 		this.pontos = pontos;
 		this.moderador = moderador;
 	}

 	public boolean isModerador() {
		return moderador;
	}

 	public void tornaModerador() {
		this.moderador = true;
	}

 	public String getNome() {
		return nome;
	}

 	public int getPontos() {
		return pontos;
	}
 	
	@Override
	public String toString() {
		return "Usuario " + nome;
	}
}
