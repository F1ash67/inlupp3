package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression{
  private SymbolicExpression argument;

  public Unary (SymbolicExpression argument) {
    this.argument = argument;
  }

  public int getPriority() {
    return 3;
  }

  public SymbolicExpression getArg() {
    return this.argument;
  }

  public String toString() {
    /// Note how the call to toString() is not necessary
    if(argument.getPriority() > this.getPriority()) return this.getName() + " " + this.argument.toString();
    return this.getName() + "(" + this.argument.toString() + ")";
  }

  public boolean equals(Object other) {
    if (other instanceof Unary) {
      return this.equals((Unary) other);
    } else {
      return false;
    }
  }

  public boolean equals(Unary other) {
    /// access a private field of other!
    return this.getName().equals(other.getName()) && this.argument.equals((other.argument));
  }
}
