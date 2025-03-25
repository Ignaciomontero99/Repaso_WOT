package com.ignmonlop.repasowot.mainModule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignmonlop.repasowot.R
import com.ignmonlop.repasowot.api.Tank
import com.ignmonlop.repasowot.api.common.Constants
import com.ignmonlop.repasowot.api.service.ZoneService
import com.ignmonlop.repasowot.databinding.ActivityMainBinding
import com.ignmonlop.repasowot.favModule.FavActivity
import com.ignmonlop.repasowot.mainModule.adapter.TanksAdapter
import com.ignmonlop.repasowot.mainModule.adapter.ZonesAdapter
import com.ignmonlop.repasowot.tankModule.TankActivity
import com.ignmonlop.repasowot.zoneModule.ZoneActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var menuProvider: MenuProvider? = null
    private lateinit var zonesAdapter: ZonesAdapter
    private lateinit var tankAdapter: TanksAdapter
    private lateinit var zonesLinearLayoutManager: LinearLayoutManager
    private lateinit var tanksLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setTitle("World of Tanks")
        createMenu()

        setUpRecyclerViewsZones()
        setUpRecyclerViewsTanks()

    }

    private fun setUpRecyclerViewsZones() {
        zonesAdapter = ZonesAdapter()
        zonesLinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false)

        mBinding.rvZones.apply{
            setHasFixedSize(true)
            layoutManager = zonesLinearLayoutManager
            adapter = zonesAdapter
        }
        getZones()
    }

    private fun setUpRecyclerViewsTanks() {
        tankAdapter = TanksAdapter()
        tanksLinearLayoutManager = LinearLayoutManager(this)

        mBinding.rvTanks.apply{
            setHasFixedSize(true)
            layoutManager = tanksLinearLayoutManager
            adapter = tankAdapter
        }
    }

    private fun createMenu() {
        menuProvider = object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }
            //Accion del menu
            // Eliminar la parte de NoActionBar en el archivo de themes
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.action_tank -> {
                        startActivity(Intent(this@MainActivity, TankActivity::class.java))
                        true
                    }
                    R.id.action_fav -> {
                        startActivity(Intent(this@MainActivity, FavActivity::class.java))
                        true
                    }
                    R.id.action_zone -> {
                        startActivity(Intent(this@MainActivity, ZoneActivity::class.java))
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

    private fun getZones(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ZoneService::class.java)

        lifecycleScope.launch(Dispatchers.IO){
            try{
                val zonesList = service.getZones()
                withContext(Dispatchers.Main){
                    Log.i("Zonas", zonesList.toString())
                    zonesAdapter.submitList(zonesList)
                }
            }catch (e: Exception){
                Log.e("zonasError", e.message.toString())
                (e as? HttpException)?. let{

                }
            }
        }
    }

    private fun getTanks(){

    }
}