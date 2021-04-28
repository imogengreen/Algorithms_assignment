public class DirectedEdge {
	
		double cost;
		int from;
		int to;
		
		public DirectedEdge() {
			
		}
		public DirectedEdge(int from ,int to,double cost){
			this.cost=cost;
			this.from=from;
			this.to=to;
		}
		
		
		double getCost() {
			return this.cost;
		}
		
		int getFrom() {
			return this.from;
		}
		
		int getTo() {
			return this.to;
		}

}
