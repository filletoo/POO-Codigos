package carlosEduardoSouzaOliveira.estoqueLista;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestesEstoqueResumido {

	@Test
	public void produtosAbaixoDoEstoqueMinimo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Fornecedor forn2 = new Fornecedor(19, "Ambev");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1);
		Produto prod2 = new Produto(15, "Cerveja", 5, 1);
		Produto prod3 = new Produto(18, "Cerveja Pilsen", 5, 1);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);

		estoque.adicionarFornecedor(12, forn1);
		estoque.adicionarFornecedor(15, forn2);
		estoque.adicionarFornecedor(18, forn2);

		estoque.comprar(12, 3, 5);
		estoque.comprar(15, 1, 10);
		estoque.comprar(18, 5, 8);

		Produto[] produtos = {prod1, prod2};
		Produto[] produtosAbaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();

		assertArrayEquals(produtos, produtosAbaixoDoMinimo);
	}

	@Test
	public void compraItens() {
		Estoque estoque = new Estoque();
		Produto prod1 = new Produto(12, "Sorvete", 5, 1);

		estoque.incluir(prod1);
		estoque.comprar(12, 10, 4);

		assertEquals(10, estoque.quantidade(12));
		assertEquals(4.0, estoque.precoDeCompra(12), 0.001);
		assertEquals(8.0, estoque.precoDeVenda(12), 0.001);
	}

	@Test
	public void compraItensComPrecoNegativo() {
		Estoque estoque = new Estoque();
		Produto prod1 = new Produto(12, "Sorvete", 5, 1);

		estoque.incluir(prod1);
		estoque.comprar(12, 10, -5);

		assertEquals(0, estoque.quantidade(12));
	}

	@Test
	public void compraPrecoPonderado() {
		Estoque estoque = new Estoque();
		Produto prod1 = new Produto(12, "Sorvete", 5, 0.5);

		estoque.incluir(prod1);
		estoque.comprar(12, 10, 2.5);
		estoque.comprar(12, 10, 7.5);

		assertEquals(20, estoque.quantidade(12));
		assertEquals(5.0, estoque.precoDeCompra(12), 0.001);
		assertEquals(7.5, estoque.precoDeVenda(12), 0.001);
	}

	@Test
	public void vendeItens() {
		Estoque estoque = new Estoque();
		Produto prod1 = new Produto(12, "Sorvete", 5, 1);

		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);

		assertEquals(10.0, estoque.vender(12, 1), 0.001);
		assertEquals(19, estoque.quantidade(12));
	}

	@Test
	public void vendaMaiorQueEstoque() {
		Estoque estoque = new Estoque();
		Produto prod1 = new Produto(12, "Sorvete", 5, 1);

		estoque.incluir(prod1);
		estoque.comprar(12, 10, 4);

		assertEquals(-1.0, estoque.vender(12, 100), 0.001);
		assertEquals(10, estoque.quantidade(12));
	}

	@Test
	public void verificaFornecedorProduto() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1);

		estoque.incluir(prod1);
		estoque.adicionarFornecedor(12, forn1);

		Fornecedor[] fornecedores = estoque.fornecedores(12);

		assertEquals(1, fornecedores.length);
		assertEquals(forn1, fornecedores[0]);
	}

	@Test
	public void fornecedorProdutoNaoIncluido() {
		Estoque estoque = new Estoque();

		Fornecedor[] fornecedores = estoque.fornecedores(0);

		assertNull(fornecedores);
	}

	@Test
	public void naoIncluiProdutoDuplicado() {
		Estoque estoque = new Estoque();

		Produto prod1 = new Produto(12, "Sorvete", 5, 1);
		Produto prod2 = new Produto(12, "Outro", 5, 2);

		estoque.incluir(prod1);
		estoque.incluir(prod2);

		estoque.comprar(12, 5, 4);

		assertEquals(5, estoque.quantidade(12));
		assertEquals(4.0, estoque.precoDeCompra(12), 0.001);
	}
}