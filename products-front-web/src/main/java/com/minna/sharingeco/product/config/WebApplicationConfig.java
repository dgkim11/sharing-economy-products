package com.minna.sharingeco.product.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * @since 1.0.0
 * @author Dennis Kim
 */
@Configuration
@EnableAutoConfiguration
@PropertySource({
		"classpath:properties/front-web-default.xml",
		"classpath:properties/front-web-${spring.profiles.active}.xml"
})
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	/**
	 * It enables you to access property value with @Value annotation.
	 *
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig()	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * The locale of this application can be changed by changing cookie value.
	 *
	 * @return
	 */
	@Bean
	public CookieLocaleResolver localeResolver()	{
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("locale");
		resolver.setDefaultLocale(Locale.KOREAN);
		return resolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * we need to add an interceptor bean that will switch to a new locale
	 * based on the value of the lang parameter appended to a request
	 * @return
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(localeChangeInterceptor());
	}

	// TODO - Remove example directory before release
	@Bean
	public MessageSource messageSource()	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:i18n/messages", "classpath:i18n/example/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
