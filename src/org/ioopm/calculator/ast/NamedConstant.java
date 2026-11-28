package org.ioopm.calculator.ast;

public class NamedConstant extends Constant{
  private String identifier;

  public NamedConstant(String identifier) {
    this.identifier = identifier;
    double value = Constants.namedConstants.get(identifier);
    super(value);
  }

  public String toString() {
    return this.identifier;
  }
}
