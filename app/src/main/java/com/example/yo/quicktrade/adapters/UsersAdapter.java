package com.example.yo.quicktrade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.modelos.Usuario;

import java.util.ArrayList;

/**
 * Created by yo on 15/01/2018.
 */

public class UsersAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Usuario> usuarios;

    public UsersAdapter(Context context, ArrayList<Usuario> usuarios) {
        this.context=context;
        this.usuarios=usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.mi_item, parent, false);
        }
        Usuario u = usuarios.get(position);
        ((TextView) convertView.findViewById(R.id.username)).setText(u.getUser());
        ((TextView) convertView.findViewById(R.id.nombre)).setText(u.getNombre());
        ((TextView) convertView.findViewById(R.id.apellidos)).setText(u.getApellidos());
        return convertView;
    }
}
