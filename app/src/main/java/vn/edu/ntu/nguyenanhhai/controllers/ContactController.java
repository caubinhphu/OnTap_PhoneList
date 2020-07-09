package vn.edu.ntu.nguyenanhhai.controllers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.ntu.nguyenanhhai.models.Contact;
import vn.edu.ntu.nguyenanhhai.ontap_phonelist.R;

public class ContactController implements IContactController {
  Context context;

  public ContactController(Context context) {
    this.context = context;
  }

  @Override
  public List<Contact> getAllContact() {
    SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_file_share), Application.MODE_PRIVATE);
    if (preferences != null) {
      Gson gson = new Gson();
      String jsonText = preferences.getString(context.getString(R.string.key_contact), null);
      Contact[] cs = gson.fromJson(jsonText, Contact[].class);
      List<Contact> contacts;
      if (cs != null && cs.length > 0) {
        contacts = new ArrayList<>(Arrays.asList(cs));
      } else {
        contacts = new ArrayList<>();
      }
      return  contacts;
    }
    return null;
  }

  @Override
  public Contact getContact(int id) {
    SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_file_share), Application.MODE_PRIVATE);
    if (preferences != null) {
      Gson gson = new Gson();
      String jsonText = preferences.getString(context.getString(R.string.key_contact), null);
      Contact[] cs = gson.fromJson(jsonText, Contact[].class);
      List<Contact> contacts;
      if (cs.length > 0) {
        contacts = new ArrayList<>(Arrays.asList(cs));
      } else {
        contacts = new ArrayList<>();
      }
      for (Contact contact : contacts) {
        if (contact.getId() == id) {
          return contact;
        }
      }
    }
    return null;
  }

  @Override
  public boolean addContact(Contact contact) {
//    Contact c = getContact(contact.getId());
//    if (c == null) {
      SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.key_file_share), Application.MODE_PRIVATE);
      Gson gson = new Gson();
      String jsonText = preferences.getString(context.getString(R.string.key_contact), null);
      Contact[] cs = gson.fromJson(jsonText, Contact[].class);
      List<Contact> contacts;
      if (cs.length > 0) {
        contacts = new ArrayList<>(Arrays.asList(cs));
      } else {
        contacts = new ArrayList<>();
      }
      contacts.add(contact);

      String jsonText2 = gson.toJson(contacts);
      SharedPreferences.Editor editor = preferences.edit();
      editor.remove(context.getString(R.string.key_contact));
      editor.putString(context.getString(R.string.key_contact), jsonText2);
      editor.commit();

      return true;
//    }
//    return false;
  }
}
