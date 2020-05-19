package com.crs4.sem.rest;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.lucene.analysis.Analyzer;

import com.crs4.sem.model.Author;
import com.crs4.sem.service.AuthorService;
import com.crs4.sem.service.NewDocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/authors")
@Api(value = "Authors", description = "Groups a list of API Authors")
public class AuthorRestResources {

	@Inject
    private AuthorService authorService;

	public static Logger log=Logger.getLogger(AuthorRestResources.class.getName());
	
	@Inject
	private Analyzer  analyzer;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get dictionary of authors", notes = "get the total set of authors")
	public List<String> getAuthors(@QueryParam("text") String text) throws IOException {
		List<String> authors = this.authorService.getAuthors(text);
		log.info("get set of authors " + text);
		
		return authors;
	}
	
	@Inject

	private NewDocumentService documentService;
    
    @GET
	@Path("/rebuild")
    @Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Rebuild index of authors", notes = "Rebuild the whole index of authors. "
			+ "Authors are extracted from documents database by analyzing 'authors' field. "
			+ "A statistic distribution of authors are computed. Rebuild index structure can take many time.")
	public  Response rebuild() {

		log.info("rebuildind index structure, this operation can take many time ");
		
		this.documentService.buildAuthors(authorService);
		return Response.ok().build();
		}
    
    
    @GET
   	@Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
   	@ApiOperation(value = "Search author", notes = "Authors can be searched using a query. Relevance is proportional to number of  documents  related to an author.")
 
	public List<Author> search(@QueryParam("text") @DefaultValue("") String text,@QueryParam("start") @DefaultValue("0") Integer start,@QueryParam("maxresults") @DefaultValue("100") Integer maxresults) throws Exception  {

		log.info("search author" + text+ " with query"+ text);
	
		return this.authorService.search(text, analyzer,start, maxresults);
		
		
	
	}

}
