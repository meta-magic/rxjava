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

import rx.Subscriber;
import rx.functions.Func1;

import io.fusionfire.rx.java.movies.pojos.MovieTitle;

public class RecommendationObserver<T extends MovieTitle> 
					extends Subscriber<MovieTitle> implements Func1<T, Boolean> {

	/**
	 * on Next is called by Observable to Process the Movie
	 * 
	 * @param _movieTitle Process the Movie Title
	 */
	@Override
	public void onNext(MovieTitle _movieTitle) {
		if(start) {
			startTime = System.currentTimeMillis();
			start = false;
		}
		try {
			// Simulating Latency
			processMovie(_movieTitle);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inform the Movie Observer if the Processing is done.
	 */
	@Override
	public void onCompleted() {
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		double seconds = totalTime / 1000;
		System.out.println("\nRxJava-"+pid+"> User Movie Suggestion Task Completed - Total Time in Seconds = "+seconds);
		start = true;
	}

	/**
	 * Throws the error while processing the movie
	 * 
	 * @param t Error while processing the movie
	 */
	@Override
	public void onError(Throwable t) {
		System.err.println("\nRxJava-"+pid+"> User Movie Suggestion : Whoops Error!! = "+t.getMessage());
	}

	/**
	 * Returns true if the Movie Rating is greater
	 * 
	 * @param _movieTitle Check the movie rating
	 * @return Boolean Returns true if the Movie Rating is greater
	 */
	@Override
	public Boolean call(T _movieTitle) {
		return (_movieTitle.rating() > rating);
	}
	
	/**
	 * Returns the Movie Rating Filter
	 * 
	 * @return Func1 Returns the Movie Rating Filter
	 */
	public Func1<T, Boolean> ratingFilter() {
		return this;
	}
	
	/**
	 * Process the movie
	 * 
	 * @param _movieTitle Observable calls this method to Process the movie
	 */
	private void processMovie(final MovieTitle _movieTitle) {
		counter++;
		if(counter > 3) {
			counter = 1;
			System.out.println("");
		}
		System.err.print("["+pid+"]="+_movieTitle.getMovieTag()+" ");
	}
	private boolean start = true;
	
	private long startTime = 0;
	private long endTime = 0;
	private long totalTime = 0;
	
	private int counter = 0;
	
	private String pid = "";
	
	// Filter Values
	private int rating = 0;
	
	/**
	 * User Movie Suggestion Initialized with Unique ID
	 * 
	 * @param _id Sets a unique ID for the Observer 
	 */
	public RecommendationObserver(String _id) {
		this(_id, 0);
	}
	
	/**
	 * User Movie Suggestion Initialized with Unique ID
	 * With Criteria to Filter
	 * 
	 * @param _id Sets a unique ID for the Observer 
	 * @param _rating Sets the base rating of the movie
	 */
	public RecommendationObserver(String _id, Integer _rating) {
		pid = _id;
		if(_rating > 0) {
			rating = _rating;
		}
		System.out.println("RxJava> User Suggestion (Observer) Initialized with ID = "+pid);
	}	

}
