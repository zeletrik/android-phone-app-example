package hu.logout.example.divinity.phone.recycleview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hu.logout.example.divinity.phone.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    protected ImageView contactimage;
    protected TextView contactname,contactnum;

    public ContactViewHolder(final View itemView) {
        super(itemView);
        contactimage = itemView.findViewById(R.id.contact_profile);
        contactname = itemView.findViewById(R.id.contact_label);
        contactnum = itemView.findViewById(R.id.contact_number);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactnum));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(intent);
                }
            }
        });
    }

}