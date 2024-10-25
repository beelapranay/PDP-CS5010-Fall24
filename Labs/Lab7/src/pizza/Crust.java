package pizza;

/**
 * This enumerated type represents the different types of crusts
 * on a pizza, along with a string description associated with each
 * of them.
 */
public enum Crust {
  Classic("Classic"),Stuffed("Stuffed"),Thin("Thin");

  private final String descriptor;

  public String toString() {
    return descriptor;
  }

  Crust(String d) {
    this.descriptor = d;
  }
}
