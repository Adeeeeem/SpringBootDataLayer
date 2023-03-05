package com.adem.springdata.controllers;

import com.adem.springdata.entities.Provider;
import com.adem.springdata.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/rest/provider")
@CrossOrigin(origins="*")
public class RestProviderController
{
    @Autowired
	private ProviderRepository providerRepository;

	@GetMapping("/list")
	public List<Provider> getAllProviders()
	{
		return (List <Provider>) providerRepository.findAll();
	}

	@PostMapping("/add")
	public Provider addProvider(@Valid @RequestBody Provider provider)
	{
		return providerRepository.save(provider);
	}

	@PutMapping("/edit/{providerId}")
	public Provider updateProvider(@PathVariable Long providerId, @Valid @RequestBody Provider providerRequest)
	{
		return providerRepository.findById(providerId).map(provider ->
		{
			provider.setName(providerRequest.getName());
			provider.setEmail(providerRequest.getEmail());
			provider.setAddress(providerRequest.getAddress());

			return providerRepository.save(provider);
		}).orElseThrow(() -> new IllegalArgumentException("Provider Id " + providerId + " not found !"));
	}

	@GetMapping("/show/{providerId}")
	public Provider getProvider(@PathVariable Long providerId)
	{
		Optional<Provider> p = providerRepository.findById(providerId);

		return p.orElseThrow(() -> new NoSuchElementException("Element not found!"));
	}

	@DeleteMapping("/delete/{providerId}")
	public  Provider deleteProvider(@PathVariable Long providerId)
	{
		Optional <Provider> p = providerRepository.findById(providerId);

		if (p.isPresent())
			providerRepository.delete(p.get());
		else
			throw new NoSuchElementException("Element introuvable!");

		return p.get();
	}
}
