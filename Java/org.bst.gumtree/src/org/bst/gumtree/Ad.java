package org.bst.gumtree;

import java.util.ArrayList;

public class Ad {
	String Title;
	String Location;
	String Price;
	String Description;
	String Category;
	String Phone;
	public ArrayList<String> Images;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getLocation() {
		return Location;
	}

	@Override
	public String toString() {
		return "Ad [Title=" + Title + ", Location=" + Location + ", Price=" + Price + ", Description=" + Description
				+ ", Phone=" + Phone + ", Category=" + Category + "]";
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public Ad(String category, String Location, String Title, String Description, String Price, String Phone,ArrayList<String> Img) {
		this.Category = category;
		this.Location = Location;
		this.Title = Title;
		this.Description = Description;
		this.Price = Price;
		this.Phone = Phone;
		Images = new ArrayList<String>();
		this.Images = Img;
//		System.out.println("The function size is  "+Img.size());
//		System.out.println("This one is  "+this.Images.size());
	}
}
