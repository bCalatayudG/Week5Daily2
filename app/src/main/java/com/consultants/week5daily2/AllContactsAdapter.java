package com.consultants.week5daily2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AllContactsAdapter extends RecyclerView.Adapter<AllContactsAdapter.ContactViewHolder> {

    private List<ContactVO> contactVOList;
    private Context mContext;
    private FragmentManager fm;
    private static final String TAG = AllContactsAdapter.class.getSimpleName() + "_TAG";

    AllContactsAdapter(List<ContactVO> contactVOList, Context mContext, FragmentManager fm) {
        this.contactVOList = contactVOList;
        this.mContext = mContext;
        this.fm = fm;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_contact_view, null);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        final ContactVO contactVO = contactVOList.get(position);
        holder.tvContactName.setText(contactVO.getContactName());
        holder.tvPhoneNumber.setText(contactVO.getContactNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + contactVO.getContactName());
                BlankFragment detailFragment = new BlankFragment();
                //bundle with 3 strings to send and be shown in fragment
                Bundle bundle = new Bundle();
                bundle.putString("name", contactVO.getContactName());
                bundle.putString("number", contactVO.getContactNumber());
                //bundle.putString("email", contactVO.getContactEmail());
                detailFragment.setArguments(bundle);
                detailFragment.show(fm, "DetailFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactVOList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        ImageView ivContactImage;
        TextView tvContactName;
        TextView tvPhoneNumber;

        ContactViewHolder(View itemView) {
            super(itemView);
            ivContactImage = itemView.findViewById(R.id.ivContactImage);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
        }
    }
}
