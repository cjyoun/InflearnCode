# 데이터 바인딩
1. gradle 에 android 부분에 추가
```kotlin
dataBinding{
    enabled true
}
```
2. xml 전체를 <layout> </layout> 으로 감싸기

3. Activity 에서 코드 추가
```kotlin
// Activity 별 Binding
private lateinit var binding : ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    
    binding.id값.setOnClickListener{
        ~~
    }
    ~~
    
}
```

# 주사위 돌리기
랜덤 함수를 사용하여 주사위 돌리기 