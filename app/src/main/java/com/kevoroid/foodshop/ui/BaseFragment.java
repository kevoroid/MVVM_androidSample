package com.kevoroid.foodshop.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.utils.PromptHandler;

public class BaseFragment extends Fragment {

	private ProgressDialog progressDialog;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage(getResources().getString(R.string.label_loading));
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
		hideLoading();
		if (getActivity() != null) {
			PromptHandler.showErrSnackBar(getMainLayout(), getActivity());
		}
	}

	public View getMainLayout() {
		View view = null;
		if (getActivity() != null) {
			view = getActivity().findViewById(android.R.id.content);
		}
		return view;

	}
}
