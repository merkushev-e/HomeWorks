package ru.gb.homeworks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class Settings extends AppCompatActivity implements Constants {

    int currentThemeCode;
    int currentThemeResId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentThemeCode = getCodeStyle();
        currentThemeResId = codeStyleToStyleId(currentThemeCode);
        setTheme(currentThemeResId);

        setContentView(R.layout.activity_settings);

        initThemeButtons();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intentResult = new Intent();
        setResult(RESULT_OK, intentResult);
        finish();

    }

    public void initThemeButtons() {
        findViewById(R.id.radioButton).setOnClickListener(v -> {
            setAppTheme(lightTheme);
            recreate();
        });
        findViewById(R.id.radioButton2).setOnClickListener(v -> {
            setAppTheme(darkTheme);
            recreate();
        });
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle())).setChecked(true);

    }

    protected void setAppTheme(int codeStyle) {
        SharedPreferences preferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        preferences.edit()
                .putInt(appTheme, codeStyle)
                .apply();
    }


    protected int getCodeStyle() {
        SharedPreferences preferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return preferences.getInt(appTheme, lightTheme);
    }

    protected int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case darkTheme:
                return R.style.DarkTheme;
            default:
                return R.style.AppTheme;
        }
    }
}