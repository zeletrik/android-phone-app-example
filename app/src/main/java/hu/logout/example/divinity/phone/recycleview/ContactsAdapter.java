package hu.logout.example.divinity.phone.recycleview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.logout.example.divinity.phone.R;
import hu.logout.example.divinity.phone.model.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private static final String SORT_ORDER = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC ";


    private List<Contact> contactList;
    private Context mContext;



    public ContactsAdapter(Context context) {
        mContext = context;
        ContentResolver cr =  mContext.getContentResolver();
        Contact contact;

        contactList = new ArrayList<>();

        String name;
        String number = null;
        String image_uri = "";

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                SORT_ORDER);

        int i = 1;
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                if(i == 1){
                    cur.moveToFirst();
                    i++;
                }
                String id = cur.getString(cur
                        .getColumnIndex(ContactsContract.Contacts._ID));
                name = cur
                        .getString(cur
                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                image_uri = cur
                        .getString(cur
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                Cursor pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = ?", new String[]{id}, null);
                if (pCur.moveToNext()) {
                    number = pCur
                            .getString(pCur
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    System.out.println("Name: "+ name + "phone: " + number);
                }
                pCur.close();
                contact = new Contact(image_uri, name, number);
                contactList.add(contact);
            }
            cur.close();
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int pos) {

        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);

        return new ContactViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int pos) {

        Contact contacts = contactList.get(pos);

        TextView contact_name = contactViewHolder.contactname;
        TextView contact_num = contactViewHolder.contactnum;
        ImageView contact_image = contactViewHolder.contactimage;

        contact_name.setText(contacts.getName());
        contact_num.setText(contacts.getContactNumber());
       if (contacts.getProfilePic() != null) {
            Uri myuri = Uri.parse(contacts.getProfilePic());
            contact_image.setImageURI(myuri);
       } else {
           contact_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_person_black_24dp));
       }

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}