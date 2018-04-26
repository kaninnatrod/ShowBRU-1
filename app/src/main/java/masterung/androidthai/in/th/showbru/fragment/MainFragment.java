package masterung.androidthai.in.th.showbru.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import masterung.androidthai.in.th.showbru.R;
import masterung.androidthai.in.th.showbru.utility.GetAllData;
import masterung.androidthai.in.th.showbru.utility.MyAlert;
import masterung.androidthai.in.th.showbru.utility.MyConstant;

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();


    }   // Main Method

    private void loginController() {

        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Have Space",
                            "Please Fill Every Blank");

                } else {
//                    No Space

                    MyConstant myConstant = new MyConstant();
                    boolean b = true;
                    String truePass, nameUser;

                    try {

                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(myConstant.getUrlGetAllUser());

                        String jsonString = getAllData.get();
                        Log.d("26AprilV1", "JSON ==> " + jsonString);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }




                }   // if



            }
        });

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   // Main Class
