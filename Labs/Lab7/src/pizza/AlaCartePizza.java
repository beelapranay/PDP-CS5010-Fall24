package pizza;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements Pizza {
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

  @Override
  public void setCrust(Crust crust) {
    this.crust = crust;
  }

  @Override
  public void setSize(Size size) {
    this.size = size;
  }

  @Override
  public void addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
  }

  @Override
  public void removeTopping(ToppingName name) {
    this.toppings.remove(name);
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



}
