package com.alexpetrov.dotaheroes.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexpetrov.dotaheroes.data.HeroModel
import com.alexpetrov.dotaheroes.databinding.FragmentHeroBinding
import com.alexpetrov.dotaheroes.ui.activity.MainActivity.Companion.heroInfo
import com.alexpetrov.dotaheroes.ui.activity.SecondActivity
import com.alexpetrov.dotaheroes.ui.adapter.HeroAdapter
import com.alexpetrov.dotaheroes.ui.interfaces.Listener
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import java.io.*

class FragmentHero : Fragment(), Listener {

    private var json: String = ""
    lateinit var binding: FragmentHeroBinding
    private val urlHeroModel = "https://api.opendota.com/api/heroStats"
    private val okHttpClient = OkHttpClient()
    private val file: String = "DotsHero"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroBinding
            .inflate(inflater, container, false)

        readJson()
        initRecyclerView()

        return binding.root
    }

    private fun getHeroModel() {
        val request = Request.Builder()
            .url(urlHeroModel)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                json = response.body.string()
                val moshi = Moshi.Builder().build()
                val listType = Types.newParameterizedType(List::class.java, HeroModel::class.java)
                val adapter: JsonAdapter<List<HeroModel>> = moshi.adapter(listType)
                heroInfo = adapter.fromJson(json)!!
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }

    private fun saveJson() {
        try {
            val bw = BufferedWriter(
                OutputStreamWriter(
                    activity?.openFileOutput(file, AppCompatActivity.MODE_PRIVATE)
                )
            )
            bw.write(json)
            bw.close()
            Log.d("MY_LOG", "File saved")

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readJson() {
        try {
            val br = BufferedReader(
                InputStreamReader(
                    activity?.openFileInput(file)
                )
            )
            var str = ""
            str = br.readLine()
            if ((str != null) || (str != "")) {
                val moshi = Moshi.Builder().build()
                val listType = Types.newParameterizedType(List::class.java, HeroModel::class.java)
                val adapter: JsonAdapter<List<HeroModel>> = moshi.adapter(listType)
                heroInfo = adapter.fromJson(str)!!

            } else {
                getHeroModel()
                while (heroInfo.isEmpty()) {
                    continue
                }
                saveJson()
            }
        } catch (e: FileNotFoundException) {
            getHeroModel()
            while (heroInfo.isEmpty()) {
                continue
            }
            saveJson()
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun initRecyclerView() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = HeroAdapter(this@FragmentHero, heroInfo)
    }

    override fun onClickItem(heroModel: List<HeroModel>, position: Int) {
        val intentHero = Intent(activity, SecondActivity::class.java)
        intentHero.putExtra(SecondActivity.KEY_ID, position)
        startActivity(intentHero)
    }

    companion object {
        fun newInstance() = FragmentHero()
    }
}