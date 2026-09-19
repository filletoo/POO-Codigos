package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivel;
import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoque{
	private ArrayList<Produto> arrayDeProdutos = new ArrayList<Produto>();
    
    public Produto pesquisar(int cod){
        for (Produto p: arrayDeProdutos){
            if (p.getCodigo() == cod) return p;
        }
        return null;
    }
    
    public boolean incluir(Produto p){
    	if (p == null || pesquisar(p.getCodigo()) != null) return false;
    	if (!(p.checarValidez())) return false;
    	
        arrayDeProdutos.add(p);
        return true;
    }

    public boolean comprar(int cod, int quant, double preco, Date val){
    	if (quant <= 0 || preco <= 0) return false;
    	Produto p = pesquisar(cod);
    	if (p == null) return false;
    
    	if (val != null) {
    		if (val.getTime() < System.currentTimeMillis()) return false;
    		if (!(p instanceof ProdutoPerecivel)) return false;
    		((ProdutoPerecivel)p).compra(quant, preco, val);
    	}else {
    		if (p instanceof ProdutoPerecivel) return false;
    		p.compra(quant, preco);
    	}
 
        return true;
    }
    
    public double vender(int cod, int quant){
    	if (quant <= 0) return -1;
        Produto p = pesquisar(cod);
        if (p != null) return p.venda(quant);
        return -1;
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
    
    public int quantidade(int cod){
        Produto p = pesquisar(cod);
        if (p != null) {
        	if (p instanceof ProdutoPerecivel) {
        		p.setQuant(((ProdutoPerecivel)p).contarNaoVencidos());
        	}
        	return p.getQuant();
        }
        return -1;
    }
    
    public int quantidadeVencidos(int cod) {
    	Produto p = pesquisar(cod);
    	if (p == null || !(p instanceof ProdutoPerecivel)) return 0;
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
