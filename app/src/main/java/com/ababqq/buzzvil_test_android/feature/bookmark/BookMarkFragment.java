package com.ababqq.buzzvil_test_android.feature.bookmark;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ababqq.buzzvil_test_android.R;
import com.ababqq.buzzvil_test_android.viewmodels.BookMarkViewModel;

public class BookMarkFragment extends Fragment {

    private BookMarkViewModel mViewModel;

    public static BookMarkFragment newInstance() {
        return new BookMarkFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_mark_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookMarkViewModel.class);
        // TODO: Use the ViewModel
    }

}