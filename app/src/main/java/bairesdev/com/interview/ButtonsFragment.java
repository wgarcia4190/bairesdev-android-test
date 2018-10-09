package bairesdev.com.interview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Ing. Wilson Garcia
 * Created on 10/9/18
 */
public class ButtonsFragment extends Fragment implements View.OnClickListener {

    private Button showToast;
    private Button showAlert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons_fragment, container, false);

        showToast = view.findViewById(R.id.show_toast);
        showAlert = view.findViewById(R.id.show_alert);

        setupEvents();

        return view;
    }

    private void setupEvents(){
        showToast.setOnClickListener(this);
        showAlert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_toast:
                Toast.makeText(getContext(), "Showing A Toast", Toast.LENGTH_LONG).show();
                break;
            case R.id.show_alert:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alert Dialog");
                builder.setMessage("This is an alert");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
                break;
        }
    }
}
