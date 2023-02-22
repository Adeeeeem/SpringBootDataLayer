package com.adem.springdata.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
    
    @Override
   	public String toString()
    {
   		return "Provider [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
   	}
}
