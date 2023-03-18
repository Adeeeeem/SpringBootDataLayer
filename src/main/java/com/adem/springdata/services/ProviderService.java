package com.adem.springdata.services;

import com.adem.springdata.entities.Provider;
import com.adem.springdata.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
		List <Provider> lp = (List <Provider>) providerRepository.findAll();
		
		if(lp.size() == 0)
			lp = null;
		return lp;
	}

	public Provider persistProvider(Provider provider)
	{
		return  this.providerRepository.save(provider);
	}
}
