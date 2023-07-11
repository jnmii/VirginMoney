package com.example.virginmoney.ui.rooms

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.virginmoney.R
import com.example.virginmoney.data.model.rooms.RoomModel
import com.example.virginmoney.databinding.ItemRoomBinding

class RoomsAdapter(val roomList: List<RoomModel>) : RecyclerView.Adapter<RoomsAdapter.ViewHolder> (){
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRoomBinding.bind(view)
        fun updateUI(room: RoomModel) {
            binding.apply {
                var prefixNumber = ""
                val convertedNumber = room.id?.toInt()

                // Room Number format ...

                when(convertedNumber) {
                    in 1..9 -> {prefixNumber = "# 00"}
                    in 10..99 -> prefixNumber = "# 0"
                    else -> prefixNumber = "# "
                }



                tvRoomId.text = prefixNumber+room.id
                tvMaxOcc.text = room.maxOccupancy.toString()
                if (room.isOccupied == true)  {
                    tvOccStatus.text = "Occupied"
                    itemRoomLayout.setBackgroundColor(Color.parseColor("#f8f8f8"))
                } else tvOccStatus.text = "Available"

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)


        )
    }

    override fun getItemCount() = roomList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.updateUI(roomList[position])
    }
}
