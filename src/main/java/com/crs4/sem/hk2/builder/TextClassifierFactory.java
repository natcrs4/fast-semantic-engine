package com.crs4.sem.hk2.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.lucene.analysis.Analyzer;
import org.glassfish.hk2.api.Factory;
import org.neo4j.graphdb.Node;

import com.crs4.sem.model.Document;
import com.crs4.sem.model.Documentable;
import com.crs4.sem.neo4j.exceptions.CategoryNotFoundInTaxonomyException;
import com.crs4.sem.neo4j.service.TaxonomyService;
import com.crs4.sem.service.NewDocumentService;
import com.crs4.sem.utils.DocumentsUtil;
import com.mfl.sem.classifier.HClassifier;
import com.mfl.sem.classifier.exception.ClassifierException;
import com.mfl.sem.classifier.impl.EnsembleClassifier;
import com.mfl.sem.classifier.impl.SVMClassifier;
import com.mfl.sem.classifier.model.CategoryDictionary;
import com.mfl.sem.classifier.policy.MajoritySelection;
import com.mfl.sem.classifier.text.Documents;
import com.mfl.sem.classifier.text.TextClassifier;
import com.mfl.sem.classifier.text.impl.EnsembleTextClassifier;
import com.mfl.sem.dataset.reader.DocumentReader;

import lombok.Data;

@Data
public class TextClassifierFactory implements Factory<TextClassifier>{
	@Inject
	private TaxonomyService taxoservice;

	@Inject
    private NewDocumentService docservice;

	@Inject
	private Analyzer analyzer;
	

	@Override
	public TextClassifier provide() {
		CategoryDictionary categoryDictionary = new CategoryDictionary();
		TextClassifier textClassifier=null;
		Node root = taxoservice.searchCategory("root");
		HClassifier<SVMClassifier> hclassifier;
		int ensize=10;
		try {
			//hclassifier = HClassifierBuilder.builder().species(SVMClassifier.class).root(root)
					//.taxonomyService(taxoservice).categoryBuilder(categoryDictionary).build();
			//SVMClassifier svm = new SVMClassifier();
		//TextClassifier textClassifier = new TextClassifierImpl(analyzer,svm,categoryDictionary);
		 SVMClassifier svm [] = new SVMClassifier[ensize];
		  for(int i=0;i<ensize;i++)
			  svm[i]= new SVMClassifier();
		  EnsembleClassifier ensemble = EnsembleClassifier.builder().size(ensize).group(svm).selectionPolicy(new MajoritySelection()).build();
		  
		  
		 
		  
		List<Documentable> docs= new ArrayList<Documentable>();
		List<Documentable> docs_ = docservice.getTrainable();
		//List<Documentable> docs_=DocumentsUtil.getAllTrainableDocument(taxoservice, docservice, "root");
		String[] categories = taxoservice.branchLabels(root, false);
		List<Documentable> kdocs = new ArrayList<Documentable>();
		
		
		for (String category : categories) {
			String[] keywords;
			keywords = taxoservice.getKetwords("root", category);
			//if(category.trim().length()==0) System.out.println("_____");
			String[] categories_ = new String[1];
			categories_[0] = category;
			if(keywords!=null) 
			for (int i = 0; i < keywords.length; i++) {
				Documentable doc = Document.builder().title(keywords[i]).categories(categories_).build();
                kdocs.add(doc);
			}
		}
		docs.addAll(kdocs);
	
		if(!docs_.isEmpty())docs.addAll(docs_);
		docs=DocumentsUtil.expandUniLabel(docs);
		System.out.println("docs size"+docs.size());
		Documents kdocsreader = new DocumentReader(docs);
		textClassifier= new EnsembleTextClassifier(ensize,ensemble,analyzer);
		textClassifier.train(kdocsreader);
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoryNotFoundInTaxonomyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return textClassifier;
		
	}
	

	@Override
	public void dispose(TextClassifier instance) {
		// TODO Auto-generated method stub
		
	}

}
