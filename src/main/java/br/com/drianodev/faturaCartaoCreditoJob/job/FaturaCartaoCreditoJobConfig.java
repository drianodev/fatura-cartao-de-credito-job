package br.com.drianodev.faturaCartaoCreditoJob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaCartaoCreditoJobConfig {

	@Autowired
	private JobRepository jobRepository;

	@Bean
	public Job faturaCartaoCreditoJob(Step faturaCartaoCreditoStep) {
		return new JobBuilder("faturaCartaoCreditoStep", jobRepository)
				.start(faturaCartaoCreditoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
