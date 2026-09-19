package carlosEduardoSouzaOliveira.estoqueLista;

public class Estoque {
	private NoDaListaDeProdutos ListaProdutos = new NoDaListaDeProdutos();
	
	public Produto procurar(int cod){
        return ListaProdutos.procurar(cod);
    }
	
	public void incluir(Produto p){
       if (ListaProdutos.procurar(p.getCod()) == null) {
    	   ListaProdutos.incluir(p);
       }
    }
	
    public void comprar(int cod, int quant, double preco){
    	if (quant <= 0 || preco <= 0) return;
        Produto p = ListaProdutos.procurar(cod);
        if (p != null) p.compra(quant, preco);
    }
    
    public double vender(int cod, int quant){
    	if (quant <= 0) return - 1;
        Produto p = ListaProdutos.procurar(cod);
        if (p != null) return p.venda(quant);
        return -1;
    }

    public int quantidade(int cod){
        Produto p = ListaProdutos.procurar(cod);
        if (p != null) return p.getQuant();
        return -1;
    }
    
    public String movimentacao(int cod) {
    	Produto p = ListaProdutos.procurar(cod);
    	if (p != null) return p.getMovimentacao();
    	return null;
    }
    
    public Fornecedor[] fornecedores(int cod) {
    	Produto p = ListaProdutos.procurar(cod);
    	if (p != null) return p.getFornecedores();
    	return null;
    }
    
    public Produto[] estoqueAbaixoDoMinimo() {
    	Produto[] vetor1 = new Produto[100];
    	Produto[] copia;
    	NoDaListaDeProdutos atual = ListaProdutos;
    	int x = 0;
    	while (atual.getProduto() != null) {
    		if (atual.getProduto().getQuant() < atual.getProduto().getMin()) {
    			vetor1[x++] = atual.getProduto();
    		}
    		if (atual.getProx() == null) break;
    		atual = atual.getProx();
 
    	}
    	copia = new Produto[x];
    	for (int i = 0; i < x; i++) {
    		copia[i] = vetor1[i];
    	}
    	return copia;
    }
    
    public void adicionarFornecedor(int cod, Fornecedor f) {
    	Produto p = ListaProdutos.procurar(cod);
    	if (p != null) p.incluirfornecedor(f);
    }
    
    public double precoDeVenda(int cod) {
    	Produto p = ListaProdutos.procurar(cod);
    	if (p != null) return p.getPvenda();
    	return -1;
    }
    
    public double precoDeCompra(int cod) {
    	Produto p = ListaProdutos.procurar(cod);
    	if (p != null) return p.getPcompra();
    	return -1;
    }
}
