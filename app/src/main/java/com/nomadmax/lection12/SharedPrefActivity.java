package com.nomadmax.lection12;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Max on 20.03.16.
 */

    public class SharedPrefActivity extends AppCompatActivity {

    public static final String PREFS = "shared_prefs";
    public static final String  KEY_CHECKBOX = "key_checkbox";
    public static final String  KEY_EDITTEXT = "key_edit_text";

    private CheckBox cbxCheck;
    private EditText etText;
    private Button btnLoadPrefs;
    private Button btnSavePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        etText = (EditText) findViewById(R.id.edit_pref);
        btnLoadPrefs = (Button) findViewById(R.id.load_pref);
        btnSavePrefs = (Button) findViewById(R.id.save_pref);
        cbxCheck = (CheckBox) findViewById(R.id.checkbox);
        btnLoadPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPrefs();
            }
        });
        btnSavePrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrefs();
            }
        });
    }

    private void savePrefs() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
//        SharedPreferences prefs=
//                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_CHECKBOX, cbxCheck.isChecked());
        editor.putString(KEY_EDITTEXT, etText.getText().toString());
//        editor.remove(KEY_EDITTEXT);
//        editor.clear();
        editor.commit();
//        editor.apply();
    }

    private void loadPrefs() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        cbxCheck.setChecked(sharedPreferences.getBoolean(KEY_CHECKBOX, false));
        etText.setText(sharedPreferences.getString(KEY_EDITTEXT, ""));
    }
}