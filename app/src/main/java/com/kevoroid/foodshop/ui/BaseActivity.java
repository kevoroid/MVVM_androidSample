package com.kevoroid.foodshop.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.utils.PromptHandler;

public class BaseActivity extends AppCompatActivity {


	@SuppressWarnings("deprecation")
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getResources().getString(R.string.label_please_wait));
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
	}

	public void showLoading() {
		if (progressDialog != null) {
			progressDialog.show();
		}
	}

	public void hideLoading() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	public void showErr() {
		PromptHandler.showErrSnackBar(getMainLayout(), this);
	}

	public void showConnectionErr() {
		PromptHandler.showConnectionErrSnackBar(getMainLayout(), this);
	}

	private View getMainLayout() {
		return findViewById(android.R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.main_menu_about:
				openAbout();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void openAbout() {
		PromptHandler.showMsgToast(this, getResources().getString(R.string.msg_about));
	}
}
