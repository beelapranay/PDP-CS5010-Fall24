package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Interface representing observable operations on a pizza,
 * such as retrieving cost and checking for specific toppings.
 */
public interface ObservablePizza {

  /**
   * Gets the total cost of this pizza.
   *
   * @return the cost of this pizza in MM.CC format
   */
  double cost();

  /**
   * Checks if the specified topping is present on this pizza
   * and, if so, returns its portion size.
   *
   * @param name the name of the topping to check
   * @return the portion of the specified topping on this pizza,
   *         or null if the topping is not present
   */
  ToppingPortion hasTopping(ToppingName name);
}