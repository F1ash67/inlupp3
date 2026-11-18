package org.ioopm.calculator.ast;

public class Unary extends SymbolicExpression{
  private SymbolicExpression argument;

  public Unary (SymbolicExpression argument) {
    this.argument = argument;
  }

  public int getPriority() {
    return 3;
  }

  public String toString() {
    /// Note how the call to toString() is not necessary
    if(argument.getPriority() > this.getPriority()) return this.getName() + " " + this.argument.toString();
    return this.getName() + "(" + this.argument.toString() + ")";
}
}
