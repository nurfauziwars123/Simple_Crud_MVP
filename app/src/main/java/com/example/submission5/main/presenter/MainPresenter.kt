package com.example.submission5.main.presenter

import android.util.Log
import com.example.submission5.model.action.ResponseAction
import com.example.submission5.model.getdata.ResponseData
import com.example.submission5.network.NetworkConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val mainInterface : MainInterface) {


    fun getData(){

       NetworkConfig.service().getData()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
                if(it.isSuccess!!){
                    mainInterface.onSuccess(it.message ?:"", it.data)
                }
           },{
               mainInterface.onError(it.localizedMessage)
           })

//        NetworkConfig.service().getData().enqueue(object : Callback<ResponseData>{
//            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
//                mainInterface.onError(t.localizedMessage)
//            }
//
//            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
//                if (response.isSuccessful){
//                    val data = response?.body()?.data
//
//                        mainInterface.onSuccess(response.message(), data)
//
//                }else{
//                        mainInterface.onError(response.errorBody().toString())
//                }
//            }
//        })
    }

    fun deleteData(id : String){

        NetworkConfig.service().deleteData(id ?:"")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getData()
                mainInterface.onDelete("berhasil hapus data")
            },{
                mainInterface.onError(it.localizedMessage)
            })

//        val delete = NetworkConfig.service().deleteData(id ?:"")
//            delete.enqueue(object : Callback<ResponseAction>{
//                override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//                    Log.d("error Response Delete", t.localizedMessage)
//                    mainInterface.onError(t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseAction>,
//                    response: Response<ResponseAction>
//                ) {
//                    Log.d("Response Delete", response.message())
//                    getData()
//                    mainInterface.onDelete("berhasil delete data")
//
//                }
//            })
    }

}