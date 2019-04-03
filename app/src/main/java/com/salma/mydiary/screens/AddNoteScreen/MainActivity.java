package com.salma.mydiary.screens.AddNoteScreen;




import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.salma.mydiary.R;
import com.salma.mydiary.screens.NoteScreen.NoteActivity;

public class MainActivity extends AppCompatActivity {

    Button closeBtn;
    Button nextBtn;
    EditText noteName;
    EditText noteContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        closeBtn=(Button)findViewById(R.id.closeBtn);
        nextBtn=(Button)findViewById(R.id.nextBtn);
        noteName=(EditText)findViewById(R.id.noteName);
        noteContent=(EditText)findViewById(R.id.noteBody);

        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("noteName", noteName.getText().toString());
                intent.putExtra("noteContent", noteContent.getText().toString());
                startActivity(intent);
            }
        });


    }
}
