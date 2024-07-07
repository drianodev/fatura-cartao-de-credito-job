package br.com.drianodev.faturaCartaoCreditoJob.step;

import br.com.drianodev.faturaCartaoCreditoJob.dominio.FaturaCartaoCredito;
import br.com.drianodev.faturaCartaoCreditoJob.dominio.Transacao;
import br.com.drianodev.faturaCartaoCreditoJob.reader.FaturaCartaoCreditoReader;
import br.com.drianodev.faturaCartaoCreditoJob.writer.TotalTransacoesFooterCallback;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FaturaCartaoCreditoStepConfig {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	Step faturaCartaoCreditoStep(
			ItemStreamReader<Transacao> lerTransacoesReader,
			ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregarDadosClienteProcessor,
			ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito,
			TotalTransacoesFooterCallback listener) {
		return new StepBuilder("faturaCartaoCreditoStep", jobRepository)
				.<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1, transactionManager)
				.reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
				.processor(carregarDadosClienteProcessor)
				.writer(escreverFaturaCartaoCredito)
				.listener(listener)
				.build();
	}
}
