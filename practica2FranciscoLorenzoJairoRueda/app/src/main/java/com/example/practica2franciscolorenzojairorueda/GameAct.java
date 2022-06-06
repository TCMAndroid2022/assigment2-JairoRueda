package com.example.practica2franciscolorenzojairorueda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class GameAct extends AppCompatActivity  {

    private int Size;
    private String word[];
    private String hits[];
    private String r="";
    private String i="";
    private int punt = 0;
    private int enteredLetters=0;
    private String fWord="";
    private TextView txtView;
    private EditText etText;
    private TextView nicktxt;
    private TextView enteredWord;
    private TextView finalPoints;
    private TextView end;
    private Button cletter;
    private Button cWord;
    private GameViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        String text = getIntent().getStringExtra("Word");
        String nick = getIntent().getStringExtra("NickName");
        etText = findViewById(R.id.letter);
        end = findViewById(R.id.Finish);
        finalPoints = findViewById(R.id.points);
        txtView = findViewById(R.id.randomWord);
        nicktxt = findViewById(R.id.textNick);
        cletter = findViewById(R.id.checkLetter);
        cWord = findViewById(R.id.checkWord);

        word = new String[text.length()];
        hits = new String[text.length()];
        Size = text.length();

        for (int r = 0; r< Size;r++) {
            hits[r] = " - ";
            word[r] = text.substring(r,r+1);
            System.out.println(word[r]);
            i = i+" - ";

        }
        txtView.setText(i);
        cletter.setOnClickListener(v->{
            String letter = etText.getText().toString();
            etText.setText("");
            if (letter != null || !(letter.equals(""))) {
                for (int r = 0; r<Size;r++) {
                    if (word[r].equalsIgnoreCase(letter)) {
                        hits[r] = letter;
                        this.enteredLetters++;

                    }
                }
                for (int z = 0; z < Size;z++) {
                    r = r + hits[z];
                }
                for (int r = 0; r<Size; r++) {
                    System.out.print(word[r]);
                }

                txtView.setText(r.toUpperCase(Locale.ROOT));
                r = "";

                if (enteredLetters == Size){
                    checkWord();
                }
            }
        });

        cWord.setOnClickListener(v->{
            checkWord();
        });
        nicktxt.setText(nick);
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    public boolean checkW(String []w, String ent){
        String word = "";
        for(int i=0;i< w.length;i++){
            word += w[i];
        }
        return word.equalsIgnoreCase(ent);
    }
    public void checkWord (){
        String ent = etText.getText().toString();
        if(enteredLetters == Size||checkW(word,ent)){
            int subs = Size - enteredLetters;
            int division = subs/Size;
            int result = division*10;
            punt = result;

        } else{
            punt = 0;
        }
        for (int i = 0; i< Size;i++) {
            fWord+=word[i];
        }
        end.setText("Game finished");

        txtView.setText(fWord.toUpperCase());

        finalPoints.setText("You have earned "+punt+" POINTS");
        String nick = nicktxt.getText().toString();

        Game newGame = new Game(nick, punt);

        viewModel.insert(newGame);

    }






}
