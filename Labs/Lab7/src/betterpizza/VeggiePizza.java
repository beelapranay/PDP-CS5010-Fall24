package betterpizza;

import java.util.Map;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Represents a veggie pizza with specific crust, size, and default veggie toppings.
 */
public class VeggiePizza extends AlaCartePizza {

  /**
   * Constructs a VeggiePizza with the specified size, crust, and toppings.
   *
   * @param size     the size of the pizza
   * @param crust    the crust type of the pizza
   * @param toppings a map of toppings and their portions
   */
  public VeggiePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  /**
   * Builder class for creating a VeggiePizza with default veggie toppings.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizza.VeggiePizzaBuilder> {

    /**
     * Initializes the builder with default veggie toppings.
     */
    public VeggiePizzaBuilder() {
      toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      toppings.put(ToppingName.BlackOlive, ToppingPortion.Full);
      toppings.put(ToppingName.GreenPepper, ToppingPortion.Full);
      toppings.put(ToppingName.Onion, ToppingPortion.Full);
      toppings.put(ToppingName.Jalapeno, ToppingPortion.Full);
      toppings.put(ToppingName.Tomato, ToppingPortion.Full);
    }

    /**
     * Returns the builder instance for method chaining.
     *
     * @return this builder instance
     */
    @Override
    protected VeggiePizza.VeggiePizzaBuilder returnBuilder() {
      return this;
    }

    /**
     * Removes cheese from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noCheese() {
      toppings.remove(ToppingName.Cheese);
      return this;
    }

    /**
     * Removes sauce from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noSauce() {
      toppings.remove(ToppingName.Sauce);
      return this;
    }

    /**
     * Removes Alfredo (white sauce) from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noWhiteSauce() {
      toppings.remove(ToppingName.Alfredo);
      return this;
    }

    /**
     * Removes green pepper from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noGreenPepper() {
      toppings.remove(ToppingName.GreenPepper);
      return this;
    }

    /**
     * Removes tomato from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noTomato() {
      toppings.remove(ToppingName.Tomato);
      return this;
    }

    /**
     * Removes onion from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noOnion() {
      toppings.remove(ToppingName.Onion);
      return this;
    }

    /**
     * Removes jalapeno from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noJalapeno() {
      toppings.remove(ToppingName.Jalapeno);
      return this;
    }

    /**
     * Removes black olives from the pizza toppings.
     *
     * @return this builder instance
     */
    public VeggiePizzaBuilder noBlackOlive() {
      toppings.remove(ToppingName.BlackOlive);
      return this;
    }

    /**
     * Builds and returns a VeggiePizza with the specified configuration.
     * Ensures that the pizza size is set before building.
     *
     * @return a new instance of VeggiePizza
     * @throws IllegalStateException if the pizza size is not specified
     */
    @Override
    public VeggiePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new VeggiePizza(size, crust, toppings);
    }
  }
}