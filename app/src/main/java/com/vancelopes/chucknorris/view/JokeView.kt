package com.vancelopes.chucknorris.view

import com.vancelopes.chucknorris.model.Joke

interface JokeView: BaseView { fun showJoke(joke: Joke) }