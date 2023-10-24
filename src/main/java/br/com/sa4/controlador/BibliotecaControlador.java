package br.com.sa4.controlador;

import java.util.List;

import br.com.sa4.modelo.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.sa4.excecao.LivroNaoEncontradaExcecao;
import br.com.sa4.servico.IBibliotecaServico;

@Controller
@RequestMapping("/livro")
public class BibliotecaControlador {

	@Autowired
	private IBibliotecaServico service;

	@GetMapping("/")
	public String exibirPaginaInicial() {
		return"paginaInicio";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicao() {
		return "adicionarLivro";
	}

	@PostMapping("/salvar")
	public String salvarLivro(@ModelAttribute Biblioteca livro, Model modelo) {
		service.salvarLivro(livro);
		Long id = service.salvarLivro(livro).getIdLivro();
		String mensagem = "Fatura com id: '" + id + "' salva com sucesso!";
		modelo.addAttribute("message", mensagem);
		return "adicionarLivro";
	}

	@GetMapping("/listar")
	public String buscarLivros(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
		List<Biblioteca> livros = service.buscarLivros();
		modelo.addAttribute("listagem", livros);
		modelo.addAttribute("message", mensagem);
		return "listarBiblioteca";
	}

	@GetMapping("/editar")
	public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long idLivro) {
		String pagina = null;
		try {
			Biblioteca livro = service.bucarLivroPorId(idLivro);
			modelo.addAttribute("livro", livro);
			pagina = "editarLivro";
		} catch (LivroNaoEncontradaExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
			pagina = "redirect:listar";
		}
		return pagina;
	}

	@PostMapping("/atualizar")
	public String atualizarLivro(@ModelAttribute Biblioteca livro, RedirectAttributes atributos) {
		service.atualizarLivro(livro);
		Long id = livro.getIdLivro();
		atributos.addAttribute("message", "Livro com id: '" + id + "' atualizada com sucesso!");
		return "redirect:listar";
	}

	@GetMapping("/deletar")
	public String deletarLivroPorId(@RequestParam Long id, RedirectAttributes atributos) {
		try {
			service.deletarLivroPorId(id);
			atributos.addAttribute("message", "Livro com id: '" + id + "' excluída com sucesso!");
		} catch (LivroNaoEncontradaExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
		}
		return "redirect:listar";
	}
}