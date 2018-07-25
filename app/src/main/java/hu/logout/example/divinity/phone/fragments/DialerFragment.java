package hu.logout.example.divinity.phone.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import hu.logout.example.divinity.phone.R;

public class DialerFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private EditText editText;

    public DialerFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.dialer_fragment, container, false);

        editText = rootView.findViewById(R.id.editText);

        fab = rootView.findViewById(R.id.callFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requestPhoneCall()) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + editText.getText().toString()));
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), "Calling " + editText.getText().toString() + " Not permited!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }

    private boolean requestPhoneCall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            return true;
        }
        return false;
    }

}