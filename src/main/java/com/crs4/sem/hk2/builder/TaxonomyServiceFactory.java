package com.crs4.sem.hk2.builder;

import java.io.File;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.neo4j.service.TaxonomyService;

import lombok.Data;
@Data
public class TaxonomyServiceFactory implements Factory<TaxonomyService>{
	
	@Inject
	private SemEngineConfig config;

	@Override
	public TaxonomyService provide() {
		GraphDatabaseService service = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File(config.neo4jDirectory()))
		.setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
		.setConfig(GraphDatabaseSettings.string_block_size, "60")
		.setConfig(GraphDatabaseSettings.array_block_size, "300")
		.setConfig(GraphDatabaseSettings.read_only,config.neo4jreadonly()).newGraphDatabase();
		TaxonomyService taxoservice = new TaxonomyService(service);
		return taxoservice;
	}

	@Override
	public void dispose(TaxonomyService instance) {
	instance.getGraphDb().shutdown();
		
	}

}
