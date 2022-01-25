package com.mahidhar.webviewapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mahidhar.webviewapp.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
//        uncomment this line to request permissions
//        setupPermissions()

    }

    fun setupPermissions(){
        //add required permissions in this list
        val permsRequestCode=101;
        val perms = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
        )

        ActivityCompat.requestPermissions(this, perms, permsRequestCode)

//        for (i in 0 until perms.size) {
//            Log.i("dd",i.toString());
//            Log.i("dd",perms[i].toString());
//            println(i);
//            println(perms[i]);
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    perms[i]
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                println("inside "+ perms[i]);
//                ActivityCompat.requestPermissions(this, arrayOf(perms[i]), permsRequestCode)
//            }
//        }
    }

    override fun onBackPressed() {
        var handled = false
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is MainFragment)
                handled = fragment.onBackPressed()
        }

        if(!handled)
            super.onBackPressed()
    }
}