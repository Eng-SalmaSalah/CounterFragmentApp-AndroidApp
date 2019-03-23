package com.salma.mydiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class NoteActivity extends AppCompatActivity {

    TextView noteName;
    TextView noteContent;
    Button closeBtn;
    Button saveInFile;
    Button loadFromFile;
    Button saveInDB;
    Button loadFromDB;
    String myNoteName;
    String myNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        closeBtn = (Button) findViewById(R.id.closeButton);
        saveInFile = (Button) findViewById(R.id.saveFileBtn);
        loadFromFile = (Button) findViewById(R.id.loadFileBtn);
        saveInDB=(Button) findViewById(R.id.saveDBBtn);
        loadFromDB=(Button) findViewById(R.id.loadDBBtn);

        Intent intent = getIntent();
        myNoteName = intent.getStringExtra("noteName");
        myNoteContent = intent.getStringExtra("noteContent");
        noteName = (TextView) findViewById(R.id.myNoteName);
        noteName.setText(myNoteName);
        noteContent = (TextView) findViewById(R.id.myNoteContent);
        noteContent.setText(myNoteContent);

        //close
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        //save in file
        saveInFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fileOutputStream = openFileOutput("UserData", Context.MODE_PRIVATE);
                    DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                    dataOutStream.writeUTF(myNoteName);
                    dataOutStream.writeUTF(myNoteContent);
                    noteName.setText("Note name");
                    noteContent.setText("Note body");
                    Toast.makeText(getApplicationContext(), "Note Saved Successfully", Toast.LENGTH_SHORT).show();
                    dataOutStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //loadFromFile
        loadFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("UserData");
                    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(fileInputStream));
                    String openedNoteName = dataInputStream.readUTF();
                    String openedNoteBody = dataInputStream.readUTF();
                    noteName.setText(openedNoteName);
                    noteContent.setText(openedNoteBody);
                    Toast.makeText(getApplicationContext(), "Note Loaded Successfully", Toast.LENGTH_SHORT).show();
                    dataInputStream.close();
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //save in database
        saveInDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note myNote=new Note(myNoteName,myNoteContent);
                DBAdapter adapter=new DBAdapter (getApplicationContext());
                adapter.insert(myNote);
                noteName.setText("Note name");
                noteContent.setText("Note body");
                Toast.makeText(getApplicationContext(), "Note Saved In database Successfully", Toast.LENGTH_SHORT).show();

            }
        });
        //load from db
        loadFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter adapter=new DBAdapter (getApplicationContext());
                Note retrievedNote = adapter.retrieve(myNoteName);
                noteName.setText(retrievedNote.getNoteName());
                noteContent.setText(retrievedNote.getNoteBody());
                Toast.makeText(getApplicationContext(), "Note Loaded From Database Successfully", Toast.LENGTH_SHORT).show();

            }
        });



    }
}

