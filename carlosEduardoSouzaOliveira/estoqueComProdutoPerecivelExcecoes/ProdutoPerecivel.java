package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;
import java.util.Date;
import java.util.ArrayList;

public class ProdutoPerecivel extends Produto{
	private ArrayList<Lote> lotes = new ArrayList<Lote>();
	
	public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
		super(cod, desc, min, lucro);
	}
	
	public int contarNaoVencidos() {
		int total = 0;
		for (Lote l: lotes) {
			if (l.getValidade().getTime() > System.currentTimeMillis()) {
				total += l.getQuant();
			}
		}
		return total;
	}
	
	public boolean compra(int quant, double val, Date validade) {
		compra(quant, val);
		
        Lote l = new Lote(quant, validade);
		lotes.add(l);
		return true;
	}
	
	public double venda(int quant) {
		if (lotes.size() == 0) return -1;
		
		this.setQuant(contarNaoVencidos());
		if (getQuant() < quant) return -1;
		
		Lote perto_vencer;
		int quantv = quant;
		
		while (quant > 0){
			perto_vencer = lotes.getFirst();
			for (Lote l: lotes) {
				if (l.getValidade().getTime() > System.currentTimeMillis()) {
					perto_vencer = l;
					break;
				}
			}
			
			for (Lote l: lotes) {
				if (l.getValidade().getTime() > System.currentTimeMillis() && l.getQuant() > 0) {
					if (l.getValidade().getTime() < perto_vencer.getValidade().getTime()) {
						perto_vencer = l;
					}
				}
			}
			
			if (quant <= perto_vencer.getQuant()) {
				perto_vencer.setQuant(perto_vencer.getQuant() - quant);
				this.setQuant(this.getQuant() - quant);
				quant = 0;
			}else {
				quant -= perto_vencer.getQuant();
				this.setQuant(this.getQuant() - perto_vencer.getQuant());
				perto_vencer.setQuant(0);
			}
		}
		
		this.setMovimentacao(this.getMovimentacao() + "Vendidos: " + quantv + " Estoque: " + this.getQuant() + "\n"); 
		return quantv*getPvenda();
	}
	
	public ArrayList<Lote> getLotes(){
		return lotes;
	}
	
}
