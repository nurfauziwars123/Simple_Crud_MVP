package com.example.submission5.input.presenter

import android.content.IntentSender
import com.example.submission5.model.action.ResponseAction
import com.example.submission5.network.NetworkConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InputPresenter(val iInterface : InputInterface) {

    fun insert(nama : String, nohp : String, alamat : String) {

        NetworkConfig.service().insertData(nama ?:"", nohp ?:"", alamat ?:"")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()){
                    iInterface.osSuccess("data berhasil ditambahkan")
                }else{
                    iInterface.fieldEmpty("Semua Data Harus Diisi")
                }
            },{
                iInterface.onError(it.localizedMessage)
            })
//        if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()) {
//            val insert = NetworkConfig.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
//            insert.enqueue(object : Callback<ResponseAction> {
//                override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//                    iInterface.onError(t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseAction>,
//                    response: Response<ResponseAction>
//                ) {
//                    iInterface.osSuccess("berhasil input data")
//                }
//            })
//            }else{
//             iInterface.fieldEmpty("Harus Diisi Semua")
//            }
        }

        fun updateData(id: String, nama: String, nohp: String, alamat: String) {

            NetworkConfig.service().updateData(id ?:"", nama?:"", nohp ?:"", alamat ?:"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()){
                        iInterface.osSuccess("data berhasil di update")
                    }else{
                        iInterface.fieldEmpty("Harus Diisi semua")
                    }
                },{

                })
//            val update = NetworkConfig.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
//            update.enqueue(object : Callback<ResponseAction> {
//                override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//                    iInterface.onError(t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseAction>,
//                    response: Response<ResponseAction>
//                ) {
//                    iInterface.osSuccess("berhasil update data")
//                }
//            })
        }
}