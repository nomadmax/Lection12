package com.nomadmax.lection12;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class InternalExternalActivity extends AppCompatActivity {
    public static final String FILE_NAME = "my_file.txt";
    private EditText mEditText;
    private Button btnLoadFromExternal;
    private Button btnSaveToExternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesystem);
        mEditText = (EditText) findViewById(R.id.edit_text);
        btnLoadFromExternal = (Button) findViewById(R.id.load);
        btnSaveToExternal = (Button) findViewById(R.id.save);
        btnLoadFromExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(mEditText);
            }
        });
        btnSaveToExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(mEditText.getText().toString());
            }
        });
    }

    private void save(String _text) {
        try {
//            File fileToEdit = new File(getCacheDir(), FILE_NAME);

//            File fileToEdit = new File(getExternalFilesDirs(null), FILE_NAME);
            if (!isExternalStorageWritable()) {
                return;
            }
            File fileToEdit=
                    new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),FILE_NAME);

            fileToEdit.getParentFile().mkdirs();
            FileOutputStream fos= new FileOutputStream(fileToEdit);
            Writer w = new BufferedWriter(new OutputStreamWriter(fos));
            try {
                w.write(_text);
                w.flush();
                fos.getFD().sync();
            }
            finally {
                w.close();
            }
        }
        catch (IOException e) {
            Log.e(getClass().getSimpleName(), "Exception writing file", e);
        }
    }

    private void load(EditText _edittext) {
        File fileToEdit = new File(getFilesDir(), FILE_NAME);
//        File fileToEdit = new File(getExternalFilesDir(null), FILE_NAME);
//        if (!isExternalStorageReadable()) {
//            return;
//        }
//        File fileToEdit=
//                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),FILE_NAME);

        StringBuilder sb = new StringBuilder();
        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(fileToEdit));
            try {
                line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        _edittext.setText(sb.toString());
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
