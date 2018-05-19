package com.arrk.arrkandroidtest.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arrk.arrkandroidtest.CharacterDetailActivity;
import com.arrk.arrkandroidtest.R;
import com.arrk.arrkandroidtest.model.Character;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman on 19/10/16.
 */

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Character> charactersList;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context) {
        this.context = context;
        charactersList = new ArrayList<>();
    }

    public List<Character> getCharactersList() {
        return charactersList;
    }

    public void setCharactersList(List<Character> people) {
        this.charactersList = people;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new CharacterVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Character character = charactersList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                CharacterVH cVH = (CharacterVH) holder;

                cVH.textView.setText(character.getName());
                cVH.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CharacterDetailActivity.class);
                        Gson gson = new Gson();
                        String myCharacter = gson.toJson(character);
                        intent.putExtra("character", myCharacter);
                        context.startActivity(intent);
                    }
                });

                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return charactersList == null ? 0 : charactersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == charactersList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Character mc) {
        charactersList.add(mc);
        notifyItemInserted(charactersList.size() - 1);
    }

    public void addAll(List<Character> mcList) {
        for (Character mc : mcList) {
            add(mc);
        }
    }

    public void remove(Character character) {
        int position = charactersList.indexOf(character);
        if (position > -1) {
            charactersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Character());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = charactersList.size() - 1;
        Character item = getItem(position);

        if (item != null) {
            charactersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Character getItem(int position) {
        return charactersList.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class CharacterVH extends RecyclerView.ViewHolder {
        private TextView textView;

        public CharacterVH(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}
