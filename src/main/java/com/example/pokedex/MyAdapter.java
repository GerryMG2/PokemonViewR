package com.example.pokedex;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Pokemon> pokemons;

    public MyAdapter(ArrayList<Pokemon> pokes){
        this.pokemons = pokes;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int i) {
        final Pokemon pokemon = pokemons.get(i);

        holder.name.setText(pokemon.getName());
        holder.type.setText(pokemon.getType());


    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView type;
        public ViewHolder(View item){
            super(item);
            name =  item.findViewById(R.id.tv_pokemon_name);
            type = item.findViewById(R.id.tv_pokemon_type);
        }
    }
}
