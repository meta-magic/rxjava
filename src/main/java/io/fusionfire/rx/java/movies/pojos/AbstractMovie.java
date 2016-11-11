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
package io.fusionfire.rx.java.movies.pojos;

public abstract class AbstractMovie implements MovieTitle {
	
	private double rating = 0;
	private int year = 1970;
	private String[] actors = {"Actor.1", "Actor.2" };
	
	/**
	 * Calculates the Movie Rating
	 * 
	 * @param _base	Set the base rating for the movie
	 * @return double Returns the rating of the Movie
	 */
	protected double calculateRating(int _base) {
		rating = (int) (Math.random() * (100 + 1));
		if(rating < _base) {
			rating += _base;
		}
		year += (rating / 4);
		rating = rating / 10;
		return rating;
	}

	/**
	 * Returns the Movie released Year
	 * 
	 * @return int
	 */
	public int year() {
		return year;
	}
	
	/**
	 * Returns Movie Rating
	 * @return int
	 */
	public double rating() {
		return rating;
	}
	
	/**
	 * Returns the Lead Actors
	 * @return String[]
	 */
	public String[] actors() {
		return actors;
	}
	
	/**
	 * Compares the Movie Rating (Descending Order)
	 */
	@Override
	public int compareTo(MovieTitle _movie) {
		return Double.compare(_movie.rating(), this.rating);
	}
	/**
	 * Returns the Movie Tag
	 * 
	 * @return String
	 */
	public String getMovieTag() {
		StringBuilder d = new StringBuilder();
		d.append(this).append(" [");
		d.append(year()).append(":");
		d.append(rating()).append("]");
		return d.toString();
	}
}
