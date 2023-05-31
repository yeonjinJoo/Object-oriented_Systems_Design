package Week13;

public interface ComplexNumberCalculator {
    public ComplexNumber add(ComplexNumber c1, ComplexNumber c2);
    public ComplexNumber sub(ComplexNumber c1, ComplexNumber c2);
    public ComplexNumber mul(ComplexNumber c1, ComplexNumber c2);
    default public void printResult(ComplexNumber c){
        System.out.printf("Real : %s, Imaginary : %s\n", c.real.toString(), c.imaginary.toString());
    }
}
