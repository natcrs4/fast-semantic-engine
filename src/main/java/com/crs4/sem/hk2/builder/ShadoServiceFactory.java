package com.crs4.sem.hk2.builder;

import java.io.File;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;
import org.hibernate.cfg.Configuration;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.service.HibernateConfigurationFactory;
import com.crs4.sem.service.ShadoService;

public class ShadoServiceFactory implements Factory<ShadoService>{

	@Inject
	private SemEngineConfig config;
	
	@Override
	public ShadoService provide() {
		String path=config.getHibernateCFGShado();
	    if (path.startsWith("classpath:")) {
	    	path=path.replace("classpath:", config.classpath()+"/applications/"+config.applicationame()+"/WEB-INF/classes/");
	    }
	   File cfgFile=  new File(path);
	    Configuration configure = HibernateConfigurationFactory.configureShadoService(cfgFile);
		ShadoService shadoService=  ShadoService.newInstance(configure);

	    
	    return shadoService;
	}

	@Override
	public void dispose(ShadoService instance) {
		// TODO Auto-generated method stub
		
	}

}
