package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="employee_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name="emp_type",discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue(value="1")
public class Employee implements Serializable
{
	private static final long serialVersionUID=48L;
	//标识属性
	@Id @Column(name="emp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//员工姓名
	@Column(name="emp_name",nullable=false,length=50,unique=true)
	private String name;
	//员工密码
	@Column(name="emp_pass",nullable=false)
	private String pass;
	//员工工资
	@Column(name="emp_salary",nullable=false)
	private double salary;
	//员工的经理
	@ManyToOne(targetEntity=Manager.class)
	@JoinColumn(name="mgr_id")
	private Manager manager;
	//员工的出勤记录
	@OneToMany(targetEntity=Attend.class,mappedBy="employee")
	private Set<Attend> attends = new HashSet<Attend>();
	//员工对应的工资支付记录
	@OneToMany(targetEntity=Payment.class,mappedBy="employee")
	private Set<Payment> payments = new HashSet<Payment>();
	//无参数构造器
	public Employee()
	{
		
	}
	public Employee(Integer id, String name, String pass, double salary, Manager manager, Set<Attend> attends, Set<Payment> payments)
	{
		this.id=id;
		this.name=name;
		this.salary=salary;
		this.manager=manager;
		this.attends=attends;
		this.payments=payments;
	}
	//get和set方法
	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	public String getPass()
	{
		return this.pass;
	}
	public void setSalary(double salary)
	{
		this.salary=salary;
	}
	public double getSalary()
	{
		return this.salary;
	}
	public void setManager(Manager manager)
	{
		this.manager=manager;
	}
	public Manager getManager()
	{
		return this.manager;
	}
	public void setAttends(Set<Attend> attends)
	{
		this.attends=attends;
	}
	public Set<Attend> getAttends()
	{
		return this.attends;
	}
	public void setPayments(Set<Payment> payments)
	{
		this.payments=payments;
	}
	public Set<Payment> getPayments()
	{
		return this.payments;
	}
	//重写hashCode与equals
	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=1;
		result = prime * result + ((name==null)?0:name.hashCode());
		result = prime * result + ((pass==null)?0:pass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj) return true;
		if(obj==null) return false;
		if(getClass()!=obj.getClass()) return false;
		Employee other=(Employee) obj;
		if(name==null)
		{
			if(other.name!=null) return false;
		}
		else if (!name.equals(other.name)) return false;
		if(pass==null)
		{
			if(other.pass!=null) return false;
		}
		else if(!pass.equals(other.pass)) return false;
		return true;
	}
	
	
}