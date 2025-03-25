package com.ignmonlop.repasowot.favModule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ignmonlop.repasowot.R
import com.ignmonlop.repasowot.databinding.ActivityFavBinding
import com.ignmonlop.repasowot.databinding.ActivityMainBinding
import com.ignmonlop.repasowot.mainModule.MainActivity

class FavActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityFavBinding
    private var menuProvider: MenuProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mBinding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createMenu()
    }

    private fun createMenu() {
        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fav, menu)
            }

            //Accion del menu
            // Eliminar la parte de NoActionBar en el archivo de themes
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.action_main -> {
                        startActivity(Intent(this@FavActivity, MainActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
        }
        menuProvider?.let {
            this.addMenuProvider(it)
        }
    }
}