package polynomial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//ask if hashmap is okay; is x^1 supposed to be x
//implement the equals method public boolean equals(Polynomial other)

public class SimplePolynomial implements Polynomial {

  private StringBuilder polynomial;
  private List<Integer> polynomialTerms = new ArrayList<>();

  public SimplePolynomial() {
    this.polynomial = new StringBuilder("0");
  }

  @Override
  public Polynomial add(Polynomial other) {
    Map<Integer, Integer> otherPolynomialTerms = getPolynomialTerms(other);

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

  public boolean equals(Object other) {
    return other.toString().equals(this.toString());
  }

  @Override
  public Polynomial multiply(Polynomial other) {
    Map<Integer, Integer> otherPolynomialTerms = getPolynomialTerms(other);
    Map<Integer, Integer> newPolynomialTerms = new HashMap<>();

    for(int thisPower : this.polynomialTerms.keySet()) {
      int thisCoefficient = this.polynomialTerms.get(thisPower);

      for(int otherPower : otherPolynomialTerms.keySet()) {
        int otherCoefficient = otherPolynomialTerms.get(otherPower);

        int newCoefficient = thisCoefficient * otherCoefficient;
        int newPower = thisPower + otherPower;

        if(newPolynomialTerms.containsKey(newPower)) {
          newCoefficient = newCoefficient + newPolynomialTerms.get(newPower);
          newPolynomialTerms.put(newPower, newCoefficient);
        } else {
          newPolynomialTerms.put(newPower, newCoefficient);
        }
      }
    }

    Polynomial result = new SimplePolynomial();
    setTerms(newPolynomialTerms);  // Assuming you have a setTerms method in the Polynomial class
    return result;
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
      polynomialTerms.add(power, coefficient);
    }
  }

  @Override
  public int getDegree() {
    int highestPower = -1;

    for(int i = 0; i < polynomialTerms.size(); i++) {
      int power = polynomialTerms.get(i);
      if(power[i]power > highestPower) {
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
}
