import java.net.PasswordAuthentication;

class A {

  String fieldNameWithPasswordInIt = retrievePassword();

  private static final String PASSED = "passed"; // compliant nothing to do with passwords

  private void a(char[] pwd, String var) {
    String variable1 = "blabla";
    String variable2 = "login=a&password=xxx"; // Noncompliant
    String variable3 = "login=a&passwd=xxx"; // Noncompliant
    String variable4 = "login=a&pwd=xxx"; // Noncompliant
    String variable5 = "login=a&password=";
    String variable6 = "login=a&password= ";

    String variableNameWithPasswordInIt = "xxx"; // Noncompliant
    String variableNameWithPasswdInIt = "xxx"; // Noncompliant
    String variableNameWithPwdInIt = "xxx"; // Noncompliant
    String otherVariableNameWithPasswordInIt;
    fieldNameWithPasswordInIt = "xx"; // Noncompliant
    fieldNameWithPasswordInIt = retrievePassword();
    this.fieldNameWithPasswordInIt = "xx"; // Noncompliant
    this.fieldNameWithPasswordInIt = retrievePassword();
    variable1 = "xx";

    String[] array = {};
    array[0] = "xx";

    A myA = new A();
    myA.setProperty("password", "xxxxx"); // XXXoncompliant - not supported in UAST rule
    myA.setProperty("passwd", "xxxxx"); // XXXoncompliant - not supported in UAST rule
    myA.setProperty("pwd", "xxxxx"); // XXXoncompliant - not supported in UAST rule
    myA.setProperty("password", new Object());
    myA.setProperty("xxxxx", "password");
    myA.setProperty(12, "xxxxx");
    myA.setProperty(new Object(), new Object());

    MyUnknownClass.myUnknownMethod("password", "xxxxx"); // XXXoncompliant - not supported in UAST rule

    PasswordAuthentication pa;
    pa = new PasswordAuthentication("userName", "1234".toCharArray());  // XXXoncompliant {{Remove this hard-coded password.}} - not supported in UAST rule
    pa = new PasswordAuthentication("userName", pwd); // Compliant
    pa = new PasswordAuthentication("userName", getPwd(var)); // Compliant
    pa = new PasswordAuthentication("userName", var.toCharArray()); // Compliant

    OtherPasswordAuthentication opa;
    opa = new OtherPasswordAuthentication("userName", "1234".toCharArray()); // Compliant
  }

  private char[] getPwd(String s) {
    return null;
  }

  private String retrievePassword() {
    return null;
  }

  private void setProperty(Object property, Object Value) {
  }

  private static class OtherPasswordAuthentication {
    OtherPasswordAuthentication(String username, char[] pwd) {}
  }
}
