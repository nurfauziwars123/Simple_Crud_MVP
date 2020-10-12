package com.example.submission5.input.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.submission5.R
import com.example.submission5.input.presenter.InputInterface
import com.example.submission5.input.presenter.InputPresenter
import com.example.submission5.model.getdata.DataItem
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity(), InputInterface {


    private lateinit var presenter : InputPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)


        btn_batal.setOnClickListener{
            finish()
        }

        presenter = InputPresenter(this)

        val getDataIntent = intent.getParcelableExtra<DataItem>("data")
        val id = getDataIntent?.id

    if(getDataIntent != null){
        et_nama.setText(getDataIntent.nama)
        et_nohp.setText(getDataIntent.nohp)
        et_alamat.setText(getDataIntent.alamat)

        btn_simpan.setText("Update")
    }


        when(btn_simpan.text){
            "Update" -> {
                btn_simpan.setOnClickListener{
                    presenter.updateData(id!!.toString(), et_nama.text.toString(), et_nohp.text.toString(), et_alamat.text.toString())

                }
                }else -> {

                btn_simpan.setOnClickListener {
                        presenter.insert(et_nama.text.toString(), et_nohp.toString(), et_alamat.text.toString())

                }
            }
        }
    }

    override fun osSuccess(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        finish()

    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun fieldEmpty(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
