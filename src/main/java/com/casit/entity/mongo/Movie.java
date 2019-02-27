package com.casit.entity.mongo;
/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年2月22日 上午10:35:19
*/
public class Movie {
	
	public Integer movieId;
	
	public String movieName;
	
	

	public Movie(Integer movieId, String movieName) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	

}
