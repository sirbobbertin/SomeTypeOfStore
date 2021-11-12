package pojo;

public class ItemsPoJo {
	


	private String name;
	private float price;
	private int id;
	private boolean removed;
	private boolean bought;
	
	public ItemsPoJo(String name, float price, int id, boolean removed, boolean bought) {
		super();
		this.name = name;
		this.price = price;
		this.id = id;
		this.removed = removed;
		this.bought = bought;
	}

	public ItemsPoJo()
	{
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


}
