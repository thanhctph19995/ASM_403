package com.example.testlogin.fragment;

import static com.example.testlogin.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.testlogin.MainActivity;
import com.example.testlogin.R;
import com.example.testlogin.databinding.FragmentHomeBinding;
import com.example.testlogin.databinding.FragmentIn4Binding;
import com.example.testlogin.mode.In4ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class In4Fragment extends Fragment {
    private FragmentIn4Binding binding;
    private View mView;
    private ImageView imgView;
    private EditText edFullName, edEmail, edPass, edRePass, edPhone, edDate;
    private Button btnUpDate;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        In4ViewModel in4ViewModel =
                new ViewModelProvider(this).get(In4ViewModel.class);

        binding = FragmentIn4Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView fullname, email;

        fullname = binding.fullName;
        email = binding.email;

        in4ViewModel.getText().observe(getViewLifecycleOwner(), fullname::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initListener() {
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestRermission();
            }
        });
    }

    private void onClickRequestRermission() {
        MainActivity mainActivity =(MainActivity)  getActivity();
        if(mainActivity == null){
            return;
        }
        //tu android 6 cho xuong ko can onClickRequestRermission()
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        }else {
            String [] permisstion = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permisstion, MY_REQUEST_CODE);
        }
    }

    private void initui(){
        imgView =mView.findViewById(R.id.img_avata);
        edFullName = mView.findViewById(R.id.ed_full_name);
        edEmail = mView.findViewById(R.id.ed_email);
        edPass = mView.findViewById(R.id.ed_pass);
        edRePass = mView.findViewById(R.id.ed_repass);
        edPhone = mView.findViewById(R.id.ed_phone);
        edDate = mView.findViewById(R.id.ed_date);

        btnUpDate = mView.findViewById(R.id.btn_sign_up);

    }
    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }
        edFullName.setText(user.getDisplayName());
        edEmail.setText(user.getEmail());
        edPhone.setText(user.getPhoneNumber());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.avatar).into(imgView);
    }


}