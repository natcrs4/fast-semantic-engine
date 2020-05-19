package com.crs4.sem.hk2.builder;

import javax.inject.Singleton;

import org.apache.lucene.analysis.Analyzer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.crs4.sem.config.SemEngineConfig;
import com.crs4.sem.model.StatusSingleton;
import com.crs4.sem.neo4j.service.TaxonomyService;
import com.crs4.sem.service.AuthorService;
import com.crs4.sem.service.LuceneService;
import com.crs4.sem.service.NERService;
import com.crs4.sem.service.NewDocumentService;
import com.crs4.sem.service.ShadoService;
import com.mfl.sem.classifier.text.TextClassifier;


public class ServiceBinder extends AbstractBinder{



	@Override
	protected void configure() {

		this.bindFactory(DocumentServiceFactory.class).to(NewDocumentService.class).in(Singleton.class);;
		this.bindFactory(AnalyzerFactory.class).to(Analyzer.class).in(Singleton.class);;
		this.bindFactory(AuthorServiceFactory.class).to(AuthorService.class).in(Singleton.class);;
		this.bindFactory(SemEngineConfigFactory.class).to(SemEngineConfig.class).in(Singleton.class);;
		this.bindFactory(TaxonomyServiceFactory.class).to(TaxonomyService.class).in(Singleton.class);;
		this.bindFactory(TextClassifierFactory.class).to(TextClassifier.class).in(Singleton.class);;
		this.bindFactory(NERServiceFactory.class).to(NERService.class).in(Singleton.class);;
		this.bindFactory(ShadoServiceFactory.class).to(ShadoService.class).in(Singleton.class);;
		this.bindFactory(LuceneServiceFactory.class).to(LuceneService.class).in(Singleton.class);;
		this.bind(StatusSingleton.class).to(StatusSingleton.class).in(Singleton.class);
	}

}
