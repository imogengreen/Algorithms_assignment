import java.util.HashSet;

public class EdgeWeightedDigraph {


		public int V; // number of vertices
		public HashSet<DirectedEdge>[] adj;
		public int[] indegree;

		EdgeWeightedDigraph(int V) {

			this.V = V;
			this.indegree = new int[V];
			adj = (HashSet<DirectedEdge>[]) new HashSet[V];
			 
			for(int v = 0; v < V; v++) {
				adj[v] = new HashSet<DirectedEdge>();
			}
		}

		public void addEdge(int from, int to, double cost) {
			
			DirectedEdge e= new DirectedEdge(from, to ,cost);
			adj[from].add(e);
			indegree[to]++;
		}
		
		public Iterable<DirectedEdge> adj(int v){
			return adj[v];
		}

		public int V() {
			return V;
		}

}
