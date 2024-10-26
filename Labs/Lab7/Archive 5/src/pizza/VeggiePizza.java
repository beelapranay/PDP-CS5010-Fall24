package pizza;

/**
 * This class represents a vegetarian pizza.
 */
public class VeggiePizza extends AlaCartePizza {

  /**
   * Create a veggie pizza with all vegetarian toppings, of the specified
   * size with the specified crust.
   * @param size the size of this pizza
   * @param crust the crust of this pizza
   */
  public VeggiePizza(Size size,Crust crust) {
    super(size,crust);
    this.addTopping(ToppingName.Cheese,ToppingPortion.Full);
    this.addTopping(ToppingName.Sauce,ToppingPortion.Full);
    this.addTopping(ToppingName.BlackOlive,ToppingPortion.Full);
    this.addTopping(ToppingName.GreenPepper,ToppingPortion.Full);
    this.addTopping(ToppingName.Onion,ToppingPortion.Full);
    this.addTopping(ToppingName.Jalapeno,ToppingPortion.Full);
    this.addTopping(ToppingName.Tomato,ToppingPortion.Full);
  }
}
