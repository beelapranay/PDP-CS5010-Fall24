import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class PizzaBuilderTest {
  public static void main(String[] args) {
    // Building the AlaCartePizza using the builder pattern
    ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    // Print the result to verify creation
    System.out.println("AlaCarte Pizza created successfully!");
    System.out.println("Cost: $" + alacarte.cost());

    System.out.println("Has Cheese Topping: " + alacarte.hasTopping(ToppingName.Cheese));
    System.out.println("Has Sauce Topping: " + alacarte.hasTopping(ToppingName.Sauce));
    System.out.println("Has Green Pepper Topping: " + alacarte.hasTopping(ToppingName.GreenPepper));
    System.out.println("Has Onion Topping: " + alacarte.hasTopping(ToppingName.Onion));
    System.out.println("Has Jalapeno Topping: " + alacarte.hasTopping(ToppingName.Jalapeno));


    ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            //.addTopping(ToppingName.Cheese,ToppingPortion.LeftHalf)
            .build();

    System.out.println("Cheese Pizza created successfully!");
    System.out.println("Cheese Pizza Cost: $" + cheese.cost());

    // Creating Veggie Pizza
    ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();

    System.out.println("Veggie Pizza created successfully!");
    System.out.println("Veggie Pizza Cost: $" + veggie.cost());

  }
}
