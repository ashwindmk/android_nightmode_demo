package com.ashwin.android.darktheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.ashwin.android.darktheme.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {
    public static final String APP_TAG = "dark-theme";
    private static final String SUB_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        Log.d(APP_TAG, SUB_TAG + ": onApplyThemeResource");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(APP_TAG, SUB_TAG + ": onCreate");
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // By-default, nothing is selected/checked.
        // The first selected will invoke this listener once with the new checkedId and isChecked=true.
        // The next selections will invoke this listener twice:
        //   - First with the previous checkedId and isChecked=false.
        //   - Second with the new checkedId and isChecked=true.
        // Repeated selections to the same button does not invoke this listener repeatedly.
        binding.themeToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                Log.d(APP_TAG, SUB_TAG + ": onButtonChecked( " + checkedId + ", " + isChecked + " )");
                if (isChecked) {
                    int theme;
                    switch (checkedId) {
                        case R.id.dark_theme_button: {
                            theme = AppCompatDelegate.MODE_NIGHT_YES;
                            break;
                        }
                        case R.id.light_theme_button: {
                            theme = AppCompatDelegate.MODE_NIGHT_NO;
                            break;
                        }
                        case R.id.default_theme_button:
                        default: {
                            // Even if we do not setDefaultNightMode, it is by-default MODE_NIGHT_FOLLOW_SYSTEM.
                            theme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                            break;
                        }
                    }
                    AppCompatDelegate.setDefaultNightMode(theme);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(APP_TAG, SUB_TAG + ": onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(APP_TAG, SUB_TAG + ": onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(APP_TAG, SUB_TAG + ": onDestroy");
    }
}
