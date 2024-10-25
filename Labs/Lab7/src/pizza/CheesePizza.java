package pizza;

/**
 * This class represents a cheese pizza.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Create a cheese pizza of the specified size with the specified crust.
   * @param size the size of this pizza
   * @param crust the crust of this pizza
   */
  public CheesePizza(Size size, Crust crust) {
    super(size, crust);
    this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
    this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
  }
}
