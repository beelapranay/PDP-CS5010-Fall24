package betterpizza;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import java.util.HashMap;
import java.util.Map;

public class CheesePizza implements ObservablePizza {
  private final Crust crust;
  private final Size size;
  private final Map<ToppingName, ToppingPortion> toppings;

  protected CheesePizza(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings) {
    if (crust == null || size == null) {
      throw new IllegalArgumentException("Crust and size cannot be null.");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
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

  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {
    private Crust crust;
    private Size size;
    private final Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

    public CheesePizzaBuilder() {
//      this.crust = Crust.Classic; // Default crust
//      this.size = Size.Small; // Default size
      // Cheese is included by default
      toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      toppings.put(ToppingName.Sauce, ToppingPortion.Full);
    }

    public CheesePizzaBuilder crust(Crust crust) {
      this.crust = crust;
      return this;
    }

    public CheesePizzaBuilder size(Size size) {
      this.size = size;
      return this;
    }

    public CheesePizzaBuilder addTopping(ToppingName topping, ToppingPortion portion) {
      toppings.put(topping, portion);
      return this;
    }

    public CheesePizzaBuilder leftHalfCheese() {
      toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return this;
    }

    public CheesePizzaBuilder rightHalfCheese() {
      toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return this;
    }

    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new CheesePizza(crust, size, toppings);
    }
    @Override
    protected CheesePizzaBuilder self() {
      return this;
    }
  }
}
