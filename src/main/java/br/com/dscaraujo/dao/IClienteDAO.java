package br.com.dscaraujo.dao;

import br.com.dscaraujo.domain.Cliente;
import java.util.List;

public interface IClienteDAO {

    Integer cadastrar(Cliente cliente) throws Exception;

    Cliente buscar(String cpf) throws Exception;

    Integer atualizar(Cliente cliente) throws Exception;

    Integer excluir(Cliente cliente) throws Exception;

    List<Cliente> buscarTodos() throws Exception;
}