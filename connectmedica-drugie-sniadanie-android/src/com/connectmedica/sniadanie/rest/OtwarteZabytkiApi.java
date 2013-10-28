package com.connectmedica.sniadanie.rest;

import com.connectmedica.sniadanie.rest.json.RelicJsonWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface OtwarteZabytkiApi {

	@GET("/relics.json")
    void getRelics(@Query("place") String place, @Query("query") String relicName, 
    		@Query("from") String from,  @Query("to") String to, @Query("has_photos") boolean hasPhotos,
    		Callback<RelicJsonWrapper> cb);

}
