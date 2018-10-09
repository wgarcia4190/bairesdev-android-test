package bairesdev.com.interview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Ing. Wilson Garcia
 * Created on 10/9/18
 */
public class GithubFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.github_fragment, container, false);

        recyclerView = view.findViewById(R.id.projects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GithubProjectsAsyncTask task = new GithubProjectsAsyncTask();
        task.execute();

        return view;
    }


    private class GithubProjectsAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL("https://api.github.com/search/repositories?q=language:kotlin");
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int status = urlConnection.getResponseCode();

                if(status == 200){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while((line = bufferedReader.readLine()) != null){
                        sb.append(line+"\n");
                    }
                    bufferedReader.close();
                    return sb.toString();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray array = jsonObject.optJSONArray("items");

                List<GithubProject> projects = new ArrayList<>();

                if(array != null){
                    for(int index = 0; index < array.length(); index++){
                        GithubProject project = new GithubProject();
                        project.setName(array.getJSONObject(index).optString("name", ""));
                        project.setUrl(array.getJSONObject(index).optString("html_url", ""));

                        projects.add(project);

                        if(index >= 9)
                            break;
                    }

                    GithubAdapter adapter = new GithubAdapter(projects, getContext());
                    recyclerView.setAdapter(adapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
