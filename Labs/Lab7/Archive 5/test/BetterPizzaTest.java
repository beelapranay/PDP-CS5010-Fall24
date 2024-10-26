import org.junit.Before;
import org.junit.Test;

import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;

/**
 * The test class for pizzas.
 */
public class BetterPizzaTest {
  private ObservablePizza alaCarte;
  private ObservablePizza cheese;
  private ObservablePizza veggie;


  @Before
  public void setup() {
    alaCarte = new betterpizza.AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce,ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper,ToppingPortion.Full)
            .addTopping(ToppingName.Onion,ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf)
            .build();

    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Medium)
            .leftHalfCheese()
            .noCheese()
            .build();

    veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce,ToppingPortion.Full)
            .addTopping(ToppingName.Alfredo,ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper,ToppingPortion.Full)
            .addTopping(ToppingName.Tomato,ToppingPortion.Full)
            .addTopping(ToppingName.Onion,ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno,ToppingPortion.Full)
            .addTopping(ToppingName.BlackOlive,ToppingPortion.Full)
            .noCheese()
            .noSauce()
            .noWhiteSauce()
            .noGreenPepper()
            .noTomato()
            .noOnion()
            .noJalapeno()
            .noBlackOlive()
            .build();
  }

  @Test
  public void testCost() {
    assertEquals(8.25, alaCarte.cost(), 0.01);
    assertEquals(5, cheese.cost(),0.01);
    assertEquals(7.0, veggie.cost(),0.01);

  }



}