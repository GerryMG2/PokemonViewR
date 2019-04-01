package com.example.pokedex;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Network {

    public static final String POKEMON_API_BASE_URL = "https://pokeapi.co/api/v2/";
    public static final String POKEMON_INFO = "pokemon";

    private static final String TAG = Network.class.getSimpleName();

    public URL buildUrl(String pokeID) {
        Uri builtUri = Uri.parse(POKEMON_API_BASE_URL)
                .buildUpon()
                .appendPath(POKEMON_INFO)
                .appendPath(pokeID)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Built URI " + url);

        return url;
    }

    public ArrayList<Pokemon> getResponseFromHttpUrl( String type) throws IOException {

        ArrayList<Pokemon> lista = new ArrayList<>();
        int cont= 1;
        while (true){
            HttpURLConnection urlConnection = (HttpURLConnection) this.buildUrl(Integer.toString(cont)).openConnection();
            try {
                InputStream in = urlConnection.getInputStream();
                Pokemon poke;
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    String  str = scanner.next();
                    JSONObject js = new JSONObject(str);
                    String tipo = (String) js.getJSONObject("types").getJSONObject("1").getJSONObject("type").get("name");
                    String name = (String) js.get("name");
                    poke = new Pokemon(cont,name,tipo);
                    if (tipo == type){
                        lista.add(poke);
                    }
                } else {
                    if (cont == 1000) break;
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            cont++;
        }
        return lista;
    }
}