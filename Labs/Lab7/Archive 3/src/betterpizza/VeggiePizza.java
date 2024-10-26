package betterpizza;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import java.util.HashMap;
import java.util.Map;

public class VeggiePizza implements ObservablePizza {
  private final Crust crust;
  private final Size size;
  private final Map<ToppingName, ToppingPortion> toppings;

  protected VeggiePizza(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings) {
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

  public static class VeggiePizzaBuilder extends PizzaBuilder {
    private Crust crust;
    private Size size;
    private final Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

    public VeggiePizzaBuilder() {
//      this.crust = Crust.Classic; // Default crust
//      this.size = Size.Small; // Default size
      // Default veggie toppings
      toppings.put(ToppingName.Cheese,ToppingPortion.Full);
      toppings.put(ToppingName.Sauce,ToppingPortion.Full);
      toppings.put(ToppingName.BlackOlive,ToppingPortion.Full);
      toppings.put(ToppingName.GreenPepper,ToppingPortion.Full);
      toppings.put(ToppingName.Onion,ToppingPortion.Full);
      toppings.put(ToppingName.Jalapeno,ToppingPortion.Full);
      toppings.put(ToppingName.Tomato,ToppingPortion.Full);
    }

    public VeggiePizzaBuilder crust(Crust crust) {
      this.crust = crust;
      return this;
    }

    public VeggiePizzaBuilder size(Size size) {
      this.size = size;
      return this;
    }

    public VeggiePizzaBuilder addTopping(ToppingName topping, ToppingPortion portion) {
      toppings.put(topping, portion);
      return this;
    }

    public VeggiePizzaBuilder noCheese() {
      toppings.remove(ToppingName.Cheese);
      return this;
    }

    public VeggiePizzaBuilder noSauce() {
      toppings.remove(ToppingName.Sauce);
      return this;
    }

    public VeggiePizzaBuilder noBlackOlive() {
      toppings.remove(ToppingName.BlackOlive);
      return this;
    }

    public VeggiePizzaBuilder noGreenPepper() {
      toppings.remove(ToppingName.GreenPepper);
      return this;
    }

    public VeggiePizzaBuilder noOnion() {
      toppings.remove(ToppingName.Onion);
      return this;
    }

    public VeggiePizzaBuilder noJalapeno() {
      toppings.remove(ToppingName.Jalapeno);
      return this;
    }

    public VeggiePizzaBuilder noTomato() {
      toppings.remove(ToppingName.Tomato);
      return this;
    }


    @Override
    protected VeggiePizzaBuilder self() {
      return this;
    }

    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new VeggiePizza(crust, size, toppings);
    }
  }
}
