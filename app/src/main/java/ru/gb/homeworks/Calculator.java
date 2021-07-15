package ru.gb.homeworks;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator implements Parcelable {


    private String displayCalculation = "";
    private String displayResult = "";

    private int valueBtn0 = 0;
    private int valueBtn1 = 1;
    private int valueBtn2 = 2;
    private int valueBtn3 = 3;
    private int valueBtn4 = 4;
    private int valueBtn5 = 5;
    private int valueBtn6 = 6;
    private int valueBtn7 = 7;
    private int valueBtn8 = 8;
    private int valueBtn9 = 9;

    private String divide = " / ";
    private static final String multiply = " * ";
    private static final String subtraction = " - ";
    private static final String addition = " + ";
    private String valueBtnDot = ".";
    private String dotZero = "0.";
    private static final String emptySpace = " ";
    private boolean dotInserted = false;
    private boolean operatorInserted = false;


    protected Calculator(Parcel in) {
        displayCalculation = in.readString();
        displayResult = in.readString();
        valueBtn0 = in.readInt();
        valueBtn1 = in.readInt();
        valueBtn2 = in.readInt();
        valueBtn3 = in.readInt();
        valueBtn4 = in.readInt();
        valueBtn5 = in.readInt();
        valueBtn6 = in.readInt();
        valueBtn7 = in.readInt();
        valueBtn8 = in.readInt();
        valueBtn9 = in.readInt();
        divide = in.readString();
        valueBtnDot = in.readString();
        dotZero = in.readString();
        dotInserted = in.readByte() != 0;
        operatorInserted = in.readByte() != 0;
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public Calculator() {

    }

    public void concatenateValues(int valueBtn) {
        displayCalculation = displayCalculation + valueBtn;
        updateCalculation();
    }

    public void pushBtn0() {
        concatenateValues(valueBtn0);
    }

    public void pushBtn1() {
        concatenateValues(valueBtn1);

    }

    public void pushBtn2() {
        concatenateValues(valueBtn2);

    }

    public void pushBtn3() {
        concatenateValues(valueBtn3);

    }

    public void pushBtn4() {
        concatenateValues(valueBtn4);

    }

    public void pushBtn5() {
        concatenateValues(valueBtn5);

    }

    public void pushBtn6() {
        concatenateValues(valueBtn6);

    }

    public void pushBtn7() {
        concatenateValues(valueBtn7);
    }

    public void pushBtn8() {
        concatenateValues(valueBtn8);
    }

    public void pushBtn9() {
        concatenateValues(valueBtn9);
    }

    public void pushDot() {
        if (displayCalculation.isEmpty()) {
            displayCalculation = dotZero;
            dotInserted = true;
        }
        if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(emptySpace)) {
            displayCalculation = displayCalculation + dotZero;
            dotInserted = true;
        }
        if (dotInserted == false) {
            displayCalculation = displayCalculation + valueBtnDot;
            dotInserted = true;
        }
        updateCalculation();
    }

    public void pushBackspace() {
        backspace();
        updateCalculation();
        updateResult();
    }

    public void pushClear() {
        clearField();
        updateCalculation();
        updateResult();
    }

    public void pushDivide() {
        dotInserted = false;
        if (!displayCalculation.isEmpty()) {
            if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(valueBtnDot)) {
                backspace();
            }
            if (operatorInserted == false) {
                displayCalculation = displayCalculation + divide;
                operatorInserted = true;
            }
        }
        updateCalculation();
    }

    public void pushMultiply() {
        dotInserted = false;
        if (!displayCalculation.isEmpty()) {
            if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(valueBtnDot)) {
                backspace();
            }
            if (operatorInserted == false) {
                displayCalculation = displayCalculation + multiply;
                operatorInserted = true;
            }
        }
        updateCalculation();
    }

    public void pushSubtraction() {
        dotInserted = false;
        if (!displayCalculation.isEmpty()) {
            if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(valueBtnDot)) {
                backspace();
            }
            if (operatorInserted == false) {
                displayCalculation = displayCalculation + subtraction;
                operatorInserted = true;
            }
        }
        updateCalculation();
    }

    public void pushAddition() {
        dotInserted = false;
        if (!displayCalculation.isEmpty()) {
            if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(valueBtnDot)) {
                backspace();
            }
            if (operatorInserted == false) {
                displayCalculation = displayCalculation + addition;
                operatorInserted = true;
            }
        }
        updateCalculation();
    }

    public void pushCalculate() {
        if (operatorInserted == true && !displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(emptySpace)) {
            String[] array = displayCalculation.split(emptySpace);
            switch (array[1].charAt(0)) {
                case '+':
                    displayResult = Double.toString(Double.parseDouble(array[0]) + Double.parseDouble(array[2]));
                    break;
                case '-':
                    displayResult = Double.toString(Double.parseDouble(array[0]) - Double.parseDouble(array[2]));
                    break;
                case '*':
                    displayResult = Double.toString(Double.parseDouble(array[0]) * Double.parseDouble(array[2]));
                    break;

                case '/':
                    if (Double.parseDouble(array[2]) == 0) {
                        displayResult = "Cannot divide by zero";
                    } else
                        displayResult = Double.toString(Double.parseDouble(array[0]) / Double.parseDouble(array[2]));
                    break;
            }
            updateResult();
        }
    }


    public void updateCalculation() {
        MainActivity.calculation.setText(String.valueOf(displayCalculation));
    }


    public void updateResult() {
        MainActivity.result.setText(String.valueOf(displayResult));

    }

    public void clearField() {
        displayCalculation = "";
        displayResult = "";
        dotInserted = false;
        operatorInserted = false;
    }

    public void backspace() {
        if (!displayCalculation.isEmpty()) {

            if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(valueBtnDot)) {
                dotInserted = false;
            }

            if (displayCalculation.substring(displayCalculation.length() - 1, displayCalculation.length()).equals(emptySpace)) {
                displayCalculation = displayCalculation.substring(0, displayCalculation.length() - 3);
                operatorInserted = false;
            } else {
                displayCalculation = displayCalculation.substring(0, displayCalculation.length() - 1);
            }
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayCalculation);
        dest.writeString(displayResult);
        dest.writeInt(valueBtn0);
        dest.writeInt(valueBtn1);
        dest.writeInt(valueBtn2);
        dest.writeInt(valueBtn3);
        dest.writeInt(valueBtn4);
        dest.writeInt(valueBtn5);
        dest.writeInt(valueBtn6);
        dest.writeInt(valueBtn7);
        dest.writeInt(valueBtn8);
        dest.writeInt(valueBtn9);
        dest.writeString(divide);
        dest.writeString(valueBtnDot);
        dest.writeString(dotZero);
        dest.writeByte((byte) (dotInserted ? 1 : 0));
        dest.writeByte((byte) (operatorInserted ? 1 : 0));
    }
}
