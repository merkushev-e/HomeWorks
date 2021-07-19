package ru.gb.homeworks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;


public class MainActivity extends AppCompatActivity implements Constants{

    static EditText calculation;
    static TextView result;
    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentThemeCode = getCodeStyle();
        int currentThemeResId = codeStyleToStyleId(currentThemeCode);
        setTheme(currentThemeResId);


        setContentView(R.layout.activity_main);

        calculation = findViewById(R.id.calculation);
        result = findViewById(R.id.result);

        calculator = new Calculator();

        findViewById(R.id.settingsButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });


        findViewById(R.id.btn_0).setOnClickListener(v -> calculator.pushBtn0());
        findViewById(R.id.btn1).setOnClickListener(v -> calculator.pushBtn1());
        findViewById(R.id.btn2).setOnClickListener(v -> calculator.pushBtn2());
        findViewById(R.id.btn3).setOnClickListener(v -> calculator.pushBtn3());
        findViewById(R.id.btn4).setOnClickListener(v -> calculator.pushBtn4());
        findViewById(R.id.btn5).setOnClickListener(v -> calculator.pushBtn5());
        findViewById(R.id.btn6).setOnClickListener(v -> calculator.pushBtn6());
        findViewById(R.id.btn7).setOnClickListener(v -> calculator.pushBtn7());
        findViewById(R.id.btn8).setOnClickListener(v -> calculator.pushBtn8());
        findViewById(R.id.btn9).setOnClickListener(v -> calculator.pushBtn9());

        findViewById(R.id.btn_dot).setOnClickListener(v -> calculator.pushDot());
        findViewById(R.id.btn_clear).setOnClickListener(v -> calculator.pushClear());
        findViewById(R.id.btn_backspace).setOnClickListener(v -> calculator.pushBackspace());

        findViewById(R.id.btn_divide).setOnClickListener(v -> calculator.pushDivide());
        findViewById(R.id.btn_multiply).setOnClickListener(v -> calculator.pushMultiply());
        findViewById(R.id.btn_sub).setOnClickListener(v -> calculator.pushSubtraction());
        findViewById(R.id.btn_add).setOnClickListener(v -> calculator.pushAddition());

        findViewById(R.id.btn_calculate).setOnClickListener(v -> calculator.pushCalculate());

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.PARAM_COUNTER, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getParcelable(Constants.PARAM_COUNTER);
        calculator.updateCalculation();
    }



    protected int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case darkTheme:
                return R.style.DarkTheme;
            default:
                return R.style.AppTheme;
        }
    }
    protected int getCodeStyle() {
        SharedPreferences preferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return preferences.getInt(appTheme, lightTheme);
    }
}