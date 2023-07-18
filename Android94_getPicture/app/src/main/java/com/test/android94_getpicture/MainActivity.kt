package com.test.android94_getpicture

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.test.android94_getpicture.databinding.ActivityMainBinding
import java.io.File

// 1. 사진을 촬영하면 이미지 파일이 저장될 경로를 xml 폴더에 등록한다.
// xml/file_path.xml

// 2. manifest에 사진촬영을 위한 프로바이더 등록해준다.
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    //이미지가 저장될 위치
    var filePath = ""

    //저장된 파일에 접근하기 위한 URI객체
    lateinit var contentUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //filaPath에 사진을 담아서 그 사진을 불러오는 식으로 하자
        //어플을 위한 외부 저장소 경로 가져옴
        //xml/file_path.xml에 저장한 경로 사용
        filePath = getExternalFilesDir(null).toString()

        //이런 식으로 하면 imageView에 썸네일을 가져온다!! 다른 방식으로 해야함!
        //사진 촬영을 위한 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        /*        cameraLauncher = registerForActivityResult(contract1) {
                    //사진을 촬영하고 촬영한 사진을 선택하고 돌아왔을 경우
                    if (it?.resultCode == RESULT_OK) {
                        //intent로부터 사진 데이터를 가져온다.
                        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            it.data?.getParcelableExtra("data", Bitmap::class.java)
                        } else {
                            it.data?.getParcelableExtra("data")
                        }

                        activityMainBinding.imageView.setImageBitmap(bitmap!!)
                    }

                }*/

        cameraLauncher = registerForActivityResult(contract1) {
            if (it?.resultCode == RESULT_OK) {
                //URI를 이용해 이미지에 접근해서 bitmap객체로 가져옴
                val bitmap = BitmapFactory.decodeFile(contentUri.path)

                //이미지 크기 조정
                //이미지의 축소/확대 비율 구함
                //가로 1024로 하도록 하자
                val ratio = 1024.0 / bitmap.width
                //세로길이 구함
                val targetHeight = (bitmap.height * ratio).toInt()

                //크기 조정한 비트맵 생성
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)


                //카메라의 정위치가 horizontal이라 기기를vertical로 해서찍으면 이미지가 돌아가서 표시됨!
                //exifInterface로 수정할 수 있음
                //(날짜와 시간, 카메라 정보, 카메라 설정, 위치 정보, Orientation(기기의 회전 정보)등의 정보 들어있음)
                //회전 각도 가져오기
                val degree = getDegree(contentUri)

                //회전 이미지를 생성하기 위한 변환 행렬
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())

                //회전 행렬을 적용해 회전된 이미지를 생성한다.
                //(원본이미지, 원본이미지의 x좌표, y좌표, 원본 이미지 가로길이, 세로길이, 변환 행렬, 필터정보)
                //원본 이미지에서 지정된 x,y좌표를 찍과 거기서부터 이미지 가로, 세로 길이만큼의 이미지 데이터를 가져와 변환 행렬을 적용해서 이미지를 변환한다.
                //여기서는 이미지 모든 영역을 선택해서 변환할거라 저런 식으로 넣어줌
                val bitmap3 =
                    Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)

                activityMainBinding.imageView.setImageBitmap(bitmap3)

                //이미지 파일은 삭제
                //삭제 안하면 이미지 파일이 쌓임
                //정말 저장하고싶다면 bitmap객체를 다시 저장해두면 됨
                //이 작업은 그저 사진 원본을 가져오기 위핸 작업임!
                val file = File(contentUri.path)
                file.delete()


            }
        }


        activityMainBinding.run {
            //아래는 썸네일을 가져옴
            /*            button.setOnClickListener {
                            //사진 촬영을 위한 액티비티 실행(스노우 같은 앱들은 이렇게 카메라를 실행시킴)
                            val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            cameraLauncher.launch(newIntent)
                        }*/

            button.setOnClickListener {
                //내가 경로, 이름 정해줄 테니까 여기에다 저장해! 라고 명령!!
                val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                //촬영한 사진이 저장될 파일 이름
                val now = System.currentTimeMillis()
                val fileName = "/temp_$now.jpg"
                //경로 + 파일 이름
                val picPath = "$filePath/$fileName"

                //파일 객체
                val file = File(picPath)

                //사진이 저장될 경로를 관리할 URI객체 생성
                contentUri = FileProvider.getUriForFile(
                    this@MainActivity,
                    "com.test.getpicture.file_provider",
                    file
                )

                newIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                cameraLauncher.launch(newIntent)
            }
        }


    }

    //이미지 파일에 기록돼있는 기기 회전 정보를 가져온다.
    fun getDegree(uri: Uri): Int {
        var exifInterface: ExifInterface? = null

        //사진 파일로부터 tag정보를 관리하는 객체를 추출
        exifInterface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val photoUri = MediaStore.setRequireOriginal(uri)
            //파일과 연결된 스트림 추출
            val inputStream = contentResolver.openInputStream(photoUri)
            //ExifInterface정보 읽어오기
            ExifInterface(inputStream!!)
        } else {
            ExifInterface(uri.path!!)
        }

        var degree = 0
        //각도 값 가져오기

        when (exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)) {
            ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
            ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
            ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
        }

        return degree
    }
}