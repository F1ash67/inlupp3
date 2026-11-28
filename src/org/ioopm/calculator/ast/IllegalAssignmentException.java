package org.ioopm.calculator.ast;

public class IllegalAssignmentException extends RuntimeException{
  public IllegalAssignmentException(String name) {
    super("Error: cannot redefine named constant: '" + name + "'");
  }
}
