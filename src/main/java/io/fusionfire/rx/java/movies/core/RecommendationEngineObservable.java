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

import rx.Observable;
import rx.Subscriber;

import io.fusionfire.rx.java.movies.core.MovieRepository;
import io.fusionfire.rx.java.movies.pojos.MovieAction;
import io.fusionfire.rx.java.movies.pojos.MovieDrama;
import io.fusionfire.rx.java.movies.pojos.MovieRomantic;
import io.fusionfire.rx.java.movies.pojos.MovieSciFi;
import io.fusionfire.rx.java.movies.pojos.MovieTitle;

public class RecommendationEngineObservable {
	
	private static MovieRepository<MovieAction> actionMovies;
	private static MovieRepository<MovieDrama> dramaMovies;
	private static MovieRepository<MovieRomantic> romanticMovies;
	private static MovieRepository<MovieSciFi> sciFiMovies;
	
	private static ArrayList<MovieTitle> suggestedMovies;
	private static boolean initialized = false;
	
	/**
	 * Initialize the Factory with Simulated Data
	 */
	public static void initialize() {
		if(!initialized) {
			getActionMovies();
			getDramaMovies();
			getRomanticMovies();
			getSciFiMovies();
			initialized = true;
		}
	}
	
	/**
	 * Returns the Action Movie Repository
	 * 
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieAction> getActionMovies() {
		return getActionMovies(MovieRepository.MAX_ACTION_MOVIES);
	}
	
	/**
	 * Retrieve Action Movies
	 * 
	 * @param _limit Limit the no: of Action Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieAction> getActionMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(actionMovies == null) {
			actionMovies = new MovieRepository<MovieAction>().createMovieAction(_limit);
		}
		return actionMovies;
	}
	
	/**
	 * Return Drama Movies
	 * 
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieDrama> getDramaMovies() {
		return getDramaMovies(MovieRepository.MAX_DRAMA_MOVIES);
	}
	
	/**
	 * Retrieve Drama Movies
	 * 
	 * @param _limit Limit the no: of Drama Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieDrama> getDramaMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(dramaMovies == null) {
			dramaMovies = new MovieRepository<MovieDrama>().createMovieDrama(_limit);
		}
		return dramaMovies;
	}
	
	/**
	 * Return Romantic Movies
	 * 
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieRomantic> getRomanticMovies() {
		return getRomanticMovies(MovieRepository.MAX_ROMANTIC_MOVIES);
	}
	
	/**
	 * Retrieve Romantic Movies
	 * 
	 * @param _limit Limit the no: of Romantic Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieRomantic> getRomanticMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(romanticMovies == null) {
			romanticMovies = new MovieRepository<MovieRomantic>().createMovieRomantic(_limit);
		}
		return romanticMovies;
	}
	
	/**
	 * Retrieve SciFi Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieSciFi> getSciFiMovies() {
		return getSciFiMovies(MovieRepository.MAX_SCIFI_MOVIES);
	}
	
	/**
	 * Retrieve Sci-Fi Movies
	 * 
	 * @param _limit Limit the no: of Sci-Fi Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private static MovieRepository<MovieSciFi> getSciFiMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(sciFiMovies == null) {
			sciFiMovies = new MovieRepository<MovieSciFi>().createMovieSciFi(_limit);
		}
		return sciFiMovies;
	}
	
	/**
	 * Check if the limit is valid
	 * 
	 * @param _limit Checks a valid limit
	 * @return int Returns a valid limit
	 */
	private static int checkLimit(int _limit) {
		return (_limit < 1) ? 20 : _limit;
	}
	
	/**
	 * Find Movies from Movie Database
	 * 
	 * @return ArrayList Returns a list movies from various categories
	 */
	private static ArrayList<MovieTitle> findMovies() {
		if(suggestedMovies == null) {
			suggestedMovies = new ArrayList<MovieTitle>();
			int totalMovies = (int) (Math.random() * (15 + 1));
			if(totalMovies < 5) {
				totalMovies = 5;
			}
			for(int x=0; x<totalMovies; x++) {
				int flavour = (int) (Math.random() * (4 + 1));
				suggestedMovies.add(getMovie(flavour));
			}
		}
		return suggestedMovies;
	}
	
	/**
	 * Pick Movies based on Movie Flavor (Action, Drama, SciFi etc)
	 * 
	 * @param _flavour Movie flavour like Action, Drama, Sci-Fi etc
	 * @return MovieTitle Returns the Movie Title
	 */
	private static MovieTitle getMovie(int _flavour) {
		MovieTitle movie = null;
		switch (_flavour) {
		case		1:	
			movie = actionMovies
					.list()
					.get((int) (Math.random() * (MovieRepository.MAX_ACTION_MOVIES - 1)));
			break;
		case	 	2:
			movie = dramaMovies
					.list()
					.get((int) (Math.random() * (MovieRepository.MAX_DRAMA_MOVIES - 1)));
			break;
		case		3:
			movie = romanticMovies
					.list()
					.get((int) (Math.random() * (MovieRepository.MAX_ROMANTIC_MOVIES - 1)));
			break;
		case 	4:
			movie = sciFiMovies
					.list()
					.get((int) (Math.random() * (MovieRepository.MAX_SCIFI_MOVIES - 1)));
			break;
		default:
			movie = sciFiMovies
					.list()
					.get((int) (Math.random() * (MovieRepository.MAX_SCIFI_MOVIES - 1)));			
		}
		return movie;
	}
	
	/**
	 * Returns Movie Observable
	 * 
	 * @return Observable Returns the Movie Observable
	 */
	public static Observable<MovieTitle> createMovieObservable () {

		// Movie Repository Call
		final ArrayList<MovieTitle> movies = findMovies();
		
		Observable<MovieTitle> obs = Observable.create(
								new Observable.OnSubscribe<MovieTitle>() {
		    @Override
		    public void call(Subscriber<? super MovieTitle> observer) {
		        try {
		            if (!observer.isUnsubscribed()) {
		            	
		            		movies
			            		.forEach( movie -> observer.onNext(movie) );
		            	
		                observer.onCompleted();
		            }
		        } catch (Exception e) {
		            observer.onError(e);
		        } finally {
		        		observer.unsubscribe();
		        }
		    }
		 } );
		return obs;
	}
}
