/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sigeiin.main.web.controllers.modalidadController;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Danie
 */
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer /**WebMvcConfigurerAdapter en versiones <1.5*/{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		registry.addResourceHandler("/uploads/**").
		addResourceLocations(resourcePath);
		log.info("resource: " + resourcePath);
	}
	
	
    
}
