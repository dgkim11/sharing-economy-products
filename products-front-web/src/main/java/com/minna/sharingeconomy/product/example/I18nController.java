package com.minna.sharingeconomy.product.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

/**
 * @author Dennis Kim
 */
@Controller
public class I18nController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/international")
	public String getInternationalPage(ModelMap model, Locale locale)	{
		String greeting = messageSource.getMessage("greeting", null, locale);
		model.addAttribute("greeting", greeting);
		model.addAttribute("curLocale", locale);
		return "example/international";
	}
}
