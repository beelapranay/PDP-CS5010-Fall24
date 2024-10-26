package betterpizza;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import java.util.HashMap;
import java.util.Map;

public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Size size;
  protected Crust crust;
  protected final Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

  public T size(Size size) {
    this.size = size;
    return self();
  }

  public T crust(Crust crust) {
    this.crust = crust;
    return self();
  }

  public T addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return self();
  }

  protected abstract T self();

  public abstract ObservablePizza build();
}
