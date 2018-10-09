package bairesdev.com.interview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author Ing. Wilson Garcia
 * Created on 10/9/18
 */
public class GoogleFragment extends Fragment {

    private WebView googleWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.google_fragment, container, false);

        googleWebView = view.findViewById(R.id.webView);

        WebSettings settings = googleWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        googleWebView.loadUrl("https://www.google.com");
        return view;
    }
}
