package com.example.yo.quicktrade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.modelos.Producto;

import java.util.ArrayList;

/**
 * Created by yo on 22/01/2018.
 */

public class ProductosAdapter extends BaseAdapter {

    ArrayList<Producto> productos;
    Context context;

    public ProductosAdapter(ArrayList<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            if( convertView == null ){
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.mi_item, parent, false);
            }
        }
        ((TextView) convertView.findViewById(R.id.username)).setText(productos.get(position).getNombre());
        ((TextView) convertView.findViewById(R.id.nombre)).setText(productos.get(position).getPrecio());
        ((TextView) convertView.findViewById(R.id.apellidos)).setText(productos.get(position).getCategoria());

        return convertView;
    }
}
