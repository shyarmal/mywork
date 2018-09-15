package airwell.rpn.calculator;

public enum Operator {

	PLUS("+"), MINUS("-"), DIVIDE("/"), MULTIPLY("*"), SQRT("sqrt"), UNDO("undo"), CLEAR("clear");
	
	private String op;
	
	Operator(String op) {
		this.op = op;
	}
	
	public static Operator get(String value) {
		for(Operator operator: values()) {
			if (value.equals(operator.op)) {
				return operator;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return op;
	}
}
