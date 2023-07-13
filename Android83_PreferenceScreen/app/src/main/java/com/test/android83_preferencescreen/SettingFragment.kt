package com.test.android83_preferencescreen

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

// build.gradle 에 androidx.perference:perference 라이브러리를 추가한다.
class SettingFragment : PreferenceFragmentCompat() {
    //Preference Screen이 생성될 때 호출
    //Preference Screen을 구성하기 위한 xml파일을 지정한다.
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref, rootKey)

        // EditTextPreference
        // defaultValue : 초기값
        // title : 화면에 보여지는 이름
        // key : 데이터를 가져올 때 사용하는 이름
        // summary : 표시는 설명
        // icon : 좌측에 표시될 아이콘
        // dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
        // dialogTitle : 입력을 위해 뜨는 다이얼로그의 타이틀
        // dialogMessage : 입력을 위해 뜨는 다이얼로그의 메시지
        // dependency : true나 false를 저장하는 요소의 값에 따라 활성화, 비활성화가 설정된다.
        // 참조하고자 하는 preference의 키를 넣어주면 됨!
        // true면 활성화되고, false면 비활성화된다.

        //다이얼로그에서 확인을 누르면 저장까지 이루어짐!!

        // CheckBoxPreference
        // defaultValue : 초기값
        // key : 데이터를 가졍로 때 사용하는 이름
        // title : 화면에 보여지는 이름
        // summary : 표시되는 설명
        // icon : 좌측에 표시될 아이콘
        // summaryOff : 체크 해제 되어 있을 때 보여줄 설명
        // summaryOn : 체크 되어 있을 때 보여줄 설명
        // dependency : true나 false를 저장하는 요소의 값에 따라 활성화, 비활성화가 설정된다.
        // true면 활성화되고, false면 비활성화된다.

        //SwitchPreference
        // defaultValue : 초기값
        // key : 데이터를 가졍로 때 사용하는 이름
        // title : 화면에 보여지는 이름
        // summary : 표시되는 설명
        // icon : 좌측에 표시될 아이콘
        // summaryOff : off 되어 있을 때 보여줄 설명
        // summaryOn : on 되어 있을 때 보여줄 설명
        // dependency : on off를 저장하는 요소의 값에 따라 활성화, 비활성화가 설정된다.
        // on 활성화되고, off면 비활성화된다.

        //ListPreference
        // defaultValue : 초기값
        // key : 데이터를 가졍로 때 사용하는 이름
        // title : 화면에 보여지는 이름
        // summary : 표시되는 설명
        // icon : 좌측에 표시될 아이콘
        // dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
        // entries : 화면 상에 보여줄 항목의 문자열
        // entryValues : 코드에서 사용할 값
        // 얘네 둘이를 string.xml에 정의해서 넣어줘야함!
        // 선택하는 순간 저장이 됨! -> 문자열형태로 저장됨

        //MultiSelectListPreference
        // defaultValue : 초기값배열(여러개가 기본 선택돼있을 수도 있으니까)
        // key : 데이터를 가졍로 때 사용하는 이름
        // title : 화면에 보여지는 이름
        // summary : 표시되는 설명
        // icon : 좌측에 표시될 아이콘
        // dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
        // entries : 화면 상에 보여줄 항목의 문자열
        // entryValues : 코드에서 사용할 값
        // 얘네 둘이를 string.xml에 정의해서 넣어줘야함!
        // 선택하는 순간 저장이 됨! -> 문자열형태로 저장됨

    }
}