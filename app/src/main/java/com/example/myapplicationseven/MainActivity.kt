package com.example.myapplicationseven

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.myapplicationseven.presentation.*
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),
    MainFragment.OnItemAddListener,
    ListFragmentType1.OnItemEditType1Listener,
    ListFragmentType2.OnItemEditType2Listener,
    DetailFragment.OnItemCreateUpdateListener,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    val TYPE_1: String? = "Type 1"
    val TYPE_2: String? = "Type 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // using toolbar as ActionBar

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        switchHamburger()

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            transactionToMainFragment()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if (item.getItemId() == R.id.nav_home) {
            replaceMainFragment()
        }

        if (item.getItemId() == R.id.nav_about) {
            replaceAboutAppFragment()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    //при нажатии кнопки "Назад", сначала должен быть закрыт NavigationView, а затем приложение.
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun switchHamburger(){
        toggle.isDrawerIndicatorEnabled = true // Чтобы получить значок гамбургера, установим индикатор
    }

    private fun switchBack(){
        toggle.isDrawerIndicatorEnabled = false

        toggle.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        toggle.setToolbarNavigationClickListener() {

            onBackPressed()

            switchHamburger()
        }
    }

    private fun replaceMainFragment(){
       transactionToMainFragment()
    }

    private fun replaceAboutAppFragment(){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentAboutApp: AboutAppFragment = AboutAppFragment.newInstance() // newInstance
        fragmentTransaction.replace(R.id.container, fragmentAboutApp) // контейнер в активити
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        switchHamburger()
    }

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `OnItemAddListener` interface methods
    override fun onAddItem(add: Bundle){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentDetail: DetailFragment = DetailFragment.newInstance(
            add.getString("Action"),
            add.getInt("Id"),
            null,
            null,
            null,
            null,
            null,
            null
        )
        fragmentTransaction.replace(R.id.container, fragmentDetail) // контейнер в активити
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        switchBack()
    }

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `OnItemEditType1Listener` interface methods
    override fun onEditItemType1(edit: Bundle){
        onUpdateItem(edit)
    }

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `OnItemEditType2Listener` interface methods
    override fun onEditItemType2(edit: Bundle){
        onUpdateItem(edit)
    }

    fun onUpdateItem(edit: Bundle){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentDetail: DetailFragment = DetailFragment.newInstance(
            edit.getString("Action"),
            edit.getInt("Id"),
            edit.getString("Title"),
            edit.getString("Description"),
            edit.getString("Priority"),
            edit.getString("Type"),
            edit.getString("Count"),
            edit.getString("Period")
        )
        fragmentTransaction.replace(R.id.container, fragmentDetail)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        switchBack()
    }

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `OnItemCreateUpdateListener` interface methods
    override fun onSaveItem(){
        transactionToMainFragment()
    }

    private fun transactionToMainFragment(){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentMain: MainFragment = MainFragment.newInstance(TYPE_1, TYPE_2)
        fragmentTransaction.replace(R.id.container, fragmentMain) // контейнер в активити
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        switchHamburger()
    }

}