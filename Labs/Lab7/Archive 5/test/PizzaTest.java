import org.junit.Before;
import org.junit.Test;

import pizza.AlaCartePizza;
import pizza.CheesePizza;
import pizza.Pizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;

/**
 * The test class for pizzas.
 */
public class PizzaTest {
  private Pizza alaCarte;
  private Pizza cheese;
  private Pizza halfCheese;


  @Before
  public void setup() {
    alaCarte = new AlaCartePizza(Size.Medium,Crust.Classic);
    alaCarte.addTopping(ToppingName.Cheese, ToppingPortion.Full);
    alaCarte.addTopping(ToppingName.Sauce,ToppingPortion.Full);
    alaCarte.addTopping(ToppingName.GreenPepper,ToppingPortion.Full);
    alaCarte.addTopping(ToppingName.Onion,ToppingPortion.Full);
    alaCarte.addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf);

    cheese = new CheesePizza(Size.Large,Crust.Thin);

    halfCheese = new CheesePizza(Size.Large,Crust.Thin);
    halfCheese.addTopping(ToppingName.Cheese,ToppingPortion.LeftHalf);
  }

  @Test
  public void testCost() {
    assertEquals(8.25, alaCarte.cost(),0.01);
    assertEquals(9,cheese.cost(),0.01);
    assertEquals(8.5,halfCheese.cost(),0.01);

  }
}
