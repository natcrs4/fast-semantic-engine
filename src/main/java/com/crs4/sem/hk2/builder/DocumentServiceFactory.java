package com.crs4.sem.hk2.builder;

import java.io.File;

import org.aeonbits.owner.ConfigFactory;
import org.glassfish.hk2.api.Factory;
import org.hibernate.cfg.Configuration;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.service.HibernateConfigurationFactory;
import com.crs4.sem.service.NewDocumentService;

public class DocumentServiceFactory implements Factory<NewDocumentService>{

	@Override
	public NewDocumentService provide() {
		SemEngineConfig config=ConfigFactory.create(SemEngineConfig.class,System.getProperties(),   System.getenv());
		  String path=config.getHibernateCFGDocuments();
		    if (path.startsWith("classpath:")) {
		    	path=path.replace("classpath:", config.classpath()+"/applications/"+config.applicationame()+"/WEB-INF/classes/");
		    }
		   File cfgFile=  new File(path);
		    Configuration configure = HibernateConfigurationFactory.configureDocumentService(cfgFile);
		   
			NewDocumentService docservice= NewDocumentService.newInstance(configure);
		    return docservice;
	}

	@Override
	public void dispose(NewDocumentService instance) {
		instance.close();
		
	}

}
