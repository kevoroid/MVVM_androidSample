package com.kevoroid.foodshop.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kevoroid.foodshop.R;

public class PromptHandler {

	private PromptHandler() {
	}

	public static void showErrSnackBar(View view, Context context) {
		Snackbar.make(view,
				context.getResources().getString(R.string.we_have_a_problem_err),
				BaseTransientBottomBar.LENGTH_LONG)
				.show();
	}

	public static void showMsgToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
