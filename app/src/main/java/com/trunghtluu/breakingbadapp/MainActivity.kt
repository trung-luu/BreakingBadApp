package com.trunghtluu.breakingbadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.trunghtluu.breakingbadapp.ui.detail.CharacterDetailFragment
import com.trunghtluu.breakingbadapp.ui.list.CharacterListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit{
                add<CharacterListFragment>(R.id.fragment_container_view, "list")
            }
        }
    }

    fun replaceFragment() {
        supportFragmentManager.commit{
            replace<CharacterDetailFragment>(R.id.fragment_container_view, "detail")
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        Log.d("FAIL", "FAIL")
        supportFragmentManager?.popBackStackImmediate()
    }
}