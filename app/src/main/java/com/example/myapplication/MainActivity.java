package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private EditText editText;
    private Button btn_save;
    private ArrayList<String> list = new ArrayList<>();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter();
        manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        editText = findViewById(R.id.ed_search);
        loading();
        addListenerChange();
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(editText.getText().toString());
                adapter.update(list);
            }
        });
    }

    public void addListenerChange() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s = s.toString().toLowerCase();
                ArrayList<String> stringArrayList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    String string = list.get(i).toLowerCase();
                    if (string.contains(s)) {
                        stringArrayList.add(list.get(i));
                    }
                    adapter.update(stringArrayList);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void download() {
        SharedPreferences.Editor editor = getSharedPreferences("share", MODE_PRIVATE).edit();
        editor.putString("text", editText.getText().toString());
        Set<String> stringSet = new HashSet();
        stringSet.addAll(list);
        editor.putStringSet("array", stringSet);
        editor.apply();
    }

    public void loading() {
        preferences = getSharedPreferences("share", MODE_PRIVATE);
        String text = preferences.getString("text", "");
        editText.setText(text);
        Set<String> set = new HashSet();
        set = preferences.getStringSet("array", set);
        list.addAll(set);
        adapter.update(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        download();
    }
}
