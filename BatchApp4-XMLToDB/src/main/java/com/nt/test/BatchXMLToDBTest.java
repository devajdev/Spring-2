package com.nt.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchXMLToDBTest {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		Job job=null;
		JobLauncher launcher=null;
		JobExecution execution=null;
		//create IOC container
		ctx=new ClassPathXmlApplicationContext("com/nt/cfgs/applicationContext.xml");
		//get Beans
		launcher=ctx.getBean("jobLauncher",JobLauncher.class);
		job=ctx.getBean("job",Job.class);
		try{
		 execution=launcher.run(job,new JobParameters());
		 System.out.println("status::"+execution.getExitStatus());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//close container
		((AbstractApplicationContext) ctx).close();
		

	}

}
