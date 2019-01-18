package com.nt.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.nt.config.BatchConfig;

@SpringBootApplication
@Import(value=BatchConfig.class)
public class BootBatchApp5CsvToDbApplication {
	@Autowired
	private static SimpleJobLauncher launcher;
	

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		Job job=null;
		JobExecution execution;
		ctx=SpringApplication.run(BootBatchApp5CsvToDbApplication.class, args);
		//get Bean (job)
		job=ctx.getBean("importUserJob",Job.class);
		try{
			execution=launcher.run(job,new JobParameters());
			System.out.println("Execuction status::"+execution.getStatus());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Batch Processing completed");
		
		//close container
		((ConfigurableApplicationContext) ctx).close();
		
	}
}
