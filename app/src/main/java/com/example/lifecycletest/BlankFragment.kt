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

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val TAG = "BlankFragment"


class BlankFragment : Fragment() {
  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d(TAG, "onAttach: ")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    Log.d(TAG, "onCreate: ")
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View? {
    Log.d(TAG, "onCreateView: ")
    return inflater.inflate(R.layout.fragment_blank, container, false)
  }

  override fun onViewCreated(
      view: View,
      savedInstanceState: Bundle?,
  ) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStart() {
    Log.d(TAG, "onStart====: " + System.nanoTime())
    super.onStart()
  }

  override fun onResume() {
    Log.d(TAG, "onResume: ")
    super.onResume()
  }
}