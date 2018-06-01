package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="checkback_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class CheckBack implements Serializable
{
	private static final long SerialVersionUID=48L;
	//标志属性
	@Id @Column(name="check_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//批复所属经理
	@ManyToOne(targetEntity=Manager.class)
	@JoinColumn(name="mgr_id",nullable=false)
	private Manager manager;
	//批复对应的申请
	@OneToOne(targetEntity=Application.class)
	@JoinColumn(name="app_id",nullable=false,unique=true)
	private Application app;
	//批复结果
	@Column(name="check_result",nullable=false)
	private boolean result;
	//批复理由
	@Column(name="check_reason",length=100)
	private String reason;
	//无参构造器
	public CheckBack()
	{
		
	}
	public CheckBack(Integer id, Manager manager, Application app, boolean result, String reason)
	{
		this.id=id;
		this.manager=manager;
		this.app=app;
		this.result=result;
		this.reason=reason;
	}
	//getter、setter方法
	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setManager(Manager manager)
	{
		this.manager=manager;
	}
	public Manager getManager()
	{
		return this.manager;
	}
	public void setApp(Application app)
	{
		this.app=app;
	}
	public Application getApp()
	{
		return this.app;
	}
	public void setResult(boolean result)
	{
		this.result=result;
	}
	public boolean getResult()
	{
		return this.result;
	}
	public void setReason(String reason)
	{
		this.reason=reason;
	}
	public String getReason()
	{
		return reason;
	}
	
}