package com.adem.springdata.controllers;

import com.adem.springdata.entities.Article;
import com.adem.springdata.entities.Provider;
import com.adem.springdata.repositories.ArticleRepository;
import com.adem.springdata.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/article")
public class ArticleController
{
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	public static String trouve = null;
	private final ArticleRepository articleRepository;
	private final ProviderRepository providerRepository;

	public ArticleController()
	{
		this.articleRepository = null;
		this.providerRepository = null;
	}

	@Autowired
	public ArticleController(ArticleRepository articleRepository, ProviderRepository providerRepository)
	{
		this.articleRepository = articleRepository;
		this.providerRepository = providerRepository;
	}

	@GetMapping("list")
	public String listProviders(Model model)
	{
		List<Article> la = (List <Article>) articleRepository.findAll();
		
		if(la.size() == 0)
			la = null;

		model.addAttribute("articles", la);
		model.addAttribute("trouve", trouve);

		return "article/listArticles";
	}
	
	@GetMapping("add")
	public String showAddArticleForm(Model model)
	{
		model.addAttribute("providers", providerRepository.findAll());
		model.addAttribute("article", new Article());

		return "article/addArticle";
	}
	
	@PostMapping("add")
	//@ResponseBody
	public String addArticle(@Valid Article article, BindingResult result, @RequestParam(name = "providerId", required = true) Long p, @RequestParam("files") MultipartFile[] files)
	{
		Provider provider = providerRepository.findById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + p));

		article.setProvider(provider);

		// Upload file
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

		try
		{
			Files.write(fileNameAndPath, file.getBytes());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Stock the file name in the Database
		StringBuilder fileName = new StringBuilder();
		fileName.append(file.getOriginalFilename());
		article.setPicture(fileName.toString());

		System.out.println(article);

		articleRepository.save(article);

		//return article.getLabel() + " " +article.getPrice() + " " + p.toString();

		return "redirect:list";
	}
	
	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id, Model model)
	{
		/*Article artice = articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));*/

		Optional<Article> article = articleRepository.findById(id);
		
		if(article.isPresent())
		{
			articleRepository.delete(article.get());
			trouve="existe";
		}
		else
			trouve="inexiste";

	//model.addAttribute("trouve", trouve); 
	  
	//List<Article> la = (List<Article>)articleRepository.findAll();
	//if(la.size()==0)
	//	la = null;
	//model.addAttribute("articles", la);

	//return "article/listArticles";

		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showArticleFormToUpdate(@PathVariable("id") long id, Model model)
	{
		Article article = articleRepository.findById(id)
				.orElseThrow(() ->new IllegalArgumentException("Invalid provider Id:" + id));

		model.addAttribute("article", article);
		model.addAttribute("providers", providerRepository.findAll());
		model.addAttribute("idProvider", article.getProvider().getId());

		return "article/updateArticle";
	}

	@PostMapping("edit")
	public String updateArticle(@Valid Article article, BindingResult result, Model model, @RequestParam(name = "providerId", required = false) Long p)
	{
		if (result.hasErrors())
			return "article/updateArticle";

		Provider provider = providerRepository.findById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + p));

		article.setProvider(provider);
		articleRepository.save(article);

		return "redirect:list";
	}

	@GetMapping("show/{id}")
	public String showArticleDetails(@PathVariable("id") long id, Model model)
	{
		Article article = articleRepository.findById(id)
				.orElseThrow(() ->new IllegalArgumentException("Invalid provider Id:" + id));

		model.addAttribute("article", article);

		return "article/showArticle";
	}
}
