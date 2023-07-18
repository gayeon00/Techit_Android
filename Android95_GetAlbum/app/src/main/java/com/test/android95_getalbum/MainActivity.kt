package com.test.android95_getalbum

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.test.android95_getalbum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    private val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    private lateinit var albumActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        val contract1 = ActivityResultContracts.StartActivityForResult()
        albumActivityResultLauncher = registerForActivityResult(contract1) {
            if (it?.resultCode == RESULT_OK) {
                //선택한 이미지에 접근할 수 있는 Uri 객체를 추출
                val uri = it.data?.data

                //안드로이드는 영상, 음원, 사진 등의 미디어 파일을 단말기 내부에 저장하면
                //미디어 스캐너 라는 것이 자동으로 가동된다.
                //미디어 스캐너의 역할은 영상, 음원, 사진 등에 관련된 데이터를 추출하여
                //sqlitedatabase에 저장한다.
                // 이 정보를 가져다 사용할 수 있는 컨텐츠 프로바이더를 이용해 정보를 가져온다.
                if (uri != null) {
                    //안드로이드10(Q)이상이면
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        //이미지를 생성할 수 있는 디코더를 생성한다.
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        //bitmap객체 생성
                        val bitmap = ImageDecoder.decodeBitmap(source)

                        activityMainBinding.imageView.setImageBitmap(bitmap)
                    } else {
                        // 컨텐츠 프로바이더를 통해 이미지 데이터 정보를 가져온다.
                        val cursor = contentResolver.query(uri, null, null, null, null)
                        if (cursor != null) {
                            cursor.moveToNext()

                            // 이미지의 경로를 가져온다.
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지를 생성하여 보여준다.
                            val bitmap = BitmapFactory.decodeFile(source)
                            activityMainBinding.imageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }

        activityMainBinding.run {
            button.setOnClickListener {
                //앨범에서 사진을 선택할 수 있는 액티비티 실행
                val newIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                //실행할 액티비티의 마임타입 설정(이미지로 설정해줌)
                newIntent.type = "image/*"
                //선택한 파일의 타입을 지정(안드로이드 os가이미지에 대한 사전 작업을 할 수 있도록)
                val mimeType = arrayOf("image/*")
                newIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                //액티비티 실행
                albumActivityResultLauncher.launch(newIntent)
            }
        }
    }
}