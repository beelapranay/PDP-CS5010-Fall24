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
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  // Static inner builder class for AlaCartePizza
  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {
    @Override
    protected AlaCartePizzaBuilder self() {
      return this;
    }

    @Override
    public AlaCartePizza build() {
      if (size == null) {
        throw new IllegalStateException("Size must be specified to build a pizza.");
      }
      return new AlaCartePizza(size, crust, toppings);
    }
  }



}
