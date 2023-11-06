package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.model.AlimentModel
import com.example.myapplication.databinding.DeleteBinding

class DeleteActivity : ComponentActivity() {
    lateinit var deleteBinding: DeleteBinding
    lateinit var initialAliment: AlimentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        deleteBinding = DeleteBinding.inflate(layoutInflater)
        setContentView(deleteBinding.root)

        val bundle = intent.getBundleExtra("alimentBundle")
        val position = bundle?.getInt("position")

        if (position != null) {
            deleteBinding.alimentId.text = "Delete aliment at position: $position"

            deleteBinding.yesButton.setOnClickListener {
                val resultBundle = Bundle()
                resultBundle.putInt("toDelete", position)
                val resultIntent = Intent()
                resultIntent.putExtra("deleteResponse", resultBundle)
                setResult(RESULT_OK, resultIntent)
                finish()
            }

            deleteBinding.noButton.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}
