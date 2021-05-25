package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private SimpleWeightedGraph<Airport,DefaultWeightedEdge>grafo;
	private ExtFlightDelaysDAO dao;
	private Map<Integer, Airport>idMap; //identity map
	
	
	
	public Model() {
		dao=new ExtFlightDelaysDAO();
		idMap=new HashMap<Integer,Airport>();
		dao.loadAllAirports(idMap); 
		
		
		
	}
	
	public void creaGrafo(int distanza) {
		
		grafo=new SimpleWeightedGraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, idMap.values());
		
		
		
		
		
		for(Rotta r: dao.getRotte(idMap, distanza)) {
			
			if(this.grafo.containsVertex(r.getA1()) && this.grafo.containsVertex(r.getA2())) {
				
				DefaultWeightedEdge e= this.grafo.getEdge(r.getA1(), r.getA2());
				
				//se l'edge non è ancora stato creato 
				//lo aggiungo
				if(e==null) {
					
					Graphs.addEdge(this.grafo, r.getA1(),r.getA2(), r.getPeso());
				}
				//se invece cè gia stato creato, con i vertici opposti, aggiungo solo la distanza e poi divido per due(per fare la media)
				else {
					double vecchioPeso=this.grafo.getEdgeWeight(e);
					double nuovoPeso= (vecchioPeso+r.getPeso())/2;
					//aggiorno l'arco con il nuovo peso
					this.grafo.setEdgeWeight(e,nuovoPeso);
					
				}
				
			}
		}
			
			
			
		}
		
	
	
	
	public int numVertici() {
		
		return this.grafo.vertexSet().size();
		
		
	}
	
	
	public int numArchi() {
		
		return this.grafo.edgeSet().size();
		
		
	}
	
	
	public List<Rotta> getRotte(){
		
		List<Rotta>rotte=new LinkedList<Rotta>();
		
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			
			rotte.add(new Rotta(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), (int)(this.grafo.getEdgeWeight(e))));
		}
		
		
		
		return rotte;
		
		
	}

}
