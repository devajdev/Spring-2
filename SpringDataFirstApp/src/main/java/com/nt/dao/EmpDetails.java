package com.nt.dao;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "EMPDETAILS")
public class EmpDetails{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer eno;
	private String ename;
	private String desg;
	private int salary;

	/*public EmpDetails() {
		System.out.println("EmpDetails::0-param constructor");
	}

	public EmpDetails(Integer eno, String ename, String desg, int salary) {
		System.out.println("EmpDetails::4-param constructor");
		this.eno = eno;
		this.ename = ename;
		this.desg = desg;
		this.salary = salary;
	}*/

	//@Id
	//@Column(name="ENO",length=5)
	/*@GenericGenerator(name = "gen1", strategy = "increment")
	@GeneratedValue(generator="gen1")*/
	//@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getEno() {
		return eno;
	}

	public void setEno(Integer eno) {
		this.eno = eno;
	}

	//@Column(name="ENAME",length=15)
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	//@Column(name="DESG",length=15)
	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	//@Column(name="SALARY",length=7)
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
