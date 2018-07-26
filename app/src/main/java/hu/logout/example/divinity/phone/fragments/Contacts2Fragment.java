package hu.logout.example.divinity.phone.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.logout.example.divinity.phone.R;
import hu.logout.example.divinity.phone.recycleview.ContactsAdapter;

/**
 * Fragment that holds the RecyclerView
 */
public class Contacts2Fragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private RecyclerView mContactListView;

    public Contacts2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View root = inflater.inflate(R.layout.contacts_list, container, false);
        mContactListView = root.findViewById(R.id.contact_list);
        requestContacts();
        return root;
    }


    private void requestContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            mContactListView.setAdapter(new ContactsAdapter(getActivity()));
            mContactListView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mContactListView.setItemAnimator(new DefaultItemAnimator());
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }
}