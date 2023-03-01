package com.adem.springdata.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Provider
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank(message = "Email is mandatory")
	@Column(name = "email", nullable = false)
	private String email;

	@NotBlank(message = "Address is mandatory")
	@Column(name = "address", nullable = false)
	private String address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
	private List <Article> articles;

	public Provider()
	{
		System.out.println("Hello From Constructor Block");
	}
	
	public Provider(String name, String address, String email)
	{
		this.name = name;
		this.address = address;
		this.email = email;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public List<Article> getArticles()
	{
		return articles;
	}

	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}

	@Override
	public String toString()
	{
		return "Provider [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
	}
	
	// Instance Block (first block to run)
	{
		System.out.println("Hello From Instance Block");
	}

	// Class Block (first block to run and it runs only one time)
	static
	{
		System.out.println("Hello From Class Block");
	}
}
