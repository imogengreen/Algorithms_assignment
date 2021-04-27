
public class Edge {

	double cost;
	int from;
	int to;
	
	Edge(double cost,int from , int to){
		this.cost=cost;
		this.from=from;
		this.to=to;
	}
	Edge(){
		
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
