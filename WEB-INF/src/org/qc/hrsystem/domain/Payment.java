package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="payment_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Payment implements Serializable
{
	private static final long SerialVersionUID=48L;
	//主键
	@Id @Column(name="payment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//薪水
	@Column(name="pay_amount")
	private double amount;
	//月份
	@Column(name="pay_month")
	private String month;
	//员工
	@ManyToOne(targetEntity=Employee.class)
	@JoinColumn(name="emp_id",nullable=false)
	private Employee employee;
	//无参构造器
	public Payment()
	{
		
	}
	public Payment(Integer id, String month, Employee employee, double amount)
	{
		this.id=id;
		this.month=month;
		this.employee=employee;
		this.amount=amount;
	}
	//定义setter和getter方法
	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setMonth(String month)
	{
		this.month=month;
	}
	public String getMonth()
	{
		return this.month;
	}
	public void setAmount(double amount)
	{
		this.amount=amount;
	}
	public double getAmount()
	{
		return this.amount;
	}
	public void setEmployee(Employee employee)
	{
		this.employee=employee;
	}
	public Employee getEmployee()
	{
		return this.employee;
	}
	
}