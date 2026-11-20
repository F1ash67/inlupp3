import org.ioopm.calculator.ast.*;

public class Test {
  public void testPrinting(String expected, SymbolicExpression e) {
    System.out.println(e + "\n");
    assert expected.equals("" + e) : "Expected '" + expected + "' but got '" + e + "'";
  }

  public void testEvaluating(SymbolicExpression expected, SymbolicExpression e) {
    SymbolicExpression r = e.eval();
    System.out.println(r + "\n");
    assert expected.equals("" + r) : "Expected '" + expected + "' but got '" + r + "'";
  }

  public void main(String[] args) {
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
    testPrinting("x", new Variable("x"));
    testPrinting("sin x", new Sin(new Variable("x")));
    testPrinting("cos x", new Cos(new Variable("x")));
    testPrinting("exp x", new Exp(new Variable("x")));
    testPrinting("log x", new Log(new Variable("x")));
    testPrinting("neg x", new Negation(new Variable("x")));
    Sin sin = new Sin(a2);
    testPrinting("sin(5 + x * 2)", sin);
    Exp exp = new Exp(m);
    testPrinting("exp((5 + x) * 2)", exp);

    SymbolicExpression a3 = new Addition(new Constant(5), new Constant(37));
    SymbolicExpression c3 = new Constant(42);
    testEvaluating(c3, a3); /// Tests if reducing a returns b (it should!)

    SymbolicExpression c4 = new Constant(6);
    SymbolicExpression s2 = new Subtraction(new Constant(10), new Constant(4));
    testEvaluating(c4, s2);

    SymbolicExpression c6 = new Constant(42);
    SymbolicExpression m3 = new Multiplication(new Constant(6), new Constant(7));
    testEvaluating(c6, m3);

    SymbolicExpression c5 = new Constant(5);
    SymbolicExpression d = new Division(new Constant(20), new Constant(4));
    testEvaluating(c5, d);

    testEvaluating(
      new Constant(15),
      new Multiplication(
        new Addition(new Constant(2), new Constant(3)),
        new Subtraction(new Constant(4), new Constant(1))
      )
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

    testEvaluating(expected, expression);

    testEvaluating(
      new Multiplication(
        new Constant(8),
        new Variable("x")
      ),
      new Multiplication(
        new Addition(new Constant(5), new Constant(3)),
        new Variable("x")
      )
    );

    testEvaluating(
      new Addition(
        new Variable("x"),
        new Constant(10)
      ),
      new Addition(
        new Variable("x"),
        new Multiplication(new Constant(5), new Constant(2))
      )
    );

    testEvaluating(new Constant(-5), new Negation(new Constant(5)));

    testEvaluating(new Constant(0), new Sin(new Constant(0)));

    testEvaluating(new Constant(1), new Cos(new Constant(0)));

    testEvaluating(new Constant(1), new Exp(new Constant(0)));

    testEvaluating(new Constant(0), new Log(new Constant(1)));
  }
}
