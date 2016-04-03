package financeiro.cheque;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * Esta annotation indica que este objeto relacional n�o precisa de um
 * ID, pois � identificada pelo ID da classe onde este objeto est� contido.
 * Geralmente � utilizada para a cria��o de chaves-prim�ria compostas utilizando
 * o hibernate.
 */
@Embeddable
public class ChequeId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Esta annotation indica se este campos � opcional ou n�o. Neste caso,
	 * criamos no banco um campo do tipo NOT NULL
	 */
	@Basic(optional = false)
	@Column(name = "cheque", nullable = false)
	private Integer cheque;

	@Basic(optional = false)
	@Column(name = "conta", nullable = false)
	private Integer conta;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cheque == null) ? 0 : cheque.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChequeId other = (ChequeId) obj;
		if (cheque == null) {
			if (other.cheque != null)
				return false;
		} else if (!cheque.equals(other.cheque))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		return true;
	}

	public Integer getCheque() {
		return cheque;
	}

	public void setCheque(Integer cheque) {
		this.cheque = cheque;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

}
