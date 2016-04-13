package co.edu.udea.compumovil.contentprovider;

import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by jaiber on 12/04/16.
 */
public class Contact {

    private Uri contentUri;

    Contact(){
        contentUri = ContactsContract.Contacts.CONTENT_URI;


    }

}
