package com.example.shadabnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText editText;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        editText = findViewById(R.id.editText);

        //fetch data that is passed from Mainactivity
        Intent intent = getIntent();



        //accessing the data using keys and values
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1)
        {
            editText.setText(MainActivity.notes.get(noteId));
        }
        else {

            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                MainActivity.notes.set(noteId,String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                //Creating Object of SharedPreferences to store data in the phone
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }
}