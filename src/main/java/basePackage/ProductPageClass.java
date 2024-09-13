package basePackage;

public class ProductPageClass {
	
	private String name;
    private int price;

    public ProductPageClass(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
