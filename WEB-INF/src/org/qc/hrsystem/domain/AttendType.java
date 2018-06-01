package org.qc.hrsystem.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="attendType_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class AttendType implements Serializable
{
	private static final long SerialVersionUID=48L;
	@Id @Column(name="type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//标识属性
	private Integer id;
	//类型名
	@Column(name="type_name",length=50,nullable=false)
	private String name;
	//罚款
	@Column(name="type_amerce",nullable=false)
	private double amerce;
	//定义setter和getter方法
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
	public void setAmerce(double amerce)
	{
		this.amerce=amerce;
	}
	public double getAmerce()
	{
		return this.amerce;
	}
}