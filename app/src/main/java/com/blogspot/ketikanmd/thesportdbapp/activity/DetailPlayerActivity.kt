package com.blogspot.ketikanmd.thesportdbapp.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.blogspot.ketikanmd.thesportdbapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.ctx

class DetailPlayerActivity : AppCompatActivity() {


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val i = intent
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        actionBar?.title = i.getStringExtra("strPlayer")
        ctx.let {
            val playerImage = findViewById(R.id.imgPlayer) as ImageView
            Picasso.get().load(i.getStringExtra("playerImage")).into(playerImage)
            playerName.text = i.getStringExtra("strPlayer")
            playerHeight.text = i.getStringExtra("strHeight")
            playerWeight.text = i.getStringExtra("strWeight")
            playerPositionDetail.text = i.getStringExtra("strPosition")
            playerDescription.text = i.getStringExtra("strDescriptionEN")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
