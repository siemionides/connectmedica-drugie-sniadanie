package com.connectmedica.sniadanie.rest;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.connectmedica.sniadanie.rest.json.RelicJsonWrapper;
import com.google.gson.Gson;

public class OtwarteZabytkiClient {

    private static OtwarteZabytkiClient instance;

    private OtwarteZabytkiApi api;

    private OtwarteZabytkiClient(){
//        new
        Gson gson = new Gson();
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("http://otwartezabytki.pl/api/v1")
                .setConverter(new GsonConverter(gson))
                .build();

        api = restAdapter.create(OtwarteZabytkiApi.class);
    }

    public static OtwarteZabytkiClient getInstance(){
        if (instance == null){
            instance = new OtwarteZabytkiClient();
        }
        return instance;
    }

    public void getSideEffects ( String place, String relicName, String from, String to, 
    		Callback<RelicJsonWrapper> cb){
        api.getRelics(place, relicName, from, to, true, cb);
    }


}
