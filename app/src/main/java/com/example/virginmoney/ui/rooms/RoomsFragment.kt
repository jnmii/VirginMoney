package com.example.virginmoney.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoney.data.model.rooms.RoomModel
import com.example.virginmoney.data.repository.Repository
import com.example.virginmoney.databinding.FragmentRoomBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    @Inject
    lateinit var repository: Repository

    lateinit var binding: FragmentRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel: RoomsViewModel by viewModels()

        binding = FragmentRoomBinding.inflate(inflater, container, false)

        if (!viewModel.isLoaded) {
            viewModel.getRoomsData()
        }

        viewModel.roomsLiveData.observe(viewLifecycleOwner) { roomsData ->
            loadData(roomsData)
        }

        return binding.root
    }

    private fun loadData(result: List<RoomModel>) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.tvRoomsDirectoryHeading.text =
                "${result.size} Rooms (${result.count { !it.isOccupied }} Available)"

            binding.rvRooms.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = RoomsAdapter(result)
            }
        }
    }
}
