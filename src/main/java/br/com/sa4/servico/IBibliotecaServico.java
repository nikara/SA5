package  br.com.sa4.servico;

import java.util.List;
import br.com.sa4.modelo.Biblioteca;

public interface IBibliotecaServico {

	public Biblioteca salvarLivro(Biblioteca livro);

	public List<Biblioteca> buscarLivros();

	public Biblioteca bucarLivroPorId(Long id);

	public void deletarLivroPorId(Long id);

	public void atualizarLivro(Biblioteca livro);

}