package com.matchandfind.ui.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.matchandfind.databinding.ItemPersonBinding;
import com.matchandfind.model.Person;
import com.matchandfind.ui.model.PersonViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonsViewHolder> {

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
    }

    public void setPersonsList(List<PersonViewModel> personsList) {
        this.personsList = new ArrayList<>(personsList);
        notifyDataSetChanged();
    }

    public void removeItem(Person person) {
        int modelToRemove = getViewModelForPerson(person);
        if (modelToRemove >= 0) {
            personsList.remove(modelToRemove);
            notifyItemRemoved(modelToRemove);
        }
    }

    private int getViewModelForPerson(Person person) {
        for (int i = 0; i < personsList.size(); i++) {
            PersonViewModel model = personsList.get(i);
            if (model.getPerson().getId() == person.getId()) {
                return i;
            }
        }
        return -1;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(final ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }

    public class PersonsViewHolder extends RecyclerView.ViewHolder {

        ItemPersonBinding mBinding;

        public PersonsViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
