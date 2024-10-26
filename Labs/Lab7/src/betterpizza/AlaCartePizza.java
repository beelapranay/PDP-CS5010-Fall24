package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  public AlaCartePizza(Size size, Crust crust) {
    this.crust = crust;
    this.size = size;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    if (size == null || crust == null || toppings == null) {
      throw new IllegalArgumentException("Size, crust, and toppings cannot be null.");
    }
    this.size = size;
    this.crust = crust;
    this.toppings = new HashMap<>(toppings);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  /**
   * Builder class for creating an AlaCartePizza with customizable options.
   * Extends the generic PizzaBuilder to allow chaining of configuration methods.
   */
  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder>  {

    /**
     * Returns the builder instance to enable method chaining.
     * @return this builder instance
     */
    @Override
    protected AlaCartePizzaBuilder returnBuilder() {
      return this;
    }

    /**
     * Builds and returns an AlaCartePizza with the specified configuration.
     * Ensures that the pizza has a specified size before creating it.
     * @return a new instance of AlaCartePizza with the configured attributes
     * @throws IllegalStateException if the pizza size is not specified
     */
    @Override
    public AlaCartePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new AlaCartePizza(size, crust, toppings);
    }
  }

}
