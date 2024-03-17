package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class SimpleJobConfigration {

    @Bean
    public Job helloJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("hello1Job", jobRepository)
                .start(helloStep1(jobRepository, transactionManager))
                .build();

    }

    @Bean
    public Step helloStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("hello1Step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("================");
                    System.out.println(">> hello1Step ");
                    System.out.println("================");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }


    @Bean
    public Job
}
