package br.com.dscaraujo.dao;

import br.com.dscaraujo.domain.Produto;
import java.util.List;

public interface IProdutoDAO {

    Integer cadastrar(Produto produto) throws Exception;

    Produto buscar(String codigo) throws Exception;

    Integer atualizar(Produto produto) throws Exception;

    Integer excluir(Produto produto) throws Exception;

    List<Produto> buscarTodos() throws Exception;
}