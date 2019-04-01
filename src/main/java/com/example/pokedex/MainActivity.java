package com.example.pokedex;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView Rview;
    private  MyAdapter adapter;
    private ArrayList<Pokemon> pokemons;
    private EditText searchText;
    private Button search_button;
    private Network UtilN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rview = (RecyclerView) findViewById(R.id.lista_recycler);

        viewBuild();

        Rview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(pokemons);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pokemons = (ArrayList<Pokemon>) UtilN.getResponseFromHttpUrl(searchText.getText().toString());
                    adapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Rview.setAdapter(adapter);

    }

    private void viewBuild() {
        search_button = findViewById(R.id.action_search);
        searchText = findViewById(R.id.search_text);
    }



}
