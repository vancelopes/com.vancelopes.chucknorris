package com.vancelopes.chucknorris.ui.joke

import android.app.ProgressDialog
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.vancelopes.chucknorris.R
import com.vancelopes.chucknorris.model.Joke
import com.vancelopes.chucknorris.presenter.JokePresenter
import com.vancelopes.chucknorris.ui.BaseFragment
import com.vancelopes.chucknorris.view.JokeView
import kotlinx.android.synthetic.main.fragment_joke.*
import java.util.*

class JokeFragment(private var category: String) : BaseFragment<JokePresenter>(), JokeView {

    companion object { const val TAG = "joke" }

    private lateinit var dialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ProgressDialog(context)
        dialog.setMessage(getString(R.string.loading))
        dialog.show()
        presenter().onViewCreated()
    }

    override fun showJoke(joke: Joke) {
        dialog.dismiss()
        tv_text.text = joke.value
        tv_link.text = String.format(Locale.getDefault(), getString(R.string.link), joke.url)
        tv_link.movementMethod = LinkMovementMethod.getInstance()
        Glide.with(context())
            .load(joke.iconUrl)
            .placeholder(R.drawable.img_placeholder)
            .into(iv_icon)
        container_text.visibility = View.VISIBLE
    }

    override fun showError() { dialog.dismiss(); showDialog(getString(R.string.error)) }

    /// region Init & Destroy
    override fun initPresenter(): JokePresenter { return JokePresenter(this, category) }
    override fun onDestroy() { super.onDestroy(); presenter().onViewDestroyed() }
    /// endregion
}