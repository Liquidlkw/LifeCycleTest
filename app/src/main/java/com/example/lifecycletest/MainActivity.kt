/*
 * ===========================================================================================
 *  COPYRIGHT
 *           PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *  This software is supplied under the terms of a license agreement or nondisclosure
 *  agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *  disclosed except in accordance with the terms in that agreement.
 *           Copyright (C) 2021-2023 PAX Computer Technology(Shenzhen) CO., LTD All
 *   rights reserved.
 *
 *  Revision History:
 *  Date                           Author                      Action
 *  2023/10/16                     likewei                     Create
 * ===========================================================================================
 *
 */

package com.example.lifecycletest

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

private const val TAG = "MainActivity"


class MainActivity() : AppCompatActivity(), Parcelable {

  val newObserver: LifecycleObserver = object : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
      Log.d(TAG, "onCreate: newObserver")
    }

    override fun onStart(owner: LifecycleOwner) {
      Log.d(TAG, "onStart: newObserver")
    }

    override fun onResume(owner: LifecycleOwner) {
      Log.d(TAG, "onResume: newObserver")
    }
  }

  constructor(parcel: Parcel) : this() {
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.container, BlankFragment());
    fragmentTransaction.commit()
    //最简单的情况:此时不发生同步 观察者和被观察状态都为INIT 不发生sync()
    // observerMap.eldest().state == observerMap.newest().state = INIT
    lifecycle.addObserver(object : LifecycleObserver {
    })

//
//        //递归复杂情况
//        lifecycle.addObserver(object : DefaultLifecycleObserver {
//            override fun onCreate(owner: LifecycleOwner) {
//                Log.d(TAG, "onCreate: ")
//            }
//
//            override fun onStart(owner: LifecycleOwner) {
//                Log.d(TAG, "onStart: ")
//                lifecycle.removeObserver(this)
//                lifecycle.addObserver(newObserver)
//            }
//
//            override fun onResume(owner: LifecycleOwner) {
//                Log.d(TAG, "onResume: ")
//            }
//        })
  }

  fun abc(num: Int): Int {
    return 1;
  }

  override fun onStart() {
//        Log.d(TAG, "onStart: " + System.nanoTime())
    super.onStart()
  }

  //STARTED
  //ON_RESUME
  override fun onResume() {
    super.onResume()
    //非INIT addObserver
    lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "onCreate: ")
      }

      //STARED
      //ON_RESUME
      override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "onStart: ")
        //case1:回调内改变原有状态
        // 虽然此处可以选择改变状态变更，但是不推荐这样使用 虽然lifecycle内部有做处理 但是容易状态混乱 出现同一个状态多次回调
        //FOR EXAMPLE  ↓↓↓↓
        //此处按道理要分发ON_RESUME,就算多分发一次ON_START 内部也会return
        // ,这里我们将他分发为ON_CREATE只是为了体现内部状态回调改变原有状态传递的列子，非正常使用，不推荐！
        //val lifecycleRegistry = lifecycle as LifecycleRegistry
        // lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)

        //case2：回调内添加观察者
        //触发addObserver sync()
        lifecycle.addObserver(newObserver)
      }

      override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "onResume: ")
      }

      override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d(TAG, "onPause: ")
      }

      override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d(TAG, "onStop: ")
      }
    })
  }

  override fun writeToParcel(
    parcel: Parcel,
    flags: Int
  ) {
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<MainActivity> {
    override fun createFromParcel(parcel: Parcel): MainActivity {
      return MainActivity(parcel)
    }

    override fun newArray(size: Int): Array<MainActivity?> {
      return arrayOfNulls(size)
    }
  }
}