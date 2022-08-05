package com.kjy.kotlinthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kjy.kotlinthread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 안드로이드에는 중요한 규칙이 있다.
        // 백그라운드 스레드는 UI 구성 요소에 접근하면 안된다.
        Thread {
            var i = 0
            while(i < 10) {
                binding.threadText.text = "$i"
                i += 1
                Thread.sleep(1000)      // 1초마다 한번씩
            }
        }.start()





    }
}

// 메인스레드가 기본적으로 있고 추가적으로 백그라운드 스레드를 생성해보자
class WorkerThread: Thread() {              // Thread 클래스 상속
    override fun run() { // Thread가 처리할 로직을 정의하느 run() 메서드를 정의한다.
        var i = 0
        while(i < 10) {
            i += 1
            Log.i("WorkerThread", "$i")
        }
    }
}

// Runnable 인터페이스를 구현해 스레드를 생성할 수 있음
// Runnable 인터페이스는 다중 상속을 허용하지 않는 코틀린 언어의 특성상 상속 관계에 잇는 클래스도 구현할 수 있도록 지원하는 모델이다.

class WorkerRunnable: Runnable {
    override fun run() {
        var i = 0
        while(i < 10) {
            i += 1
            Log.i("WorkerRunnable", "$i")
        }

    }
}

/*
- 람다식으로 Runnable 익명 객체 구현
인터페이스 내부에 메서드가 하나만 있는 경우는 람다식으로 변환 가능 Runnable 인터페이스를 이용한 스레드는 람다식으로 변환 가능.
   Thread {
            var i = 0
            while(i < 10){
                i += 1
                Log.i("LambdaWorker", "$i")
            }
        }.start()
 */

/*
      - 코틀린에서 제공하는 thread 구현
        thread(start = true) {
            var i = 0
            while (i < 10) {
                i += 1
                Log.i("KotlinThread", "$i")
            }
        }
 */
