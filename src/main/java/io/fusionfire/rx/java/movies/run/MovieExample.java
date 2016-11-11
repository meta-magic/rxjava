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
package io.fusionfire.rx.java.movies.run;

import rx.Observable;
import rx.schedulers.Schedulers;

import io.fusionfire.rx.java.movies.core.RecommendationEngineObservable;
import io.fusionfire.rx.java.movies.core.RecommendationObserver;
import io.fusionfire.rx.java.movies.pojos.MovieTitle;

public class MovieExample {

	/**
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		MovieExample movie = new MovieExample();
		
		System.out.println("\nRxJava> Starting Movies Async Test Suite...................");
		System.out.println("Movie Codes > AC=Action, SF=SciFi, DR=Drama, RO=Romantic");
		
		System.out.println("\nRxJava> Starting Testing U1..................");
		movie.movieRecommendations();
		System.out.println("RxJava> Tests Scheduled for U1...............");
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}

		int rating = 6;
		int take = 5;
		System.out.println("\nRxJava> Starting Testing U2..................");
		System.out.println("RxJava> User Suggestions Rating > "+rating+" Suggest "+take+" Movies");
		movie.filterSortFlatMap(rating, take);
		System.out.println("RxJava> Tests Scheduled for U2...............");
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
		}

		
		System.out.println("\nRxJava> Movies Async Test Suite Complete..........");
	}

	/**
	 * Show Movie Recommendations
	 */
	public void movieRecommendations() {
		RecommendationObserver<MovieTitle> 
					user = recommendationObserver("U1", 5);
		Observable<MovieTitle> movies = recommendationObservable();
		
		movies
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
					movie -> user.onNext(movie),
					throwable -> user.onError(throwable),
					() -> user.onCompleted()
				);
	}
	
	/**
	 * Get Suggested movies / Sort / Filter / Take (n)
	 * Sorts the Movie based user Rating
	 * 
	 * @param _rating User movie Rating
	 * @param _take No: of movies the user wants at a time
	 */
	public void filterSortFlatMap(int _rating, int _take) {
		RecommendationObserver<MovieTitle> 
					user = recommendationObserver("U2", _rating);
		Observable<MovieTitle> movies = recommendationObservable();
		
		movies
			.filter(user.ratingFilter())
			.toSortedList()
			.flatMap(list -> Observable.from(list)) 
			.take(_take)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
					movie -> user.onNext(movie),
					throwable -> user.onError(throwable),
					() -> user.onCompleted()
				);
	}
	
	private RecommendationObserver<MovieTitle> recommendationObserver(String _id, Integer _rating) {
		return new RecommendationObserver<MovieTitle>(_id, _rating);
	}	
	
	/**
	 * Returns Movie Observable
	 * @return
	 */
	private Observable<MovieTitle> recommendationObservable() {
		RecommendationEngineObservable.initialize();
		return RecommendationEngineObservable.createMovieObservable();
	}
}
