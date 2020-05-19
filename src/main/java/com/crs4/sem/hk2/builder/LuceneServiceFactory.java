package com.crs4.sem.hk2.builder;

import java.io.IOException;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.service.LuceneService;

public class LuceneServiceFactory implements Factory<LuceneService>{

	@Inject
	private SemEngineConfig config;
	
	@Override
	public LuceneService provide() {
		  String source=config.indexbase()+"/com.crs4.sem.model.NewDocument";
			LuceneService luceneService=null;
			try {
				luceneService =  LuceneService.newInstance(source);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return luceneService;
	}

	@Override
	public void dispose(LuceneService instance) {
		// TODO Auto-generated method stub
		
	}

}
