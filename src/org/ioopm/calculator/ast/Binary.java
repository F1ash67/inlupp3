package org.ioopm.calculator.ast;

public class Binary extends SymbolicExpression {
  private SymbolicExpression lhs = null;
  private SymbolicExpression rhs = null;

  public Binary(SymbolicExpression lhs, SymbolicExpression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public String toString() {
      String left = lhs.toString();
      String right = rhs.toString();

      if (lhs.getPriority() < this.getPriority()) {
          left = "(" + left + ")";
      }

      if (rhs.getPriority() < this.getPriority()) {
          right = "(" + right + ")";
      }

      return left + " " + this.getName() + " " + right;
  }

  public boolean equals(Object other) {
    if (other instanceof Binary) {
      return this.equals((Binary) other);
    } else {
      return false;
    }
  }

  public boolean equals(Binary other) {
    /// access a private field of other!
    return this.lhs.equals(other.lhs) && 
            this.rhs.equals(other.rhs) && 
            this.getName().equals(other.getName());
  } 
}
