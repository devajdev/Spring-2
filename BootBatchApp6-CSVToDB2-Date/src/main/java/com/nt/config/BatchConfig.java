package com.nt.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nt.model.IExamResult;
import com.nt.model.OExamResult;
import com.nt.processor.ExamResultItemProcessor;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  
  @Autowired
  private DataSource ds;
  
  

   //ItemReader
  @Bean
  public FlatFileItemReader<IExamResult> reader() {
	  
    FlatFileItemReader<IExamResult> reader; 
    reader= new FlatFileItemReader<>();
    
    reader.setResource(new ClassPathResource("com/nt/csv/graduates.csv"));
    
    reader.setLineMapper(new DefaultLineMapper<IExamResult>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"id", "sem","percentage","dob"});
      }});
      
      setFieldSetMapper(new BeanWrapperFieldSetMapper<IExamResult>() {{
        setTargetType(IExamResult.class);
      }});
    }});
    return reader;
  }

  //Item Processor
  @Bean
  public ExamResultItemProcessor processor() {
    return new ExamResultItemProcessor();
  }

  //Item Writer
  @Bean
  public JdbcBatchItemWriter<OExamResult> writer() {
    JdbcBatchItemWriter<OExamResult> writer =null;
    writer=	new JdbcBatchItemWriter<>();

    writer.setDataSource(ds);
    writer.setSql("INSERT INTO EXAM_RESULT(ID,SEMISTER,PERCENTAGE,DOB)  VALUES (:id, :sem,:percentage,:dob)");
    writer.setItemSqlParameterSourceProvider(
        new BeanPropertyItemSqlParameterSourceProvider<OExamResult>());
    return writer;
  }
  


  // tag::jobstep[]
  @Bean
  public Job importUserJob() {
    return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer())
        .flow(step1()).end().build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1").<IExamResult, OExamResult>chunk(10).reader(reader())
        .processor(processor()).writer(writer()).build();
  }
  

 
}
