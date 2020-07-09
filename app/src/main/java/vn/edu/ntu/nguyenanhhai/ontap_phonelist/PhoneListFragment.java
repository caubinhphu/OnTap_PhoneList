package vn.edu.ntu.nguyenanhhai.ontap_phonelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.ntu.nguyenanhhai.controllers.ContactController;
import vn.edu.ntu.nguyenanhhai.controllers.IContactController;
import vn.edu.ntu.nguyenanhhai.models.Contact;

public class PhoneListFragment extends Fragment {
  NavController navController;
  IContactController contactController;
  RecyclerView rvListPhone;
  ContactAdapter adapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    navController = NavHostFragment.findNavController(this);
    ((MainActivity)getActivity()).navController = navController;

    contactController = ((MainActivity)getActivity()).contactController;
  }

  @Override
  public void onStart() {
    super.onStart();
    ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_plus, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.mnu_plus) {
      // add
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_phone_list, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    rvListPhone = view.findViewById(R.id.rvListPhone);
    rvListPhone.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    adapter = new ContactAdapter(contactController.getAllContact());
    rvListPhone.setAdapter(adapter);
  }


  private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txtName, txtBirth, txtPhone, txtId;
    ImageView imvEdit, imvCall, imvMess;
    Contact contact;

    public ContactHolder(@NonNull View itemView) {
      super(itemView);
      txtName = itemView.findViewById(R.id.txtName);
      txtBirth = itemView.findViewById(R.id.txtBirth);
      txtPhone = itemView.findViewById(R.id.txtPhone);
      imvEdit = itemView.findViewById(R.id.imvEdit);
      imvCall = itemView.findViewById(R.id.imvCall);
      imvMess = itemView.findViewById(R.id.imvMess);
      txtId = itemView.findViewById(R.id.txtId);

      imvEdit.setOnClickListener(this);
      imvCall.setOnClickListener(this);
      imvMess.setOnClickListener(this);
    }

    public void bind(Contact contact) {
      this.contact = contact;
      txtName.setText(contact.getName());
      txtBirth.setText("" + contact.getBirthday());
      txtPhone.setText(contact.getPhone());
      txtId.setText("" + contact.getId());
    }

    @Override
    public void onClick(View v) {
      int id = v.getId();
      switch (id) {
        case R.id.imvEdit: edit(); break;
        case R.id.imvCall: call(); break;
        case R.id.imvMess: mess(); break;
      }
    }

    private void edit() {
      Bundle bundle = new Bundle();
      bundle.putInt("id", new Integer(txtId.getText().toString()));
      navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }
    private void call() {
      Toast.makeText(getActivity(), "Call " + txtPhone.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    private void mess() {
      Toast.makeText(getActivity(), "Message " + txtPhone.getText().toString(), Toast.LENGTH_SHORT).show();
    }
  }

  private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
    List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
      this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = getLayoutInflater();
      View view =  inflater.inflate(R.layout.phone_item, parent, false);
      return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
      holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
      return contacts.size();
    }
  }
}
