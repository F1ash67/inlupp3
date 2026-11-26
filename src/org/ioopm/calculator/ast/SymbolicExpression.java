package org.ioopm.calculator.ast;

public abstract class SymbolicExpression {

  public boolean isConstant() {
    return false;
  }

  public boolean isCommand() {
    return false;
  }

  public String getName() {
    throw new RuntimeException("getName() called on expression with no operator");
  }

  public double getValue() {
    throw new RuntimeException("getValue() called on non constant");
  }

  public int getPriority() {
    return 4;
  }

  public abstract SymbolicExpression eval(Environment vars);
}
