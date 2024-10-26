package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class CheesePizza extends AlaCartePizza {
  public CheesePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizza.CheesePizzaBuilder> {
    @Override
    protected CheesePizza.CheesePizzaBuilder returnBuilder() {
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

    public CheesePizzaBuilder noCheese() {
      toppings.remove(ToppingName.Cheese);
      return this;
    }

    @Override
    public CheesePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new CheesePizza(size, crust, toppings);
    }
  }
}
