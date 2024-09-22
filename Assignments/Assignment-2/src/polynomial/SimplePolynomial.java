package polynomial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePolynomial implements Polynomial {

  private StringBuilder polynomial;
  private Map<Integer, Integer> polynomialTerms = new HashMap<>();

  public SimplePolynomial() {
    this.polynomial = new StringBuilder("0");
  }

  @Override
  public Polynomial add(Polynomial other) {
    Map<Integer, Integer> otherPolynomialTerms = new HashMap<>();

    for(int i = other.getDegree(); i >= 0; i--) {
      if(other.getCoefficient(i) != 0) {
        otherPolynomialTerms.put(i, other.getCoefficient(i));
      }
    }

    for(int power : this.polynomialTerms.keySet()) {
        int newCoefficient = otherPolynomialTerms.getOrDefault(power, 0)
                + this.polynomialTerms.get(power);
        this.polynomialTerms.put(power, newCoefficient);
    }

    for(int power : otherPolynomialTerms.keySet()) {
      if(!this.polynomialTerms.containsKey(power)) {
        this.polynomialTerms.put(power, otherPolynomialTerms.get(power));
      }
    }

    return this;
  }

  @Override
  public Polynomial multiply(Polynomial other) {
    return null;
  }

  @Override
  public Polynomial derivative() {
    Map<Integer, Integer> derivativeTerms = new HashMap<Integer, Integer>();

    for(int power : polynomialTerms.keySet()) {
      if(power == 0) {
        derivativeTerms.put(power, 0);
      } else {
        derivativeTerms.put(power - 1, power * polynomialTerms.get(power));
      }
    }

    polynomialTerms = derivativeTerms;

    return this;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if(power < 0) {
      throw new IllegalArgumentException();
    } else {
      polynomialTerms.put(power, coefficient);
    }
  }

  @Override
  public int getDegree() {
    int highestPower = -1;

    for(int power : polynomialTerms.keySet()) {
      if(power > highestPower) {
        highestPower = power;
      }
    }

    return highestPower;
  }

  @Override
  public double evaluate(double x) {
    double result = 0.0;

    for (Map.Entry<Integer, Integer> entry : polynomialTerms.entrySet()) {
      int power = entry.getKey();
      int coefficient = entry.getValue();

      result += Math.pow(x, power) * coefficient;
    }
    return result;
  }

  @Override
  public int getCoefficient(int power) {
    return polynomialTerms.getOrDefault(power, 0);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    List<Integer> powers = new ArrayList<Integer>(polynomialTerms.keySet());
    powers.sort(Collections.reverseOrder());


    for(int power : powers) {
      int coefficient = polynomialTerms.get(power);

      if(coefficient == 0) {
        continue;
      } if(result.length() > 0 && coefficient > 0) {
        result.append("+");
      } if(power == 0) {
        result.append(coefficient);
      } else if (power == 1) {
        result.append(coefficient).append("x");
      } else {
        result.append(coefficient).append("x^").append(power);
      }
    }

    if(result.length() > 0) {
      this.polynomial = new StringBuilder(result.toString());
    } else {
      this.polynomial = new StringBuilder("0");
    }

    return this.polynomial.toString();
  }

  public static void main(String[] args) {
    SimplePolynomial simplePolynomial = new SimplePolynomial();
    SimplePolynomial simplePolynomial1 = new SimplePolynomial();

    simplePolynomial.addTerm(3, 4);
    simplePolynomial.addTerm(-5, 3);
    simplePolynomial.addTerm(2, 1);
    simplePolynomial.addTerm(-4, 0);

    simplePolynomial1.addTerm(-5, 2);
    simplePolynomial1.addTerm(2, 1);
    simplePolynomial1.addTerm(-4, 0);

//    System.out.println(simplePolynomial.evaluate(2.5));
//    System.out.println(simplePolynomial.getDegree());
//    ((((simplePolynomial.derivative()).derivative()).derivative()).derivative()).derivative();
//    System.out.println(simplePolynomial.toString());
    simplePolynomial.add(simplePolynomial1);
    System.out.println(simplePolynomial.toString());
  }
}
