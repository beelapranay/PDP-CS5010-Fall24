package betterpizza;

import java.util.HashMap;
import java.util.Map;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Abstract builder class for constructing pizza objects with customizable properties.
 * Uses a generic type parameter to allow chaining of configuration methods in subclasses.
 *
 * @param <T> the type of the concrete builder subclass
 */
public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Size size;
  protected Crust crust;
  protected final Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

  /**
   * Sets the size of the pizza.
   *
   * @param size the desired pizza size
   * @return the builder instance for method chaining
   */
  public T size(Size size) {
    this.size = size;
    return returnBuilder();
  }

  /**
   * Sets the crust type for the pizza.
   *
   * @param crust the desired crust type
   * @return the builder instance for method chaining
   */
  public T crust(Crust crust) {
    this.crust = crust;
    return returnBuilder();
  }

  /**
   * Adds a topping with the specified portion to the pizza.
   *
   * @param name    the name of the topping
   * @param portion the portion size of the topping
   * @return the builder instance for method chaining
   */
  public T addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return returnBuilder();
  }

  /**
   * Returns the builder instance for the purpose of method chaining.
   * This method should be implemented by subclasses to return the correct builder type.
   *
   * @return the builder instance
   */
  protected abstract T returnBuilder();

  /**
   * Builds and returns the final pizza object based on the builder configuration.
   * Subclasses must implement this to provide the specific pizza type.
   *
   * @return an instance of ObservablePizza
   */
  public abstract ObservablePizza build();
}