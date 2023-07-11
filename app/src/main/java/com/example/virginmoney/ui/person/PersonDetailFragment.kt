package com.example.virginmoney.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.virginmoney.R
import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.repository.Repository
import com.example.virginmoney.databinding.FragmentPersonDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PersonDetailFragment : Fragment() {

    @Inject
    lateinit var repository: Repository
    lateinit var binding: FragmentPersonDetailBinding
    lateinit var personId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personId = arguments?.getString(("person_id")).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewmodel: PersonDetailViewModel by viewModels<PersonDetailViewModel>()

        binding = FragmentPersonDetailBinding.inflate(inflater, container, false)

        if (!viewmodel.isLoaded) {
            viewmodel.getPeopleData(personId)
        }

        viewmodel.peopleLiveData.observe(viewLifecycleOwner) { peopleData ->
            loadData(peopleData)

        }

//        loadData()


        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_person_detail, container, false)
    }

    private fun loadData(result: PeopleModel) {

        CoroutineScope(Dispatchers.Main).launch {
//            val result = personId?.let { result.getPersonById(it) }

            binding.apply {
                tvPersonDetailFullName.text = result?.firstName + " " + result?.lastName
                tvPersonDetailFirstName.text = result?.firstName
                tvPersonDetailLastName.text = result?.lastName
                tvPersonDetailEmail.text = result?.email
                tvPersonDetailDate.text = result?.createdAt
                tvPersonDetailFavColor.text = result?.favouriteColor
                tvPersonDetailJobTitle.text = result?.jobtitle

                Glide
                    .with(requireContext())
                    .load(result?.avatar)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(civproficepic)

            }

        }
    }

}

