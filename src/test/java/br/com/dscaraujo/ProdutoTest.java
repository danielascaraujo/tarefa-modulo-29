package br.com.dscaraujo;

import br.com.dscaraujo.dao.IProdutoDAO;
import br.com.dscaraujo.dao.ProdutoDAO;
import br.com.dscaraujo.domain.Produto;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ProdutoTest {

    private IProdutoDAO produtoDAO = new ProdutoDAO();

    @Test
    public void cadastrarTest() throws Exception {
        Produto produto = criarProduto();
        Integer qtd = produtoDAO.cadastrar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertEquals(produto.getNome(), produtoBD.getNome());

        // Limpa o produto após o teste
        produtoDAO.excluir(produtoBD);
    }

    @Test
    public void buscarTest() throws Exception {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertEquals(produto.getNome(), produtoBD.getNome());

        // Limpa o produto após o teste
        produtoDAO.excluir(produtoBD);
    }

    @Test
    public void atualizarTest() throws Exception {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        produto.setNome("Novo Nome");
        Integer qtd = produtoDAO.atualizar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        assertEquals("Novo Nome", produtoBD.getNome());

        // Limpa o produto após o teste
        produtoDAO.excluir(produtoBD);
    }

    @Test
    public void excluirTest() throws Exception {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        Integer qtd = produtoDAO.excluir(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        assertNull(produtoBD);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        Produto produto1 = criarProduto();
        produtoDAO.cadastrar(produto1);

        Produto produto2 = criarProduto();
        produtoDAO.cadastrar(produto2);

        List<Produto> produtos = produtoDAO.buscarTodos();
        assertTrue(produtos.size() >= 2);

        // Limpa os produtos após o teste
        produtoDAO.excluir(produto1);
        produtoDAO.excluir(produto2);
    }

    private Produto criarProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCodigo(gerarCodigo());
        produto.setDescricao("Descrição do Produto Teste");
        produto.setPreco(10.0);
        produto.setEstoque(100); // Adiciona a definição do estoque
        return produto;
    }

    private String gerarCodigo() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return "PROD" + randomNumber;
    }
}