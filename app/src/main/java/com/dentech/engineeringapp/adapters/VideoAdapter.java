package com.dentech.engineeringapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.dentech.engineeringapp.R;
import com.dentech.engineeringapp.model.YoutubeVideos;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    List<YoutubeVideos> youtubeVideosList;

    public VideoAdapter(List<YoutubeVideos> youtubeVideos){
        this.youtubeVideosList = youtubeVideos;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view_layout, parent, false);
         return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.webView.loadData(
                youtubeVideosList.get(position).getVideoUrl(), "text/html", "utf-8"
        );
    }

    @Override
    public int getItemCount() {
        return youtubeVideosList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView webView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            webView =(WebView) itemView.findViewById(R.id.videoWebView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
        }
    }
}
