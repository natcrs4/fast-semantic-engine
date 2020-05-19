package com.crs4.sem.hk2.builder;

import java.io.File;

import org.aeonbits.owner.ConfigFactory;
import org.glassfish.hk2.api.Factory;
import org.hibernate.cfg.Configuration;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.service.AuthorService;
import com.crs4.sem.service.HibernateConfigurationFactory;

public class AuthorServiceFactory implements Factory<AuthorService>{

	@Override
	public AuthorService provide() {
		SemEngineConfig config=ConfigFactory.create(SemEngineConfig.class,System.getProperties(),   System.getenv());
		 String path=config.getHibernateCFGAuthors();
		    if (path.startsWith("classpath:")) {
		    	path=path.replace("classpath:", config.classpath()+"/applications/"+config.applicationame()+"/WEB-INF/classes/");
		    }
		   File cfgFile=  new File(path);
		   
		    Configuration configuration=HibernateConfigurationFactory .configureAuthorService(cfgFile);
			AuthorService authorService= AuthorService.newInstance(configuration);		
		    return authorService;
	}

	@Override
	public void dispose(AuthorService instance) {
		// TODO Auto-generated method stub
		
	}

}
