package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class VeggiePizza extends AlaCartePizza {
  public VeggiePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizza.VeggiePizzaBuilder> {
    @Override
    protected VeggiePizza.VeggiePizzaBuilder returnBuilder() {
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

    public VeggiePizzaBuilder noWhiteSauce() {
      toppings.remove(ToppingName.Alfredo);
      return this;
    }

    public VeggiePizzaBuilder noGreenPepper() {
      toppings.remove(ToppingName.GreenPepper);
      return this;
    }

    public VeggiePizzaBuilder noTomato() {
      toppings.remove(ToppingName.Tomato);
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

    public VeggiePizzaBuilder noBlackOlive() {
      toppings.remove(ToppingName.BlackOlive);
      return this;
    }

    @Override
    public VeggiePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Pizza size must be specified.");
      }
      return new VeggiePizza(size, crust, toppings);
    }
  }
}
