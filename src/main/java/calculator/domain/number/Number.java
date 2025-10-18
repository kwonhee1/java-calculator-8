package calculator.domain.number;

public class Number {

    private final Long number;

    public Number(String numberStr) {
        number = Long.parseLong(numberStr);
        validateIsPositive(number);
    }

    private void validateIsPositive(Long number) {
        if (number <= 0)
            throw new IllegalArgumentException("잘못된 입력입니다.");
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
