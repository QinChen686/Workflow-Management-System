package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="app_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Application implements Serializable
{
	private static final long SerializableUID=48L;
	//标识属性
	@Id @Column(name="app_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//申请理由
	@Column(name="app_reason",length=100)
	private String reason;
	//处理标志
	@Column(name="result")
	private boolean result;
	//申请修改到type类型
	@ManyToOne(targetEntity=AttendType.class)
	@JoinColumn(name="type_id",nullable=false)
	private AttendType type;
	//关联的出勤记录
	@ManyToOne(targetEntity=Attend.class)
	@JoinColumn(name="attend_id",nullable=false)
	private Attend attend;
	//回复结果
	@OneToOne(targetEntity=CheckBack.class,mappedBy="app")
	private CheckBack check; 
	//定义无参构造器
	public Application()
	{
		
	}
	//定义所有参数的构造器
	public Application(Integer id, String reason, boolean result, AttendType type, Attend attend, CheckBack check)
	{
		this.id=id;
		this.reason=reason;
		this.result=result;
		this.type=type;
		this.attend=attend;
		this.check=check;
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
	public void setReason(String reason)
	{
		this.reason=reason;
	}
	public String getReason()
	{
		return this.reason;
	}
	public void setResult(boolean result)
	{
		this.result=result;
	}
	public boolean getResult()
	{
		return this.result;
	}
	public void setType(AttendType type)
	{
		this.type=type;
	}
	public AttendType getType()
	{
		return this.type;
	}
	public void setAttend(Attend attend)
	{
		this.attend=attend;
	}
	public Attend getAttend()
	{
		return this.attend;
	}
	public void setCheck(CheckBack check)
	{
		this.check=check;
	}
	public CheckBack getCheck()
	{
		return this.check;
	}
	
}