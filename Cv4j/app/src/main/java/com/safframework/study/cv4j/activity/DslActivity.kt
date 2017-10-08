package com.safframework.study.cv4j.activity

/**
 * Created by tony on 2017/10/8.
 */
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView

import com.cv4j.core.filters.NatureFilter
import com.cv4j.rxjava.cv4j
import com.safframework.study.cv4j.R

class DslActivity : AppCompatActivity() {

    var image: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dsl)

        initViews()
        initData()
    }

    private fun initViews() {

        image = findViewById(R.id.image) as ImageView?
    }

    private fun initData() {

        cv4j {
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.test_io)

            filter = NatureFilter()

            imageView = image
        }
    }
}