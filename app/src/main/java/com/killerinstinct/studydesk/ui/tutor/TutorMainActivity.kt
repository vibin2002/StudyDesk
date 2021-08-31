package com.killerinstinct.studydesk.ui.tutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.ActivityTutorMainBinding
import com.killerinstinct.studydesk.databinding.NavHeaderStudentMainBinding
import com.killerinstinct.studydesk.databinding.NavHeaderTutorMainBinding
import com.killerinstinct.studydesk.ui.EntryActivity

class TutorMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTutorMainBinding
    private val currUser = FirebaseAuth.getInstance().currentUser
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarTutorMain.toolbar)

        val drawerLayout: DrawerLayout = binding.tutorDrawerLayout
        val navView: NavigationView = binding.tutNavView
        navController = findNavController(R.id.nav_host_fragment_content_tutor_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.tutorHomeFragment, R.id.tutorDashBoardFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val headerView = navView.getHeaderView(0)
        val headerBinding = NavHeaderTutorMainBinding.bind(headerView)
        headerBinding.tutorDrawerName.text = currUser?.displayName ?: "StudyDesk"
        headerBinding.tutorDrawerEmail.text = currUser?.email ?: "NULL"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tutor_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_classroom -> {
                navController.navigateUp()
                navController.navigate(R.id.action_tutorHomeFragment_to_addClassroomFragment)
                return true
            }
            R.id.item_assignment -> {
                navController.navigateUp()
                navController.navigate(R.id.action_tutorHomeFragment_to_addAssignmentFragment)
                return true
            }
            R.id.item_test -> {
                navController.navigateUp()
                navController.navigate(R.id.action_tutorHomeFragment_to_addTestFragment)
                return true
            }
            R.id.item_logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,EntryActivity::class.java))
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_tutor_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}