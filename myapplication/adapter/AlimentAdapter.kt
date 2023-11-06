package com.example.myapplication.adapter


import androidx.activity.result.ActivityResultLauncher
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.MainActivity
import com.example.myapplication.activity.DeleteActivity
import com.example.myapplication.activity.EditActivity
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.model.AlimentModel

class AlimentAdapter (
    private val context: MainActivity,
    private val aliments: MutableList<AlimentModel>,
    private val editActivityLauncher: ActivityResultLauncher<Intent>,
    private val deleteActivityLauncher: ActivityResultLauncher<Intent>
): RecyclerView.Adapter<AlimentAdapter.AlimentViewHolder>(){

    inner class AlimentViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(aliment: AlimentModel, position: Int){
            itemBinding.description.text =
                (position + 1).toString() + ". Name: " + aliment.name + " | Buy Date: " +
                        aliment.buy_date.toString() + " | Expiration Date: " +
                        aliment.expiration_date.toString() + " | Quantity: " +
                        aliment.aliment_quantity + " | Status: " +
                        aliment.status

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlimentViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: AlimentViewHolder, position: Int) {
        holder.bind(aliments[position], position)

        holder.itemBinding.update.setOnClickListener() {
            val bundle = Bundle();
            val intent = Intent(context, EditActivity::class.java)
            bundle.putSerializable("aliment", aliments[position])
            bundle.putInt("position", position)
            intent.putExtra("alimentBundle", bundle)
            editActivityLauncher.launch(intent)
        }

        holder.itemBinding.delete.setOnClickListener() {
            val bundle = Bundle();
            val intent = Intent(context, DeleteActivity::class.java)
            bundle.putSerializable("aliment", aliments[position])
            bundle.putInt("position", position)
            intent.putExtra("alimentBundle", bundle)
            deleteActivityLauncher.launch(intent)
        }
    }

    override fun getItemCount(): Int {
        return aliments.size
    }

}