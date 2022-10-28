package com.example.requesttest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.requesttest1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		var data = listOf("선택하세요", "1", "2", "3", "4", "5")

		var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

		binding.spinner.adapter = adapter

		var url = "http://192.168.0.29:8083/test/index.jsp"
		// 로컬 컴을 localhost나 '127.0.0.1' 로 연결하면 오류가 발생함
		// (net::ERR_CLEARTEXT_NOT_PERMITTED 오류 발생)
		// 버튼 클릭시 'test1.jsp'를 열면서 v1과 v2 변수에 각각 10과 20의 값을 가지고 가
		// test1.jsp에서 그 값을 받아 출력

		var num = ""

		binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
			override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
				num = data[p2]
			}

			override fun onNothingSelected(p0: AdapterView<*>?) {  }
		}

		binding.btnSend.setOnClickListener {
			var id = binding.txtID.text.toString()
			var pw = binding.txtPW.text.toString()
			var name = binding.txtName.text.toString()


			url = "http://192.168.0.29:8083/test/test.jsp?v1=${id}&v2=${pw}&v3=${name}&v4=${num}"
			binding.webView.loadUrl(url)

		}

		binding.webView.webViewClient = WebViewClient()
		binding.webView.loadUrl(url)

	}
}