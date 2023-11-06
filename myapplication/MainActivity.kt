package com.example.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.activity.AddActivity
import com.example.myapplication.adapter.AlimentAdapter
import com.example.myapplication.model.AlimentModel
import com.example.myapplication.databinding.MainListBinding
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    private val aliments = mutableListOf<AlimentModel>()

    private fun initAliments() {
        aliments.add(
            AlimentModel(
                name = "Milk",
                buy_date = LocalDate.parse("2023-10-27"),
                expiration_date = LocalDate.parse("2023-11-27"),
                aliment_quantity = 1f,
                status = "Excellent"
            )
        )
        aliments.add(
            AlimentModel(
                name = "Bread",
                buy_date = LocalDate.parse("2023-10-28"),
                expiration_date = LocalDate.parse("2023-11-28"),
                aliment_quantity = 2f,
                status = "Good"
            )
        )
        aliments.add(
            AlimentModel(
                name = "Apples",
                buy_date = LocalDate.parse("2023-11-01"),
                expiration_date = LocalDate.parse("2023-11-15"),
                aliment_quantity = 5f,
                status = "Excellent"
            )
        )
    }

    lateinit var addActivityLauncher: ActivityResultLauncher<Intent>
    lateinit var editActivityLauncher: ActivityResultLauncher<Intent>
    lateinit var deleteActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAliments()
        val listBinding = MainListBinding.inflate(layoutInflater)

        addActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val aliment =
                    result.data?.getBundleExtra("alimentBundle")?.getSerializable("aliment", AlimentModel::class.java)
                if (aliment != null) {
                    if (aliments.find { repoAliment -> repoAliment.getId() == aliment.getId() } != null) {
                        Toast.makeText(
                            this,
                            "Aliment already exists, so it was not added",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        aliments.add(aliment)
                        listBinding.recyclerView.adapter?.notifyItemInserted(aliments.size - 1)
                    }
                }
            }
        }

        editActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val aliment =
                    result.data?.getBundleExtra("updateResponse")?.getSerializable("updatedAliment", AlimentModel::class.java)
                val position = result.data?.getBundleExtra("updateResponse")?.getInt("position")
                aliments[3].name = aliment.toString()
                aliments[3].status = position.toString()
                listBinding.recyclerView.adapter?.notifyItemChanged(3)

                if (aliment != null && position != null) {
                    aliments[position] = aliment
                    listBinding.recyclerView.adapter?.notifyItemChanged(position)
                }
            }
        }

        deleteActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val bundle = result.data?.getBundleExtra("deleteResponse")!!

                if(bundle.containsKey("toDelete")) {
                    val position = bundle.getInt("toDelete")
                    aliments.removeAt(position)
                    listBinding.recyclerView.adapter?.notifyItemRemoved(position)
                }
            }
        }

        listBinding.addButton.setOnClickListener {
            val intent = Intent(applicationContext, AddActivity::class.java)
            addActivityLauncher.launch(intent)
        }
        val adapter = AlimentAdapter(this, aliments, editActivityLauncher, deleteActivityLauncher)
        listBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        listBinding.recyclerView.adapter = adapter

        setContentView(listBinding.root)
    }
}
