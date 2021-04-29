/**
 * This class contains the information about an edge object and function to getCost , 
 * and stop ids from and to of an edge 
 * The class has been based of the book by Robert Sedgewick and Kevin Wayne
 * 
 * @authors Khushboo Jain
 *          
 * @version HT 2021
 */

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
