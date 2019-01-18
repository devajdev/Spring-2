package com.nt.test;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchDBToCsvTest {

	public static void main(String[] args) {
      ApplicationContext ctx=null;
      JobLauncher launcher=null;
      Job  job=null;
      JobExecution jobExecution=null;
      System.out.println("In main(-) .....");
		//create IOC container
      ctx=new ClassPathXmlApplicationContext("com/nt/cfgs/applicationContext.xml");
      //get Job Launcher
      launcher=ctx.getBean("jobLauncher",JobLauncher.class);
      //get Job
      job=ctx.getBean("examResultJob",Job.class);
      //run the job
      try{
    	jobExecution=(JobExecution) launcher.run(job, new JobParameters());
    	System.out.println(jobExecution.getExitStatus());
      }
      catch(Exception e){
    	  e.printStackTrace();
      }
      
      //close container
      ((AbstractApplicationContext) ctx).close();

	}

}
