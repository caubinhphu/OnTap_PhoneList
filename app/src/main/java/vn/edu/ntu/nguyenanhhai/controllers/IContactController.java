package vn.edu.ntu.nguyenanhhai.controllers;

import android.content.Context;

import java.util.List;

import vn.edu.ntu.nguyenanhhai.models.Contact;

public interface IContactController {
  List<Contact> getAllContact();
  Contact getContact(int id);
  boolean addContact(Contact contact);
}
