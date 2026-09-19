package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;
import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoqueComExcecoes{
	private ArrayList<Produto> arrayDeProdutos = new ArrayList<Produto>();
    
	public Produto pesquisar (int cod) throws ProdutoInexistente{
        for (Produto p: arrayDeProdutos){
            if (p.getCodigo() == cod) return p;
        }
        throw new ProdutoInexistente(cod);
    }
    
    public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos{
    	if (pesquisar(p.getCodigo()) != null) throw new ProdutoJaCadastrado(p.getCodigo());
    	if (p == null || !(p.checarValidez())) {
    		throw new DadosInvalidos();
    	}
    	
        arrayDeProdutos.add(p);
    }

    public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente,
	DadosInvalidos, ProdutoNaoPerecivel{
    	if (quant <= 0 || preco <= 0) throw new DadosInvalidos();
    	Produto p = pesquisar(cod);
    	if (p == null) throw new ProdutoInexistente(cod);
    
    	if (val != null) {
    		if (val.getTime() < System.currentTimeMillis()) throw new DadosInvalidos();
    		if (!(p instanceof ProdutoPerecivel)) throw new ProdutoNaoPerecivel(cod);
    		((ProdutoPerecivel)p).compra(quant, preco, val);
    	}else {
    		if (p instanceof ProdutoPerecivel) throw new DadosInvalidos();
    		p.compra(quant, preco);
    	}
    }
    
    public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido,
	DadosInvalidos{
    	if (quant <= 0) throw new DadosInvalidos();
        Produto p = pesquisar(cod);
        if (p != null) return p.venda(quant);
        throw new ProdutoInexistente(cod);
    }
    
    public ArrayList<Produto> estoqueVencido(){
    	ArrayList<Produto> vencidos = new ArrayList<Produto>();
    	ArrayList<Lote> lotes;
    	
    	for (Produto p: arrayDeProdutos) {
    		if (p instanceof ProdutoPerecivel) {
    			lotes = ((ProdutoPerecivel)p).getLotes();
	    		for (Lote l: lotes) {
	    			if (l.getValidade().getTime() <= System.currentTimeMillis()) {
	    				vencidos.add(p);
	    				break;
	    			}
	    		}
    		}
    	}
    	return vencidos;
    }
    
    public int quantidade(int cod) throws ProdutoInexistente{
        Produto p = pesquisar(cod);
        if (p != null) {
        	if (p instanceof ProdutoPerecivel) {
        		p.setQuant(((ProdutoPerecivel)p).contarNaoVencidos());
        	}
        	return p.getQuant();
        }
        throw new ProdutoInexistente(cod);
    }
    
    public int quantidadeVencidos(int cod) throws ProdutoInexistente {
    	Produto p = pesquisar(cod);
    	if (p == null) throw new ProdutoInexistente(cod);
    	if (!(p instanceof ProdutoPerecivel)) return 0;
    	int total = 0;
    	for (Lote l: ((ProdutoPerecivel)p).getLotes()) {
    		if (l.getValidade().getTime() <= System.currentTimeMillis()) {
    			total += l.getQuant();
    		}
    	}
    	return total;
    }
    
    public String movimentacao(int cod) {
    	Produto p = pesquisar(cod);
    	if (p != null) return p.getMovimentacao();
    	return null;
    }
    
    public Fornecedor[] fornecedores(int cod) {
    	Produto p = pesquisar(cod);
    	if (p != null) return p.getFornecedores();
    	return null;
    }
    
    public ArrayList<Produto> estoqueAbaixoDoMinimo() {
    	ArrayList<Produto> vetor1 = new ArrayList<Produto>();
    	
    	for (Produto p: arrayDeProdutos) {
    		if (p instanceof ProdutoPerecivel) {
    			p.setQuant(((ProdutoPerecivel)p).contarNaoVencidos());
    		}
    		
	    	if (p.getQuant() < p.getMin()) {
	    		vetor1.add(p);
	    	}
    	}
    	return vetor1;
    }
    
    public void adicionarFornecedor(int cod, Fornecedor f) {
    	Produto p = pesquisar(cod);
    	if (p != null) p.incluirfornecedor(f);
    }
    
    public double precoDeVenda(int cod) {
    	Produto p = pesquisar(cod);
    	if (p != null) return p.getPvenda();
    	return -1;
    	
    }
    
    public double precoDeCompra(int cod) {
    	Produto p = pesquisar(cod);
    	if (p != null) return p.getPcompra();
    	return -1;
    }
}
