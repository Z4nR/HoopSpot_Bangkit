package com.zulham.hoopspot.ui.parking.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zulham.hoopspot.data.remote.response.ParkingItem
import com.zulham.hoopspot.databinding.ActivityParkingDetailBinding
import com.zulham.hoopspot.utils.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ParkingDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ParkingDetailViewModel

    private lateinit var binding: ActivityParkingDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parkDetail = intent.getIntExtra(EXTRA_PARKING, 0)
        val placeId = intent.getIntExtra(EXTRA_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[ParkingDetailViewModel::class.java]

        viewModel.setParkingDetail(placeId, parkDetail)
        viewModel.getParkingDetail().observe(this, {
            val parkings = it.hoopsEntity[0].parking!!
            hoopsDetail(parkings[0])
        })

    }

    private fun hoopsDetail(detail: ParkingItem){
        binding.tvParkName.text = detail.parkName
        binding.tvParkAddress.text = detail.parkAddress
        detail.parkVideo?.let { webView(it) }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webView(url : String){
        val urlVideo = "https://capstone-api-firebase-ptzf4vjc4a-et.a.run.app"

        binding.webViewCamera.settings.javaScriptEnabled = true

        binding.webViewCamera.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:alert('Web Berhasil di Load')")
            }
        }

        binding.webViewCamera.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: android.webkit.JsResult): Boolean {
                Toast.makeText(this@ParkingDetailActivity, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }

        binding.webViewCamera.loadUrl(urlVideo + url)
    }

    companion object {
        const val EXTRA_PARKING = "extra_parking"
        const val EXTRA_ID = "extra_id"
    }

}