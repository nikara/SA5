package br.com.sa4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import  br.com.sa4.modelo.Biblioteca;

public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Long> {

}