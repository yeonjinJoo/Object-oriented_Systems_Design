package Week13;

public class Calculator implements RealNumberCalculator, ComplexNumberCalculator{
    // 유리수 표현
    public ComplexNumber.RealNumber add(ComplexNumber.RealNumber r1, ComplexNumber.RealNumber r2){
        int divisor = r1.divisor * r2.divisor;
        int dividend = r1.dividend * r2.divisor + r2.dividend * r1.divisor;
        return new ComplexNumber.RealNumber(dividend, divisor);
    }
    public ComplexNumber.RealNumber sub(ComplexNumber.RealNumber r1, ComplexNumber.RealNumber r2){
        int divisor = r1.divisor * r2.divisor;
        int dividend = r1.dividend * r2.divisor - r2.dividend * r1.divisor;
        return new ComplexNumber.RealNumber(dividend, divisor);
    }
    public ComplexNumber.RealNumber mul(ComplexNumber.RealNumber r1, ComplexNumber.RealNumber r2){
        int divisor = r1.divisor * r2.divisor;
        int dividend = r1.dividend * r2.dividend;
        return new ComplexNumber.RealNumber(dividend, divisor);
    }

    // 복소수 표현
    public ComplexNumber add(ComplexNumber c1, ComplexNumber c2){
        return new ComplexNumber(add(c1.real, c2.real), add(c1.imaginary, c2.imaginary));
    }
    public ComplexNumber sub(ComplexNumber c1, ComplexNumber c2){
        return new ComplexNumber(sub(c1.real, c2.real), sub(c1.imaginary, c2.imaginary));
    }
    public ComplexNumber mul(ComplexNumber c1, ComplexNumber c2){
        return new ComplexNumber(mul(c1.real, c2.real), mul(c1.imaginary, c2.imaginary));
    }
}
