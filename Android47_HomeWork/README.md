# 요구사항

---

![출처: 윤재성 강사님 과제안내](https://github.com/gayeon00/Techit_Android/assets/68911884/63f30a7c-9f4a-431c-957e-9fc34e8c99fd)

![출처: 윤재성 강사님 과제안내](https://github.com/gayeon00/Techit_Android/assets/68911884/76fbd0a4-0d8a-4336-97df-14c38c739c3e)

![출처: 윤재성 강사님 과제안내](https://github.com/gayeon00/Techit_Android/assets/68911884/db139e38-87be-4f02-9f95-31eb986d2a59)

![출처: 윤재성 강사님 과제안내](https://github.com/gayeon00/Techit_Android/assets/68911884/60c3c716-9b3d-44cb-87bb-06d929d78060)

# 클래스 구조

---

- 첫 화면
    - MainActivity
- 카테고리 추가 화면
    - AddCategoryActivity
- 카테고리 수정 화면
    - EditCategoryActivity
- 특정 카테고리 하위 메모 목록 화면
    - MemoMainActivity
- 메모 등록 화면
    - MemoAddActivity
- 메모 수정 화면
    - MemoEditActivity
- 메모 상세 화면
    - MemoDetailActivity
- 데이터 클래스
    - Category
        - Category Data Class
        - Memo Data Class

```kotlin
├── category
│   ├── AddCategoryActivity
│   └── EditCategoryActivity
├── memo
│   ├── MemoMainActivity
│   ├── MemoAddActivity
│   ├── MemoEditActivity
│   └── MemoDetailActivity
├── Category.kt
└── MainActivity
```

# 아이디어

---

- 하나의 액티비티를 다시 나갔다 들어와도 그 데이터가 유지돼야했다. 그냥 savedInstance로 해결해야하는 것이 아닌, Category 클래스 내부 memoList 프로퍼티가 저장돼야 하는 것이었다. 따라서, 나는 이를 여러 액티비티가 하나의 데이터를 공유해야하는 경우로 생각했고, 결과적으로 Data 라는 클래스를 하나 두어서 companion object로 categoryList를 두어 해결했다.
- 각 액티비티는 다이렉트로 Data아래 categoryList에 접근하여 읽고 쓰게 된다

```kotlin
class Data {
    companion object {
        val categoryList = mutableListOf<Category>()
    }
}

data class Category(var title: String, var memoList: ArrayList<Memo> = ArrayList())

data class Memo(var title: String, var content: String)
```

# 과정

---

1. 필요한 액티비티를 만들고 레이아웃을 작성한다.
2. 요구사항 플로우에 따라 기능을 개발한다.

# 키워드

---

- ViewBinding
- RecyclerView
- ActivityResultLauncher
- OptionMenu
- companion object
