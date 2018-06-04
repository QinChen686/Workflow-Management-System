package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="attend_Inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Attend implements Serializable
{
	private static final long serialVersionUID=48L;
	//标识属性
	@Id @Column(name="attend_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//出勤日期
	@Column(name="duty_day",nullable=false,length=50)
	private String dutyDay;
	//打卡时间
	@Column(name="punch_time")
	private Date punchTime;
	//是否为上班打卡
	@Column(name="is_come",nullable=false)
	private boolean isCome;
	//出勤类型
	@ManyToOne(targetEntity=AttendType.class)
	@JoinColumn(name="type_id",nullable=false)
	private AttendType type;
	//出勤人员
	@ManyToOne(targetEntity=Employee.class)
	@JoinColumn(name="emp_id",nullable=false)
	private Employee employee;
	//无参构造器
	public Attend()
	{
		
	}
	//初始化全部参数的构造器
	public Attend(Integer id, String dutyDay, Date punchTime, boolean isCome, AttendType type, Employee employee)
	{
		this.id=id;
		this.dutyDay=dutyDay;
		this.punchTime=punchTime;
		this.isCome=isCome;
		this.type=type;
		this.employee=employee;
	}
	//成员变量的getter和setter
	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setDutyDay(String dutyDay)
	{
		this.dutyDay=dutyDay;
	}
	public String getDutyDay()
	{
		return this.dutyDay;
	}
	public void setPunchTime(Date punchTime)
	{
		this.punchTime=punchTime;
	}
	public Date getPunchTime()
	{
		return this.punchTime;
	}
	public void setIsCome(boolean isCome)
	{
		this.isCome=isCome;
	}
	public boolean getIsCome()
	{
		return isCome;
	}
	public void setType(AttendType type)
	{
		this.type=type;
	}
	public AttendType getType()
	{
		return this.type;
	}
	public void setEmployee(Employee employee)
	{
		this.employee=employee;
	}
	public Employee getEmployee()
	{
		return this.employee;
	}
	//重写hashcode equals方法
	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=1;
		result=prime*result+((dutyDay==null)?0:dutyDay.hashCode());
		result=prime*result+((employee==null)?0:employee.hashCode());
		result=prime*result+(isCome?1231:1237);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj==this) return true;
		if(obj==null) return false;
		if(getClass()!=obj.getClass()) return false;
		Attend other=(Attend) obj;
		if(dutyDay==null)
		{
			if(other.dutyDay!=null) return false;
		}
		else if(!dutyDay.equals(other.dutyDay)) return false;
		
		if(employee==null)
		{
			if(other.employee!=null) return false;
		}
		else if(!employee.equals(other.employee)) return false;

		return !(isCome!=other.isCome);

	}
	
}