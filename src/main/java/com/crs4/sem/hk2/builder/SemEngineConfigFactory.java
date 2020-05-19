package com.crs4.sem.hk2.builder;

import org.aeonbits.owner.ConfigFactory;
import org.glassfish.hk2.api.Factory;

import com.crs4.sem.config.SemEngineConfig;

public class SemEngineConfigFactory implements Factory<SemEngineConfig>{

	@Override
	public SemEngineConfig provide() {
		 return ConfigFactory.create(SemEngineConfig.class,System.getProperties(),   System.getenv());
	}

	@Override
	public void dispose(SemEngineConfig instance) {
		// TODO Auto-generated method stub
		
	}

}
