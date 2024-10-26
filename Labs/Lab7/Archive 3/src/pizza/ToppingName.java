package pizza;

/**
 * This enumerated type represents the name of a topping.
 */
public enum ToppingName {
  Cheese("Cheese",1.00),
  Sauce("Red Sauce",1.00),
  Alfredo("White Sauce",1.25),
  GreenPepper("Green Pepper",0.5),
  Tomato("Tomato",0.5),
  Onion("Onion",0.5),
  Jalapeno("Jalapeno Pepper",0.5),
  BlackOlive("Black Olive",0.5),
  Ham("Ham",1.00),
  Pepperoni("Pepperoni",0.75),
  Steak("Steak",0.75),
  Chicken("Chicken",1.00);

  private final String descriptor;
  private final double cost;

  public String toString() {
    return descriptor;
  }

  public double getCost() {
    return this.cost;
  }

  ToppingName(String d, double price) {
    this.descriptor = d;
    this.cost = price;
  }


}
