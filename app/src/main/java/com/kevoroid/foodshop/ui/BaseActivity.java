package com.kevoroid.foodshop.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.utils.PromptHandler;

public class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		PromptHandler.showMsgToast(this, "Designed & Implemented by Kevoroid!\nhttps://kevoroid.github.io/");
	}
}
