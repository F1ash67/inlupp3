import org.ioopm.calculator.ast.*;

public class Test {
  public static void testPrinting(String expected, SymbolicExpression e) {
    System.out.println("Expected " + expected + " and got " + e + "\n");
    assert expected.equals("" + e) : "Expected '" + expected + "' but got '" + e + "'";
  }

  public static void testEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment vars) {
    SymbolicExpression r = e.eval(vars);
    System.out.println( e + " = " + r + "\n");
    assert expected.equals(r) : "Expected '" + expected + "' but got '" + r + "'";
  }

  public static void main(String[] args) {
    Constant c1 = new Constant(5);
    Constant c2 = new Constant(2);
    Variable v = new Variable("x");
    Addition a = new Addition(c1, v);
    Multiplication m = new Multiplication(a, c2);
    testPrinting("(5 + x) * 2", m);
    Multiplication m2 = new Multiplication(v, c2);
    Addition a2 = new Addition(c1, m2);
    testPrinting("5 + x * 2", a2);
    Subtraction s = new Subtraction(c2, a);
    testPrinting("2 - 5 + x", s);
    testPrinting("42", new Constant(42));
    testPrinting("x", v);
    testPrinting("sin x", new Sin(v));
    testPrinting("cos x", new Cos(v));
    testPrinting("exp x", new Exp(v));
    testPrinting("log x", new Log(v));
    testPrinting("-((5 + x) * 2)", new Negation(m));
    Sin sin = new Sin(a2);
    testPrinting("sin(5 + x * 2)", sin);
    Exp exp = new Exp(m);
    testPrinting("exp((5 + x) * 2)", exp);

    testPrinting("x = 5", new Assignment(v, c1));

    
    testPrinting("x = (5 + x) * 2", new Assignment(v, m));

    SymbolicExpression a3 = new Addition(new Constant(5), new Constant(37));
    SymbolicExpression c3 = new Constant(42);
    testEvaluating(c3, a3, new Environment()); /// Tests if reducing a returns b (it should!)

    SymbolicExpression c4 = new Constant(6);
    SymbolicExpression s2 = new Subtraction(new Constant(10), new Constant(4));
    testEvaluating(c4, s2, new Environment());

    SymbolicExpression c6 = new Constant(42);
    SymbolicExpression m3 = new Multiplication(new Constant(6), new Constant(7));
    testEvaluating(c6, m3, new Environment());

    SymbolicExpression c5 = new Constant(5);
    SymbolicExpression d = new Division(new Constant(20), new Constant(4));
    testEvaluating(c5, d, new Environment());

    testEvaluating(
      new Constant(15),
      new Multiplication(
        new Addition(new Constant(2), new Constant(3)),
        new Subtraction(new Constant(4), new Constant(1))
      ), new Environment()
    );

    SymbolicExpression expected =
      new Multiplication(
        new Addition(new Constant(5), new Variable("x")),
        new Constant(2)
      );

    SymbolicExpression expression =
      new Multiplication(
          new Addition(new Constant(5), new Variable("x")),
          new Constant(2)
      );

    testEvaluating(expected, expression, new Environment());

    testEvaluating(
      new Multiplication(
        new Constant(8),
        new Variable("x")
      ),
      new Multiplication(
        new Addition(new Constant(5), new Constant(3)),
        new Variable("x")
      ), new Environment()
    );

    testEvaluating(
      new Addition(
        new Variable("x"),
        new Constant(10)
      ),
      new Addition(
        new Variable("x"),
        new Multiplication(new Constant(5), new Constant(2))
      ), new Environment()
    );

    testEvaluating(new Constant(-5), new Negation(new Constant(5)), new Environment());

    testEvaluating(new Constant(0), new Sin(new Constant(0)), new Environment());

    testEvaluating(new Constant(1), new Cos(new Constant(0)), new Environment());

    testEvaluating(new Constant(1), new Exp(new Constant(0)), new Environment());

    testEvaluating(new Constant(0), new Log(new Constant(1)), new Environment());

    Environment env = new Environment();
    Assignment ass = new Assignment(c1, v);
    testEvaluating(c1, ass, env);
    System.out.println("a = " + new Variable("x").hashCode() + "\nb = " + new Variable("x").hashCode());
    testEvaluating(new Constant(20), expression, env);
    testEvaluating(new Constant(84), 
      new Addition(
        new NamedConstant("Answer"), 
        new NamedConstant("Answer")), env);

    try {
      Assignment illegalAssignment = new Assignment(
        new Constant(21),
        new NamedConstant("Answer")
      );

      illegalAssignment.eval(env);

      // If execution reaches this line, no exception was thrown.
      System.out.println("FAIL: Expected IllegalAssignmentException");
    } catch (IllegalAssignmentException e) {
      System.out.println("PASS: IllegalAssignmentException was thrown");
    }
  }
}
