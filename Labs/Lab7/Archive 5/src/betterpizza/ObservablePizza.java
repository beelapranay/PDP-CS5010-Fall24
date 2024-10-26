package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

public interface ObservablePizza {
  /**
   * Get the cost of this pizza
   *
   * @return the cost of this pizza in MM.CC format
   */
  double cost();

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion
   *
   * @param name the name of the topping
   * @return the portion of this topping on this pizza, or null if the given topping is not
   * on this pizza
   */
  ToppingPortion hasTopping(ToppingName name);
}
