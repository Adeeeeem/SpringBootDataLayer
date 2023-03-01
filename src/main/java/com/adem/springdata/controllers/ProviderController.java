package com.adem.springdata.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adem.springdata.entities.Provider;
import com.adem.springdata.repositories.ProviderRepository;

@Controller
@RequestMapping("/provider")
public class ProviderController
{
	private final ProviderRepository providerRepository;
	private final ProviderService providerService;

	public ProviderController()
	{
		this.providerRepository = null;
	}

	@Autowired
	public ProviderController(ProviderRepository providerRepository, ProviderService providerService)
	{
		this.providerRepository = providerRepository;
		this.providerService = providerService;
	}

	@GetMapping("list")
	//@ResponseBody
	public String listProviders(Model model)
	{
		// Without Service
		//List <Provider> lp = (List <Provider>) providerRepository.findAll();
		
		//if(lp.size() == 0)
			//lp = null;

		// With Service
		List <Provider> lp = this.providerService.getAllProviders();

		model.addAttribute("providers", lp);
		
		return "provider/listProviders";
	}
	
	@GetMapping("add")
	public String showAddProviderForm(Model model)
	{
		Provider provider = new Provider();
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}
	
	@PostMapping("add")
	public String addProvider(@Valid Provider provider, BindingResult result)
	{
		if(provider.getName() == "")
			provider.setName(null);

		if(provider.getEmail() == "")
			provider.setEmail(null);
		
		if(provider.getAddress() == "")
			provider.setAddress(null);

		if (result.hasErrors())
			return "provider/addProvider";

		// Without Service
		//providerRepository.save(provider);

		// With Service
		this.providerService.persistProvider(provider);

		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id, Model model)
	{
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));

		providerRepository.delete(provider);
		
		/*model.addAttribute("providers", providerRepository.findAll());
		return "provider/listProviders";*/

		return "redirect:../list";
	}
	
	@GetMapping("edit/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") long id, Model model)
	{
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		
		model.addAttribute("provider", provider);
		
		return "provider/updateProvider";
	}

	@PostMapping("update")
	public String updateProvider(@Valid Provider provider, BindingResult result, Model model)
	{
		if(provider.getName() == "")
			provider.setName(null);

		if(provider.getEmail() == "")
			provider.setEmail(null);
		
		if(provider.getAddress() == "")
			provider.setAddress(null);

		if (result.hasErrors())
			return "provider/updateProvider";

		providerRepository.save(provider);

		return"redirect:list";
	}

	@GetMapping("show/{id}")
	public String showProvider(@PathVariable("id") long id, Model model)
	{
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));

		List <Article> articles = providerRepository.findArticlesByProvider(id);

		for (Article a : articles)
			System.out.println("Article = " + a.getLabel());

		model.addAttribute("articles", articles);
		model.addAttribute("provider", provider);

		return "provider/showProvider";
	}
}
