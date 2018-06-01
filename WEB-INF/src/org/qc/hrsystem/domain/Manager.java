package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorValue(value="2")
public class Manager extends Employee implements Serializable
{
	private static final long serialVersionUID=48L;
	//经理所处的部门
	@Column(name="dept_name",length=50)
	private String dept;
	//经理对应的员工
	@OneToMany(targetEntity=Employee.class, mappedBy="manager")
	private Set<Employee> employees = new HashSet<Employee>();
	@OneToMany(targetEntity=CheckBack.class, mappedBy="manager")
	private Set<CheckBack> checks=new HashSet<CheckBack>();
	public Manager()
	{
		
	}
	public Manager(String dept, Set<Employee> employees, Set<CheckBack> checks)
	{
		this.dept=dept;
		this.employees=employees;
		this.checks=checks;
	}
	public void setDept(String dept)
	{
		this.dept=dept;
	}
	public String getDept()
	{
		return this.dept;
	}
	public void setEmployees(Set<Employee> employees)
	{
		this.employees=employees;
	}
	public Set<Employee> getEmployees()
	{
		return this.employees;
	}
	public void setChecks(Set<CheckBack> checks) { this.checks=checks; }
	public Set<CheckBack> getChecks()
	{
		return this.checks;
	}
	
}