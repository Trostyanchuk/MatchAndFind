package com.matchandfind.ui.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.matchandfind.databinding.ItemPersonBinding;
import com.matchandfind.ui.model.PersonViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonsViewHolder>{

    private List<PersonViewModel> personsList = new ArrayList<>();

    @Override
    public PersonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPersonBinding binding = ItemPersonBinding.inflate(inflater, parent, false);
        return new PersonsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(PersonsViewHolder holder, int position) {
        holder.mBinding.setPerson(personsList.get(position));
//        Picasso.with(holder.mBinding..getContext()).load(v).into(imageView);
    }

    public void setPersonsList(List<PersonViewModel> personsList) {
        this.personsList = new ArrayList<>(personsList);
        notifyDataSetChanged();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(final ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }

    public class PersonsViewHolder extends RecyclerView.ViewHolder{

        ItemPersonBinding mBinding;

        public PersonsViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
