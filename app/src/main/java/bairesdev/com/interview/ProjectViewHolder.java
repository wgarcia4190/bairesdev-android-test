package bairesdev.com.interview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @author Ing. Wilson Garcia
 * Created on 10/9/18
 */
public class ProjectViewHolder extends RecyclerView.ViewHolder {

    private TextView projectName;

    public ProjectViewHolder(@NonNull View itemView) {
        super(itemView);

        projectName = itemView.findViewById(R.id.project_name);
    }

    public void bindElement(final GithubProject project, final Context context){
        projectName.setText(project.getName());

        projectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(project.getUrl()));
                context.startActivity(browserIntent);
            }
        });
    }
}
