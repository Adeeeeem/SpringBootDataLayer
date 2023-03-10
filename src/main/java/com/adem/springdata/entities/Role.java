package com.adem.springdata.entities;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	public Role()
	{
		this.name = "";
	}

	public Role(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Role [id=" + id + ", name=" + name + "]";
	}
}