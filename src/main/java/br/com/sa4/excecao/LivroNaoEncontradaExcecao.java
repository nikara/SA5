
package br.com.sa4.excecao;

public class LivroNaoEncontradaExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivroNaoEncontradaExcecao() {
		super();
	}

	public LivroNaoEncontradaExcecao(String mensagemPersonalizada) {
		super(mensagemPersonalizada);
	}
}