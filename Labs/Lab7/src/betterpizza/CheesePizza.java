package betterpizza;

import java.util.Map;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Represents a cheese pizza with specific crust, size, and toppings.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Constructs a CheesePizza with the specified size, crust, and toppings.
   *
   * @param size     the size of the pizza
   * @param crust    the crust type of the pizza
   * @param toppings a map of toppings and their portions
   */
  public CheesePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  /**
   * Builder class for creating a CheesePizza with default cheese and sauce toppings.
   */
  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizza.CheesePizzaBuilder> {

    /**
     * Returns this builder instance for method chaining.
     *
     * @return this builder instance
     */
    @Override
    protected CheesePizza.CheesePizzaBuilder returnBuilder() {
      return this;
    }

    /**
     * Initializes the builder with default full portions of cheese and sauce toppings.
     */
    public CheesePizzaBuilder() {
      toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      toppings.put(ToppingName.Sauce, ToppingPortion.Full);
    }

    /**
     * Sets the cheese topping to cover the left half of the pizza.
     *
     * @return this builder instance
     */
    public CheesePizzaBuilder leftHalfCheese() {
      toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return this;
    }

    /**
     * Sets the cheese topping to cover the right half of the pizza.
     *
     * @return this builder instance
     */
    public CheesePizzaBuilder rightHalfCheese() {
      toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return this;
    }

    /**
     * Removes the cheese topping from the pizza.
     *
     * @return this builder instance
     */
    public CheesePizzaBuilder noCheese() {
      toppings.remove(ToppingName.Cheese);
      return this;
    }

    /**
     * Builds and returns a CheesePizza instance with the specified attributes.
     * Ensures that the pizza size is set before building.
     *
     * @return a new CheesePizza instance
     * @throws IllegalStateException if the pizza size is not specified
     */
    @Override
    public CheesePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new CheesePizza(size, crust, toppings);
    }
  }
}