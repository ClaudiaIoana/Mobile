package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.model.AlimentModel
import com.example.myapplication.databinding.EditBinding
import java.time.LocalDate

class EditActivity : ComponentActivity() {
    private lateinit var editBinding: EditBinding
    private lateinit var initialAliment: AlimentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = EditBinding.inflate(layoutInflater)
        setContentView(editBinding.root)

        val bundle = intent.getBundleExtra("alimentBundle")
        val initialAliment = bundle?.getSerializable("aliment", AlimentModel::class.java) as? AlimentModel
        if (initialAliment != null) {
            editBinding.name.setText(initialAliment.name)
            editBinding.buyDate.setText(initialAliment.buy_date.toString())
            editBinding.expirationDate.setText(initialAliment.expiration_date.toString())
            editBinding.alimentQuantity.setText(initialAliment.aliment_quantity.toString())
            editBinding.status.setText(initialAliment.status.toString())

            editBinding.editButton.setOnClickListener {
                if (checkInputs()) {
                    val updatedAliment = AlimentModel(
                        name = editBinding.name.text.toString(),
                        buy_date = LocalDate.parse(editBinding.buyDate.text.toString()),
                        expiration_date = LocalDate.parse(editBinding.expirationDate.text.toString()),
                        aliment_quantity = editBinding.alimentQuantity.text.toString().toFloat(),
                        status = editBinding.status.text.toString()
                    )

                    val resultIntent = Intent()
                    val resultBundle = Bundle()
                    resultBundle.putSerializable("updatedAliment", updatedAliment)
                    resultBundle.putInt("position", bundle.getInt("position")) // Include the position in the result
                    resultIntent.putExtra("updateResponse", resultBundle)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }

    private fun checkInputs(): Boolean {
        if (
            editBinding.status.text.isEmpty() ||
            editBinding.alimentQuantity.text.isEmpty()
        ) {
            return false
        }

        return true
    }
}
