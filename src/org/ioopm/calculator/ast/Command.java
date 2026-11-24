package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression{
  public SymbolicExpression eval(Environment vars) {
    throw new RuntimeException("Cannot evaluate a command");
  }
}
