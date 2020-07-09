package vn.edu.ntu.nguyenanhhai.ontap_phonelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.nguyenanhhai.controllers.IContactController;
import vn.edu.ntu.nguyenanhhai.models.Contact;

public class InfoFragment extends Fragment implements View.OnClickListener {
  TextView edtId, edtPhone, edtName, edtAddr, edtBirth;
  Button btnSave;
  IContactController contactController;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_info, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    contactController = ((MainActivity)getActivity()).contactController;

    edtId = view.findViewById(R.id.edtId);
    edtPhone = view.findViewById(R.id.edtPhone);
    edtName = view.findViewById(R.id.edtName);
    edtAddr = view.findViewById(R.id.edtAddr);
    edtBirth = view.findViewById(R.id.edtBirth);
    btnSave = view.findViewById(R.id.btnSave);

    btnSave.setOnClickListener(this);

    Bundle bundle = getArguments();
    int id = bundle.getInt("id");
    Contact contact = contactController.getContact(id);
    if (contact != null) {
      edtId.setText("" + contact.getId());
      edtPhone.setText(contact.getPhone());
      edtName.setText(contact.getName());
      edtAddr.setText(contact.getAddr());
      edtBirth.setText(contact.getBirthday());
    }
  }

  @Override
  public void onClick(View v) {

  }
}
