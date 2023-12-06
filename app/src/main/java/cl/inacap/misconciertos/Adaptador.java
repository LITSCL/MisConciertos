package cl.inacap.misconciertos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Item> listaItems;

    public Adaptador(Context context, ArrayList<Item> listaItems) {
        this.context = context;
        this.listaItems = listaItems;
    }

    @Override
    public int getCount() { //Este método indica cuantos datos se le van a cargar al ListView (Como se carga el ArrayList al ListView, la cantidad de datos corresponden al largo del ArrayList).
        return listaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listaItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) { //En este método se asignan los elementos.
        Item item=(Item)getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imagenIv = (ImageView)convertView.findViewById(R.id.imagenIv);
        TextView nombreArtistaTv = (TextView)convertView.findViewById(R.id.nombreArtistaTv);
        TextView fechaTv = (TextView)convertView.findViewById(R.id.fechaTv);
        TextView valorEntradaTv = (TextView)convertView.findViewById(R.id.valorEntradaTv);
        imagenIv.setImageResource(item.getImagen());
        nombreArtistaTv.setText(item.getNombreArtista());
        fechaTv.setText(item.getFecha());
        valorEntradaTv.setText(item.getValorEntrada());

        return convertView;
    }
}
