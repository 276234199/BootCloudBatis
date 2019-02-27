package com.casit.entity.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年2月14日 上午10:14:12
*/
@Document(collection = "player")
public class Player implements Serializable{
	

	private static final long serialVersionUID = -4865817758205629600L;
	@Id
    private String  playerId;
	@Field(value="name")
	private String name;
	
	private Integer count;
	
	private List<Movie> favouriteMovies;
	
	private List<String> favouriteCities;
	
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<Movie> getFavouriteMovies() {
		return favouriteMovies;
	}
	public void setFavouriteMovies(List<Movie> favouriteMovies) {
		this.favouriteMovies = favouriteMovies;
	}
	public List<String> getFavouriteCities() {
		return favouriteCities;
	}
	public void setFavouriteCities(List<String> favouriteCities) {
		this.favouriteCities = favouriteCities;
	}
	
	

}
