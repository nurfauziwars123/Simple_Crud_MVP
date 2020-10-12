package com.example.submission5.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.submission5.R
import com.example.submission5.adapter.Adapter_Main
import com.example.submission5.input.view.InputActivity
import com.example.submission5.main.presenter.MainInterface
import com.example.submission5.main.presenter.MainPresenter
import com.example.submission5.model.getdata.DataItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInterface {


    private lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter.getData()

        btn_fab.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.getData()
    }

    override fun onSuccess(msg: String, data: List<DataItem?>?) {
          val adapter = Adapter_Main(data, object  : Adapter_Main.onClickListener{
              override fun detail(item: DataItem?) {
                  val intent = Intent(applicationContext, InputActivity::class.java)
                  intent.putExtra("data", item)
                  startActivity(intent)
              }

              override fun hapus(item: DataItem?) {
                  AlertDialog.Builder(this@MainActivity).apply{
                      setTitle("Hapus Data?")
                      setMessage("Yakin Untuk Menghapus Data?")
                      setPositiveButton("hapus"){dialogInterface, i ->
                          presenter.deleteData(item?.id ?:"")
                          dialogInterface.dismiss()
                      }
                      setNegativeButton("batal"){dialogInterface, i ->
                          dialogInterface.dismiss()
                      }
                  }.show()
              }
          })

        rv_list_item.adapter = adapter

    }



    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDelete(msg: String) {
     Toast.makeText(this, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show()

    }

}
