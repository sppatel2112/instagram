package com.example.instagram;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    public static final String TAG = "PostsAdapter";

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> allPosts) {
        this.context = context;
        this.posts = allPosts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView descriptionUsername;
        private TextView date;
        private ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDesciption);
            descriptionUsername = itemView.findViewById(R.id.desciptionUsername);
            date = itemView.findViewById(R.id.date);
            userImage = itemView.findViewById(R.id.userImage);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(Post post) {
            Date datePost = post.getCreatedAt();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm");
            String posted = dateFormat.format(datePost);
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            descriptionUsername.setText(post.getUser().getUsername() + ": ");
            date.setText(posted);
            ParseFile image = post.getImage();
            if (image != null)
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            Glide.with(context).load(R.drawable.profile).into(userImage);
        }
    }
}
