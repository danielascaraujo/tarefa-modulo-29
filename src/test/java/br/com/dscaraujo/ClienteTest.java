package br.com.dscaraujo;

import br.com.dscaraujo.dao.ClienteDAO;
import br.com.dscaraujo.dao.IClienteDAO;
import br.com.dscaraujo.domain.Cliente;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ClienteTest {

    private IClienteDAO clienteDAO = new ClienteDAO();

    @Test
    public void cadastrarTest() throws Exception {
        Cliente cliente = criarCliente();
        Integer qtd = clienteDAO.cadastrar(cliente);
        assertTrue(qtd == 1);

        Cliente clienteBD = clienteDAO.buscar(String.valueOf(cliente.getCpf()));
        assertNotNull(clienteBD);
        assertEquals(cliente.getNome(), clienteBD.getNome());

        // Limpa o cliente após o teste
        clienteDAO.excluir(clienteBD);
    }

    @Test
    public void buscarTest() throws Exception {
        Cliente cliente = criarCliente();
        clienteDAO.cadastrar(cliente);

        Cliente clienteBD = clienteDAO.buscar(String.valueOf(cliente.getCpf()));
        assertNotNull(clienteBD);
        assertEquals(cliente.getNome(), clienteBD.getNome());

        // Limpa o cliente após o teste
        clienteDAO.excluir(clienteBD);
    }

    @Test
    public void atualizarTest() throws Exception {
        Cliente cliente = criarCliente();
        clienteDAO.cadastrar(cliente);

        cliente.setNome("Novo Nome");
        Integer qtd = clienteDAO.atualizar(cliente);
        assertTrue(qtd == 1);

        Cliente clienteBD = clienteDAO.buscar(String.valueOf(cliente.getCpf()));
        assertEquals("Novo Nome", clienteBD.getNome());

        // Limpa o cliente após o teste
        clienteDAO.excluir(clienteBD);
    }

    @Test
    public void excluirTest() throws Exception {
        Cliente cliente = criarCliente();
        clienteDAO.cadastrar(cliente);

        Integer qtd = clienteDAO.excluir(cliente);
        assertTrue(qtd == 1);

        Cliente clienteBD = clienteDAO.buscar(String.valueOf(cliente.getCpf()));
        assertNull(clienteBD);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        Cliente cliente1 = criarCliente();
        clienteDAO.cadastrar(cliente1);

        Cliente cliente2 = criarCliente();
        clienteDAO.cadastrar(cliente2);

        List<Cliente> clientes = clienteDAO.buscarTodos();
        assertTrue(clientes.size() >= 2);

        // Limpa os clientes após o teste
        clienteDAO.excluir(cliente1);
        clienteDAO.excluir(cliente2);
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setCpf(gerarCPF()); // Gera CPF aleatório e válido
        cliente.setTel(11999999999L);
        cliente.setEndereco("Rua Teste");
        cliente.setNumero(123L);
        cliente.setCidade("São Paulo");
        cliente.setEstado("SP");
        return cliente;
    }

    private Long gerarCPF() {
        Random random = new Random();
        long cpf = 10000000000L + random.nextInt(900000000); // Gera um número aleatório de 11 dígitos
        return cpf;
    }
}