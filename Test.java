import org.ioopm.calculator.ast.*;

public class Test {
  public static void testPrinting(String expected, SymbolicExpression e) {
    System.out.println(e + "\n");
    assert expected.equals("" + e) : "Expected '" + expected + "' but got '" + e + "'";
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
  }
}
