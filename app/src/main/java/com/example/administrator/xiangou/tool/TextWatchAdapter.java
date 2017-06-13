package com.example.administrator.xiangou.tool;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 作者： tj on 2017/6/12.
 * <p>
 * 功能：TextWatcher的实现抽象类
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public abstract class TextWatchAdapter implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
