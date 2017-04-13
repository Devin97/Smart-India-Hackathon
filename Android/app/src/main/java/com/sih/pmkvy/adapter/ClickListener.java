package com.sih.pmkvy.adapter;

import android.view.View;

/**
 * Created by ASUS on 2/25/2017.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}