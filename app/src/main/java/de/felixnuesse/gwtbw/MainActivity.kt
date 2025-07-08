package de.felixnuesse.gwtbw

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.felixnuesse.gwtbw.databinding.ActivityMainBinding
import java.nio.charset.StandardCharsets


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mId = 0
    private lateinit var mComic: Comics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        loadComic()
        setComic()

        binding.bNext.setOnClickListener {
            if(mId<mComic.comics.size-1){
                mId++
            }
            setComic()
        }

        binding.bPrev.setOnClickListener {
            if(mId>0){
                mId--
            }
            setComic()
        }

        binding.bFirst.setOnClickListener {
            mId = 0
            setComic()
        }

        binding.bLast.setOnClickListener {
            mId = mComic.comics.size-1
            setComic()
        }

    }

    private fun loadComic() {
        var metadata = assets.open("metadata.json")
        // todo: api
        var comicMedata = String(metadata.readAllBytes(), StandardCharsets.UTF_8);
        mComic = Comics.fromJSON(comicMedata)
    }

    private fun setComic() {
        setComic(mId)
    }

    private fun setComic(id: Int) {
        val comic = mComic.comics[id]
        binding.title.text = comic.title
        binding.image.setImageDrawable(Drawable.createFromStream(assets.open("comics/${comic.img}"), comic.title))
    }
}