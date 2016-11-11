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

public class MovieDrama extends AbstractMovie {

	private final int id;
	
	/**
	 * Drama Movie : Base = 35
	 * @param _id Unique ID for the Drama Movie
	 */
	public MovieDrama(int _id) {
		id = _id;
		calculateRating(35);
	}
	
	/**
	 * String Movie ID
	 * @return String
	 */
	public int id() {
		return id;
	}
	@Override
	public String title() {
		return "Movie Title.1";
	}

	@Override
	public String director() {
		return "Movie Director.1";
	}

	@Override
	public String writer() {
		return "Movie Writer";
	}
	
	/**
	 * HashCode Method. Returns ID
	 * 
	 * @return int
	 */
	public int hashCode() {
		return id;
	}
	
	/**
	 * To String Method. Prints ID
	 * 
	 * @return String
	 */
	public String toString() {
		return (id <10) ? "DR:0"+id : "DR:"+id;
	}

	/**
	 * Equals Method
	 */
	public boolean equals(Object o) {
		try {
			MovieDrama a = (MovieDrama)o;
			if(id == a.id) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}


}
