package daniel.brian.xpressapp.admin.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedBookingsAdapter extends RecyclerView.Adapter<CompletedBookingsAdapter.CompletedBookingsViewHolder> {
    @NonNull
    @Override
    public CompletedBookingsAdapter.CompletedBookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedBookingsAdapter.CompletedBookingsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CompletedBookingsViewHolder extends RecyclerView.ViewHolder{
        public CompletedBookingsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
