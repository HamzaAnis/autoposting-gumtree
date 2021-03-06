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

	public Ad(String category, String Location, String Title, String Description, String Price, String Phone,
			String Image1, String Image2, String Image3, String Image4, String Image5, String Image6, String Image7,
			String Image8, String Image9) {
		this.Category = category;
		this.Location = Location;
		this.Title = Title;
		this.Description = Description;
		this.Price = Price;
		this.Phone = Phone;
		Images = new ArrayList<String>();

		Images.add(Image1);
		Images.add(Image2);
		Images.add(Image3);
		Images.add(Image4);
		Images.add(Image5);
		Images.add(Image6);
		Images.add(Image7);
		Images.add(Image8);
		Images.add(Image9);

		// System.out.println("The function size is "+Img.size());
		// System.out.println("This one is "+this.Images.size());
	}
}
