package androidadvance.com.androidsurveyexample.forms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidadvance.com.androidsurveyexample.R;
import androidadvance.com.androidsurveyexample.http.hosp.resp.Form;

/**
 * Created by asjain on 1/5/2017.
 */

public class FormListAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private ArrayList<Form> forms;

    public FormListAdapter(Context context, ArrayList<Form> forms) {

        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.forms = forms;
    }

    @Override
    public int getCount() {
        return forms.size();
    }

    @Override
    public Object getItem(int i) {
        return forms.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        RelativeLayout itemView;
        if (convertView == null) {
            itemView = (RelativeLayout) mLayoutInflater.inflate(
                    R.layout.list_form_item, parent, false);

        } else {
            itemView = (RelativeLayout) convertView;
        }

        ImageView imageView = (ImageView)
                itemView.findViewById(R.id.listImage);
        TextView titleText = (TextView)
                itemView.findViewById(R.id.formTitle);
        TextView descriptionText = (TextView)
                itemView.findViewById(R.id.formDescription);

        String title = ((Form)getItem(i)).getName();
        titleText.setText(title);
        String description = ((Form)getItem(i)).getDescription();
        if (description.trim().length() == 0) {
            description = "";
        }
        descriptionText.setText(description);

        return itemView;
    }
}
