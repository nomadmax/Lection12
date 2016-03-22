package com.nomadmax.lection12;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Max on 20.03.16.
 */
public class PreferenceContentsFragment extends Fragment {
    private TextView checkbox;
    private TextView ringtone;
    private TextView checkbox2;
    private TextView text;
    private TextView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.content, parent, false);

        checkbox=(TextView)result.findViewById(R.id.checkbox);
        ringtone=(TextView)result.findViewById(R.id.ringtone);
        checkbox2=(TextView)result.findViewById(R.id.checkbox2);
        text=(TextView)result.findViewById(R.id.text);
        list=(TextView)result.findViewById(R.id.list);

        return(result);
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs=
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        checkbox.setText(Boolean.valueOf(prefs.getBoolean("checkbox", false)).toString());
        ringtone.setText(prefs.getString("ringtone", "<unset>"));
        checkbox2.setText(Boolean.valueOf(prefs.getBoolean("checkbox2", false)).toString());
        text.setText(prefs.getString("text", "<unset>"));
        list.setText(prefs.getString("list", "<unset>"));
    }
}