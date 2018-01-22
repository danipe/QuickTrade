package com.example.yo.quicktrade.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.modelos.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by yo on 22/01/2018.
 */

public class ProductosAdapter extends BaseAdapter {
    CheckBox checkBox;
    ArrayList<Producto> productos;
    ArrayList<String> keys;
    Context context;

    public ProductosAdapter(ArrayList<Producto> productos, Context context, ArrayList<String> keys) {
        this.productos = productos;
        this.context = context;
        this.keys = keys;
    }

    @Override
    public int getCount() {
        return this.productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            if( convertView == null ){
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.mi_producto, parent, false);
            }
        }
        ((TextView) convertView.findViewById(R.id.username)).setText(productos.get(position).getNombre());
        ((TextView) convertView.findViewById(R.id.nombre)).setText(productos.get(position).getPrecio());
        ((TextView) convertView.findViewById(R.id.apellidos)).setText(productos.get(position).getCategoria());

        checkBox = ((CheckBox) convertView.findViewById(R.id.checkbox));
        checkfav(keys.get(position));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                update(keys.get(position), isChecked);
//                Log.e("tag", ""+isChecked);
                buttonView.setChecked(isChecked);
            }
        });
        return convertView;
    }

    public void checkfav(String key) {
        checkBox.setChecked(false);
        DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("productos");
        Query q = ddbb.child(key).child("favs").orderByKey();
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("tag", "cantidad: "+dataSnapshot.getChildrenCount());
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    if (dataSnapshot1.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        checkBox.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void update(String key, boolean checked) {
        DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("productos");
        if(checked) {
            ddbb.child(key).child("favs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("true");
        } else {
            ddbb.child(key).child("favs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
        }
    }
}
