import java.text.DecimalFormat;

public class Model {
    private final Viewer viewer;
    private String inputExpression;
    private final RPN rpn;
    private String textButton;
    private String result;

    public Model(Viewer viewer) {
        inputExpression = "";
        rpn = new RPN(this);
        this.viewer = viewer;
    }

    public void calculate(String newData) {
        if (newData.equals("Zero")) {
            processOperand("0");
        } else if (newData.equals("One")) {
            processOperand("1");
        } else if (newData.equals("Two")) {
            processOperand("2");
        } else if (newData.equals("Three")) {
            processOperand("3");
        } else if (newData.equals("Four")) {
            processOperand("4");
        } else if (newData.equals("Five")) {
            processOperand("5");
        } else if (newData.equals("Six")) {
            processOperand("6");
        } else if (newData.equals("Seven")) {
            processOperand("7");
        } else if (newData.equals("Eight")) {
            processOperand("8");
        } else if (newData.equals("Nine")) {
            processOperand("9");
        } else if (newData.equals("Open_p")) {
            processDotAndParenthesis("(");
        } else if (newData.equals("Close_p")) {
            processDotAndParenthesis(")");
        } else if (newData.equals("Dot")) {
            processDotAndParenthesis(".");
        } else if (newData.equals("Plus")) {
            processOperator("+");
        } else if (newData.equals("Minus")) {
            processOperator("-");
        } else if (newData.equals("Multiply")) {
            processOperator("*");
        } else if (newData.equals("Divide")) {
            processOperator("/");
        } else if (newData.equals("Del")) {
            String displayText = viewer.display.getText();
            if (inputExpression.endsWith(" ")) {
                viewer.display.setText("");
                inputExpression = inputExpression.substring(0, inputExpression.length() - 3);
                viewer.appendToDisplay(displayText.substring(0, displayText.length() - 1));
            } else {
                viewer.display.setText("");
                inputExpression = inputExpression.substring(0, inputExpression.length() - 1);
                viewer.appendToDisplay(displayText.substring(0, displayText.length() - 1));
            }
        } else if (newData.equals("Del_all")) {
            inputExpression = "";
            viewer.display.setText(inputExpression);
        } else if (newData.equals("Equal")) {
            viewer.display.setText("");
            double answer = rpn.infixToPostfix(inputExpression);
            DecimalFormat df = new DecimalFormat("#.#########################");
            result = df.format(answer);
            viewer.appendToDisplay(result);
        }
    }

    public void processOperand(String operand) {
        if (viewer.display.getText().equals(result)) {
            inputExpression = "";
            viewer.display.setText("");
        }
        inputExpression = inputExpression + operand;
        viewer.appendToDisplay(operand);
    }

    public void processDotAndParenthesis(String sign) {
        if (sign.equals(".")) {
            if (viewer.display.getText().isEmpty() || viewer.display.getText().endsWith(",")) {
                textButton = "";
            } else {
                textButton = ",";
                inputExpression = inputExpression + ".";
                viewer.appendToDisplay(textButton);
            }
        } else {
            if (viewer.display.getText().matches(".*[0-9]$") && sign.equals("(")) {
                textButton = "";
                return;
            }
            if (!viewer.display.getText().contains("(") && sign.equals(")")) {
                textButton = "";
                return;
            }
            if (viewer.display.getText().endsWith(",") || viewer.display.getText().endsWith(")") || viewer.display.getText().endsWith("(")) {
                textButton = "";
            } else {
                textButton = sign;
                inputExpression = inputExpression + " " + textButton + " ";
                viewer.appendToDisplay(textButton);
            }
        }
    }

    public void processOperator(String operator) {
        if (viewer.display.getText().isEmpty() || viewer.display.getText().endsWith(",")) {
            textButton = "";
        } else {
            if (operator.equals("*")) {
                textButton = "\u00D7";
            } else if (operator.equals("/")) {
                textButton = "\u00F7";
            } else {
                textButton = operator;
            }
            inputExpression = inputExpression + " " + operator + " ";
            viewer.appendToDisplay(textButton);
        }
    }
}
