package com.example.osvaldo.assingment1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.List;

public class Home_gridview extends BaseAdapter {

    private List<JSONObject> listdata;
    private LayoutInflater layoutinflater;
    private Context context;

    public Home_gridview(Context aContext, List<JSONObject> listdata) {
        this.context = aContext;
        this.listdata = listdata;
        this.layoutinflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return this.listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutinflater.inflate(R.layout.home_gridview, null);
        TextView texto = (TextView)convertView.findViewById(R.id.texto);
        ImageView image = (ImageView)convertView.findViewById(R.id.thumbnail);

        JSONObject article = this.listdata.get(position);
        try {
            String title = article.getString("title");
            texto.setText(" "+title+" ");
            String thumbnail = article.getString("urlToImage");
            Picasso.get()
                    .load(thumbnail)
                    .resize(500, 400)
                    .centerCrop()
                    .into(image);
        } catch (JSONException je){
            Log.e("Title_error: ", je.getMessage());
        }
        return convertView;
    }
}
