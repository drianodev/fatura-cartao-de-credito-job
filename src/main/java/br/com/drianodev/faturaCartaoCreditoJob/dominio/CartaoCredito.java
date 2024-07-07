package br.com.drianodev.faturaCartaoCreditoJob.dominio;

import lombok.Data;

@Data
public class CartaoCredito {

	private int numeroCartaoCredito;
	private Cliente cliente;
}
