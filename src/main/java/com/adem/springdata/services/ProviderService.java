package com.adem.springdata.services;

import com.adem.springdata.entities.Provider;
import com.adem.springdata.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProviderService
{
	private final ProviderRepository providerRepository;

	@Autowired
	public ProviderService(ProviderRepository providerRepository)
	{
		this.providerRepository = providerRepository;
	}

	public List<Provider> getAllProviders()
	{
		return (List<Provider>)providerRepository.findAll();
	}

	public Provider persistProvider(Provider provider)
	{
		return  this.providerRepository.save(provider);
	}
}
