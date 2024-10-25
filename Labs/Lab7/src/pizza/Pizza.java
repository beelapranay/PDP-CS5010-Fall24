package pizza;

//import betterpizza.ObservablePizza;

/**
 * This class represents the operations offered by a single pizza.
 */
public interface Pizza {

  /**
   * Set the type of crust for this pizza.
   * @param crust the crust for this pizza
   */
  void setCrust(Crust crust);

  /**
   * Set the size of this pizza.
   * @param size the size of this pizza
   */
  void setSize(Size size);

  /**
   * Add the specified topping in the specified portion to this pizza. If this
   * topping already exists on the pizza, it will overwrite its portion with
   * the specified portion size.
   * @param name the name of the topping
   * @param portion the portion size of this topping
   */
  void addTopping(ToppingName name,ToppingPortion portion);

  /**
   * Remove the specified topping from this pizza. This method has no effect
   * if the topping is not present on this pizza.
   * @param name the name of the topping to be removed
   */
  void removeTopping(ToppingName name);

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
