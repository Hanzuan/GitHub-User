package com.example.navigationdanapi.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationdanapi.DetailActivity
import com.example.navigationdanapi.databinding.ProfileCardBinding
import com.example.navigationdanapi.response.UserResponse
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val listUser: List<UserResponse>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            ProfileCardBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = listUser[position].login
        Glide.with(viewHolder.itemView)
            .load(listUser[position].avatarUrl)
            .into(viewHolder.image)

        viewHolder.itemView.setOnClickListener{
            val intent = Intent(viewHolder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.username, listUser[position].login)
            intent.putExtra(DetailActivity.avatar, listUser[position].avatarUrl)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listUser.size

    class ViewHolder(binding: ProfileCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.tvUsername
        val image: CircleImageView = binding.imgProfile
    }
}