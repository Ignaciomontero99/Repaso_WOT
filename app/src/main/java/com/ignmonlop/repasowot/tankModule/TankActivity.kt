package com.ignmonlop.repasowot.tankModule

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ignmonlop.repasowot.R
import com.ignmonlop.repasowot.databinding.ActivityTankBinding
import com.ignmonlop.repasowot.favModule.FavActivity
import com.ignmonlop.repasowot.mainModule.MainActivity

class TankActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityTankBinding
    private var menuProvider: MenuProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mBinding = ActivityTankBinding.inflate(layoutInflater)
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
                menuInflater.inflate(R.menu.menu_common, menu)
            }

            //Accion del menu
            // Eliminar la parte de NoActionBar en el archivo de themes
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.action_main -> {
                        startActivity(Intent(this@TankActivity, MainActivity::class.java))
                        true
                    }
                    R.id.action_fav -> {
                        startActivity(Intent(this@TankActivity, FavActivity::class.java))
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