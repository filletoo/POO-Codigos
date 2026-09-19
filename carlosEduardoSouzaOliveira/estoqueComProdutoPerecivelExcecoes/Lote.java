package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;
import java.util.Date;
public class Lote {
	private int quant;
	private Date val;
	
	public Lote(int quant, Date validade) {
		this.val = validade;
		this.quant = quant;
	}

	public Date getValidade() {
		return val;
	}

	public void setValidade(Date val) {
		this.val = val;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}
	
	
}
