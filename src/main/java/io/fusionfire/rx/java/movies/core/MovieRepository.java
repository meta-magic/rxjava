/**
 * Copyright (c) 2016 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 
 *   or (per the licensee's choosing)
 
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
*/
package io.fusionfire.rx.java.movies.core;

import java.util.ArrayList;
import java.util.Collection;

import io.fusionfire.rx.java.movies.pojos.MovieAction;
import io.fusionfire.rx.java.movies.pojos.MovieDrama;
import io.fusionfire.rx.java.movies.pojos.MovieRomantic;
import io.fusionfire.rx.java.movies.pojos.MovieSciFi;
import io.fusionfire.rx.java.movies.pojos.MovieTitle;

public class MovieRepository<T extends MovieTitle> {
	
	private ArrayList<MovieTitle> movies;
	
	public final static int MAX_ACTION_MOVIES 	= 200;
	public final static int MAX_DRAMA_MOVIES 	= 140;
	public final static int MAX_ROMANTIC_MOVIES 	= 90;
	public final static int MAX_SCIFI_MOVIES 	= 50;
	
	/**
	 * Initialize the Repository
	 */
	public MovieRepository() {
		movies = new ArrayList<MovieTitle>();
	}

	/**
	 * Creates Action Movies
	 * 
	 * @param _limit Limit the no: of Actions Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieAction(int _limit) {
		movies.clear();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieAction(x));
		}
		return this;
	}
	
	/**
	 * Creates Drama Movies
	 * 
	 * @param _limit Limit the no: of Drama Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieDrama(int _limit) {
		movies.clear();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieDrama(x));
		}
		return this;
	}
	
	/**
	 * Creates SciFi Movies
	 * 
	 * @param _limit Limit the no: of Sci-Fi Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieSciFi(int _limit) {
		movies.clear();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieSciFi(x));
		}
		return this;
	}
	
	/**
	 * Creates Romantic Movies
	 * 
	 * @param _limit Limit the no: of Romantic Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieRomantic(int _limit) {
		movies.clear();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieRomantic(x));
		}
		return this;
	}
	
	/**
	 * Returns Movie Collection
	 * 
	 * @return Collection Returns a collection of Movies
	 */
	public Collection<MovieTitle> collection() {
		return movies;
	}
	
	/**
	 * Returns Movie List
	 * 
	 * @return ArrayList Returns an ArrayList of Movies
	 */
	public ArrayList<MovieTitle> list() {
		return movies;
	}
	
	/**
	 * Returns Movie Iterable
	 * 
	 * @return Iterable Returns an iterable of Movies
	 */
	public Iterable<MovieTitle> iterable() {
		return movies;
	}
}
