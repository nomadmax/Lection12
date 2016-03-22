package com.nomadmax.lection12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button mRawButton;
    private Button mAssetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        mTextView = (TextView) findViewById(R.id.text_view);
        mRawButton = (Button) findViewById(R.id.load_raw);
        mAssetButton = (Button) findViewById(R.id.load_asset);
        mRawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRawText(mTextView);
            }
        });
        mAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAssetText(mTextView);
            }
        });
    }

    private void loadRawText(TextView _textView) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = getResources().openRawResource(R.raw.loren_ipsum);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
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
        _textView.setText(sb.toString());
    }

    private void loadAssetText(TextView _textView) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open("asset_text.txt");
//            inputStream = getResources().getAssets().open("some_asset_folder/asset_text_in_folder.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
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
        _textView.setText(sb.toString());
    }
}
