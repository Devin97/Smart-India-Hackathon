package com.sih.pmkvy.adapter;

import com.sih.pmkvy.find_centre.find_centre;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by ASUS on 2/25/2017.
 */


public class centre_list_adapter extends RecyclerView.Adapter<centre_list_adapter.centre_holder> implements View.OnClickListener {


    private List<find_centre> centre_lists;


    public class centre_holder extends RecyclerView.ViewHolder {

        public TextView centre_name, centre_address, centre_phone, centre_info;
        public Button call_button;
        //TODO: add call button while code in it

        public centre_holder(View itemView) {
            super(itemView);
            centre_name = (TextView) itemView.findViewById(R.id.centre_name);
            centre_address = (TextView) itemView.findViewById(R.id.centre_address);
            centre_phone = (TextView) itemView.findViewById(R.id.centre_phone_no);
            centre_info = (TextView) itemView.findViewById(R.id.centre_info);
            call_button = (Button) itemView.findViewById(R.id.call);
        }
    }


    public centre_list_adapter(List<find_centre> centre_list) {
        this.centre_lists = centre_list;
    }


    private centre_holder inner_class;

    @Override
    public centre_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.traning_centre_list, parent, false);
        Button call_but = (Button) itemView.findViewById(R.id.call);
        call_but.setOnClickListener(this);
        inner_class = new centre_holder(itemView);
        return inner_class;
    }

    @Override
    public void onBindViewHolder(centre_holder holder, int position) {
        find_centre centre = centre_lists.get(position);
        holder.centre_name.setText(centre.getCentre_name());
        //holder.centre_name.setOnClickListener(this);
        holder.centre_address.setText(centre.getCentre_address());
        //holder.centre_address.setOnClickListener(this);
        holder.centre_phone.setText(centre.getCentre_phone());
        //holder.centre_phone.setOnClickListener(this);
        holder.centre_info.setText(centre.getCentre_info());
        //holder.centre_info.setOnClickListener(this);
        //holder.call_button.setText(centre.getCall_button());
        holder.call_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        //// TODO: add calling feature
        // Toast.makeText(v.getContext(),"HAHAH", Toast.LENGTH_SHORT).show();
        Intent call_centre = new Intent(Intent.ACTION_CALL);

        call_centre.setData(Uri.fromParts("tel", inner_class.centre_phone.getText().toString(), null));
        if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        v.getContext().startActivity(call_centre);

    }


    @Override
    public int getItemCount() {
        return centre_lists.size();
    }


}
