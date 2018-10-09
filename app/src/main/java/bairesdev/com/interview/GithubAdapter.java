package bairesdev.com.interview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Ing. Wilson Garcia
 * Created on 10/9/18
 */
public class GithubAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

    private List<GithubProject> data;
    private Context context;

    public GithubAdapter(List<GithubProject> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.project_view, viewGroup, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder projectViewHolder, int i) {
        projectViewHolder.bindElement(data.get(i), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
