package pojo;

public class OfferPoJo {

	int offerID;
	int itemID;
	String itemName;
	float itemPrice;
	String name;
	int id;
	String offerStatus;
	
	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public OfferPoJo(int offerID, int itemID, String itemName, float itemPrice, String name, int id,
			String offerStatus) {
		super();
		this.offerID = offerID;
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.name = name;
		this.id = id;
		this.offerStatus = offerStatus;
	}

	public OfferPoJo() {
		super();
		
	}

}
