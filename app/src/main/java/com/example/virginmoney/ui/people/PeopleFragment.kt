
package com.virginmoney.virginmoneydirectory.ui.contact


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.databinding.FragmentPeopleBinding


import com.example.virginmoney.ui.people.PeopleAdapter
import com.example.virginmoney.ui.people.PeopleViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {


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
            viewmodel.getPeopleData()
        }

        viewmodel.peopleLiveData.observe(viewLifecycleOwner) { peopleDta ->

            loadData(peopleDta)
        }
//        loadData()

        return binding.root

    }

    private fun loadData(result: List<PeopleModel>) {
//           val result = repository.getAllPeople()
//           val result = ApiDetails.getAllPeople()
        binding.tvStaffDirectoryHeading.text = result.size.toString() +" Employees"




        binding.rvAllPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PeopleAdapter(result)

        }

    }

}