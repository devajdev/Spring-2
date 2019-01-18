package com.nt.test;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.nt.config.BatchConfig;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@Import(value=BatchConfig.class)
public class BootBatchApp7MultiResourceReaderApplication {
	@Autowired
	private static SimpleJobLauncher launcher;

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		Job job=null;
		JobExecution execution=null;
		
		ctx=SpringApplication.run(BootBatchApp7MultiResourceReaderApplication.class, args);
		//get Beans
		job=ctx.getBean("createJob",Job.class);
		try{
		   execution=launcher.run(job,new JobParameters());
		  System.out.println("Status:::"+execution.getStatus());  
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		//close contianer
		((ConfigurableApplicationContext) ctx).close();
	}//main
}//class
