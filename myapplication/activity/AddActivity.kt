package com.example.myapplication.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.AddBinding
import java.time.LocalDate
import android.widget.Toast
import com.example.myapplication.model.AlimentModel
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class AddActivity: ComponentActivity() {
    lateinit var addBinding: AddBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        addBinding = AddBinding.inflate(layoutInflater)
        setContentView(addBinding.root)

        addBinding.addButton.setOnClickListener {
            if (checkInputs()) {
                val aliment = AlimentModel(
                    name = addBinding.name.text.toString(),
                    buy_date = LocalDate.parse(addBinding.buyDate.text.toString()),
                    expiration_date = LocalDate.parse(addBinding.expirationDate.text.toString()),
                    aliment_quantity = addBinding.alimentQuantity.text.toString().toFloat(),
                    status = addBinding.status.text.toString()
                )
                val bundle = Bundle()
                bundle.putSerializable("aliment", aliment)
                intent.putExtra("alimentBundle", bundle)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "All fields must be completed, and the date must have the format yyyy-MM-dd!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun checkInputs(): Boolean {
        val dateChecker = addBinding.buyDate.text.toString()
        val dateChecker2 = addBinding.expirationDate.text.toString()
        return !addBinding.name.text.isNullOrEmpty() &&
                !addBinding.alimentQuantity.text.isNullOrEmpty() &&
                !addBinding.status.text.isNullOrEmpty() &&
                isValidDate(dateChecker) && isValidDate(dateChecker2)
    }

    private fun isValidDate(stringToTest: String): Boolean {
        return try {
            LocalDate.parse(stringToTest, DateTimeFormatter.ISO_LOCAL_DATE)
            true
        } catch (pe: DateTimeParseException) {
            false
        }
    }
}