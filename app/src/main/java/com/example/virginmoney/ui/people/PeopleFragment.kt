
package com.virginmoney.virginmoneydirectory.ui.contact


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.example.virginmoney.R
import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.remote.ApiDetails
import com.example.virginmoney.databinding.FragmentPeopleBinding


import com.example.virginmoney.databinding.FragmentPersonDetailBinding
import com.example.virginmoney.ui.people.PeopleAdapter
import com.example.virginmoney.ui.people.PeopleViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    //    @Inject
//    lateinit var repository : Repository
    lateinit var binding: FragmentPeopleBinding
    val currentUser = Firebase.auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val viewmodel : PeopleViewModel by viewModels<PeopleViewModel>()
        // Inflate the layout for this fragment
        binding = FragmentPeopleBinding.inflate(layoutInflater, container, false)

        if (!viewmodel.isLoaded) {
            viewmodel.getAllPeopleData()
        }

        viewmodel.peopleLiveData.observe(viewLifecycleOwner) { peopleDta ->

            loadData(peopleDta)
        }
//        loadData()

        return binding.root

    }

    private fun loadData(result: List<PeopleModel>) {
           val result = repository.getAllPeople()
           val result = ApiDetails.getAllPeople()
        binding.tvStaffDirectoryHeading.text = result.size.toString() +" Employees"
        binding..text = (currentUser?.displayName ?: currentUser?.email).toString()



        binding.rvPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PeopleAdapter(result)

        }

    }

}