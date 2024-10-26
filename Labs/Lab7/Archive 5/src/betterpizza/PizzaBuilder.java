package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Size size;
  protected Crust crust;
  protected final Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

  public T size(Size size) {
    this.size = size;
    return returnBuilder();
  }

  public T crust(Crust crust) {
    this.crust = crust;
    return returnBuilder();
  }

  public T addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return returnBuilder();
  }

  protected abstract T returnBuilder();

  public abstract ObservablePizza build();
}

