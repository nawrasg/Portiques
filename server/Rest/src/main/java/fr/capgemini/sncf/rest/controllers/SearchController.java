package fr.capgemini.sncf.rest.controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sncf/search")
public class SearchController {
	
	/**
	 * Make a search request through ElasticSearch.
	 * 
	 * @param userName to look for in the database.
	 * @return search results.
	 */
	@RequestMapping(value="/{userName}", method = RequestMethod.GET)
	private @ResponseBody Object searchUsers(@PathVariable String userName){
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("node.name", "Nawras Le Grand")
				.put("discovery.zen.ping.multicast.enabled", false) //for local tests
				.put("discovery.zen.ping.unicast.hosts", "localhost") //for local tests
				.build(); 
		final Node node = new NodeBuilder().settings(settings)
				.data(false).client(true).build().start();
		final Client nClient = node.client();
		
		SearchResponse nSearch = nClient.prepareSearch("sncf").setTypes("user").setQuery(QueryBuilders.termQuery("name", userName)).execute().actionGet();
		SearchHit[] nResults = nSearch.getHits().getHits();
		Set<Map<String, Object>> nOutput = new HashSet<Map<String, Object>>();
		for(SearchHit nResult : nResults){
			Map<String,Object> result = nResult.getSource();
			nOutput.add(result);
		}
		return nOutput;
	}

}
