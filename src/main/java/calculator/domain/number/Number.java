package calculator.domain.number;

public class Number {

    private final Long number;

    public Number(String numberStr) {
        validateIsNotEmpty(numberStr);
        number = Long.parseLong(numberStr);
        validateIsPositive(number);
    }

    private void validateIsNotEmpty(String numberStr) {
        if (numberStr==null || numberStr.isEmpty())
            throw new IllegalArgumentException("Number cna not be empty");
    }

    private void validateIsPositive(Long number) {
        if (number <= 0)
            throw new IllegalArgumentException("Number must be positive");
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Number))
            return false;

        Number otherNumber = (Number) other;

        if(!otherNumber.number.equals(this.number))
            return false;

        return true;
    }

    public Long getNumber() { return number;}

}
