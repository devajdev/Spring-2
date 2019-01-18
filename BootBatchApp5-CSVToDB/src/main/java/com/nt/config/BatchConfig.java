package com.nt.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import com.nt.model.ExamResult;
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
  public FlatFileItemReader<ExamResult> reader() {
	  
    FlatFileItemReader<ExamResult> reader; 
    reader= new FlatFileItemReader<>();
    
    reader.setResource(new ClassPathResource("com/nt/csv/graduates.csv"));
    
    reader.setLineMapper(new DefaultLineMapper<ExamResult>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"id", "sem","percentage"});
      }});
      
      setFieldSetMapper(new BeanWrapperFieldSetMapper<ExamResult>() {{
        setTargetType(ExamResult.class);
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
  public JdbcBatchItemWriter<ExamResult> writer() {
    JdbcBatchItemWriter<ExamResult> writer =null;
    writer=	new JdbcBatchItemWriter<>();

    writer.setDataSource(ds);
    writer.setSql("INSERT INTO EXAM_RESULT(ID,SEMISTER,PERCENTAGE)  VALUES (:id, :sem,:percentage)");
    writer.setItemSqlParameterSourceProvider(
        new BeanPropertyItemSqlParameterSourceProvider<ExamResult>());
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
    return stepBuilderFactory.get("step1").<ExamResult, ExamResult>chunk(10).reader(reader())
        .processor(processor()).writer(writer()).build();
  }
  
 /* @Bean 
  @Primary
  public  CustomDateEditor  createCustomDE(){
	  return new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true);
  }
  
  
  @Bean 
  public CustomEditorConfigurer createCEConfigure(){
	  CustomEditorConfigurer configurer=new CustomEditorConfigurer();
  	  Map <java.lang.Class<?>,java.lang.Class<? extends java.beans.PropertyEditor>>  map=new HashMap();
  	  map.put(Date.class,createCustomDE().getClass());
  	  configurer.setCustomEditors(map);
  	  return configurer;
  	  
	  
  }*/
  // end::jobstep[]
}
