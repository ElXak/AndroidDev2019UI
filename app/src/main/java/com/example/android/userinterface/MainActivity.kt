package com.example.android.userinterface

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    // older, stable approach of view references
    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // explicitly set the height of layout in physical pixels
//        button1.layoutParams.height = 100

        // convert px into dp. 100f is 100px in float
/*
        val pixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics
        )
        button1.layoutParams.height = pixels.toInt()
*/
        /*
        container = findViewById(R.id.linearLayout)

        // get string from resources
        addTextView(getString(R.string.hello))
        addTextView(getString(R.string.from))
        addTextView(getString(R.string.android))

        button.setOnClickListener {
            // get user input from editText component
            val userName = nameInput.text.toString()
            val password = passwordInput.text.toString()
            val message = getString(R.string.login_message, userName, password)
            // Toast messages are easy way to present info to users, but has down sides
            // Toast message isn't part of your app interface. It's managed independently by framework
            // It is part of notification framework and can be completely suppressed by user unintentionally
            // If user completely stops notifications from your app and they won't see your Toast messages.
//            Toast.makeText(this,"Name: ${userName.toUpperCase()}, Password: $password", Toast.LENGTH_LONG).show()
//            Toast.makeText(this,message, Toast.LENGTH_LONG).show()
            // Snackbar are most reliable with CoordinatorLayout. When you create a Snackbar message
            // you have to pass in a reference, to some view that is either CoordinatorLayout or a
            // child of the CoordinatorLayout.
            // here it references to button which is child of CoordinatorLayout. instead we could
            // reference to CoordinatorLayout itself. User can swipe snackbar.
//            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
            Snackbar.make(it, message, Snackbar.LENGTH_LONG)
            // you can additionally add button and execute some code
                .setAction("Click me", { showAnotherMessage() })
                .show()
        }
        */

        button1.setOnClickListener{
            displayImageAsset("monster01.webp")
        }

        button2.setOnClickListener{
            displayImageAsset("monster02.webp")
        }

        button3.setOnClickListener{
            displayImageAsset("monster03.webp")
        }

    }

    private fun showAnotherMessage() {
        Toast.makeText(this,"You clicked!", Toast.LENGTH_LONG).show()
    }

    private fun displayImageResource(resId: Int) {
        // change ImageView image from resource
        monsterImage.setImageResource(resId)
    }

    private fun displayImageAsset(fileName: String) {
        // assets property of context gets assets directory, then opens file stream
        // any stream that is opened has to be closed. use. does everything for you
        assets.open(fileName).use {
            // takes image into memory
            val drawable = Drawable.createFromStream(it, null)
            monsterImage.setImageDrawable(drawable)
        }
    }

    private fun addTextView(label: String) {
        // TextView constructor receives context: this. This TextView is bound to the current context
        // but it is not actually displayed anywhere yet
/*
        val view = TextView(this)
        view.text = lable
        view.textSize = 28f
        view.setTextColor(Color.parseColor("#FF0000"))
*/
/*
        view.let {
            it.text = lable
            it.textSize = 28f
            it.setTextColor(Color.parseColor("#FF0000"))
        }
*/
        val view = TextView(this).apply {
            this.text = label
            this.textSize = 28f
            this.setTextColor(Color.parseColor("#FF0000"))
        }

        // synthetic binding is compiler based tool that looks at the XML layout file and gets the
        // references you need by view id.
//        linearLayout.addView(view)
        container.addView(view)
    }

}
