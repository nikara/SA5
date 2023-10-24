package br.com.sa4.implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sa4.excecao.LivroNaoEncontradaExcecao;
import  br.com.sa4.modelo.Biblioteca;
import br.com.sa4.repositorio.BibliotecaRepositorio;
import br.com.sa4.servico.IBibliotecaServico;
 
@Service
public class BibliotecaServicoImpl implements IBibliotecaServico {
 
	@Autowired
	private BibliotecaRepositorio repositorio;
 
	@Override
	public Biblioteca salvarLivro(Biblioteca livro) {
		return repositorio.save(livro);
	}
 
	@Override
	public List<Biblioteca> buscarLivros() {
		return repositorio.findAll();
	}
 
	@Override
	public Biblioteca bucarLivroPorId(Long id) {
		Optional<Biblioteca> opcional = repositorio.findById(id);
		if (opcional.isPresent()) {
			return opcional.get();
		} else {
			throw new LivroNaoEncontradaExcecao("Livro com id: " + id + " não encontrada.");
		}
	}
 
	@Override
	public void deletarLivroPorId(Long id) {
		repositorio.delete(bucarLivroPorId(id));
	}
 
	@Override
	public void atualizarLivro(Biblioteca invoice) {
		repositorio.save(invoice);
	}
}