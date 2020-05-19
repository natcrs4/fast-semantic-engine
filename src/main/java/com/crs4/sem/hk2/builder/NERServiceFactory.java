package com.crs4.sem.hk2.builder;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.service.NERService;

public class NERServiceFactory implements Factory<NERService>{
	@Inject
	private SemEngineConfig config;

	@Override
	public NERService provide() {
		File file = new File(config.icabparameters());
		try {
			return new NERService(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void dispose(NERService instance) {
		// TODO Auto-generated method stub
		
	}

}
