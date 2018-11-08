
public class CostMatrixElement { 
	private double cost;
	private boolean accessible;

	public CostMatrixElement(double cost, boolean accessible) {
		this.cost = cost;
		this.accessible = accessible;
	}

	// GETERS AND SETTERS
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	@Override
	public String toString() {
		return "CostMatrixElement [cost=" + cost + ", accessible=" + accessible + "]";
	}

}
