package com.nt.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import com.nt.model.ExamResult;
import com.nt.processor.ExamResultItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilder;
	@Autowired
	private StepBuilderFactory stepBuilder;
	
	@Value("com/nt/csv/graduates*.csv")
	  private Resource[] resources;
	
	
	   @Bean
	  public Job createJob() {
	    return jobBuilder.get("createJob").incrementer(new RunIdIncrementer()).
	        flow(step1()).end().build();
	  }

	  @Bean
	  public Step step1() {
	    return stepBuilder.get("step1").<ExamResult, ExamResult>chunk(10)
	        .reader(mrReader()).processor(processor()).writer(writer()).build();
	  }
	  
	  @Bean
	  public ExamResultItemProcessor processor(){
		  return new ExamResultItemProcessor();
	  }
	  
	  
	  @Bean
	  public FlatFileItemReader<ExamResult> reader() {
	    FlatFileItemReader<ExamResult> reader =null;
	    reader=new FlatFileItemReader<ExamResult>();
	    reader.setLineMapper(new DefaultLineMapper() {{
	      setLineTokenizer(new DelimitedLineTokenizer() {{
	        setNames(new String[]{"id", "sem","percentage","dob"});
	      }});
	      setFieldSetMapper(new BeanWrapperFieldSetMapper<ExamResult>() {{
	        setTargetType(ExamResult.class);
	      }});
	    }});
	    return reader;
	  }
	  
	  @Bean
	  public MultiResourceItemReader<ExamResult> mrReader() {
	    MultiResourceItemReader<ExamResult> resourceItemReader = new MultiResourceItemReader<ExamResult>();
	    resourceItemReader.setResources(resources);
	    resourceItemReader.setDelegate(reader());
	    return resourceItemReader;
	  }
	  
	  @Bean
	  public FlatFileItemWriter<ExamResult> writer() {
	    FlatFileItemWriter<ExamResult> writer = new FlatFileItemWriter<>();
	    
	    writer.setResource(new FileSystemResource("output/superGraduates.all.csv"));

	    writer.setLineAggregator(new DelimitedLineAggregator<ExamResult>() {{
	      setDelimiter(",");
	      setFieldExtractor(new BeanWrapperFieldExtractor<ExamResult>() {{
	        setNames(new String[]{"id","sem","percentage","dob"});
	      }});
	    }});
	    return writer;
	  }

}
