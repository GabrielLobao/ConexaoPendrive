package loop.conexaopendrive.Adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import loop.conexaopendrive.Ajuste.ImageHelper;
import loop.conexaopendrive.Dominio.Pen;
import loop.conexaopendrive.Interface.RecyclerViewOnClickListenerHack;
import loop.conexaopendrive.R;


/**
 * Created by lobao on 09/08/2015.
 */
public class PenAdapter extends RecyclerView.Adapter<PenAdapter.mViewHolder> {
    private Context mContext;
    private List<Pen> nList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;


    public PenAdapter(Context c, List<Pen> l) {
        mContext = c;
        nList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int) (14d * scale + 0.5f);
        height = (width / 16) * 9;


    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_pen_card, parent, false);
        mViewHolder mvh = new mViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {

        holder.ivPen.setImageResource(nList.get(position).getFoto());
        holder.tvmodelo.setText(nList.get(position).getModelo());
        holder.tvcategoria.setText(nList.get(position).getCategoria());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.ivPen.setImageResource(nList.get(position).getFoto());

        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), nList.get(position).getFoto());
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
            holder.ivPen.setImageBitmap(bitmap);

        }

    }

    @Override
    public int getItemCount() {

        return nList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack c) {
        mRecyclerViewOnClickListenerHack = c;
    }

    public void addListItem(Pen p, int position) {
        nList.add(p);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        nList.remove(position);
        notifyItemRemoved(position);
    }

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivPen;
        public TextView tvmodelo;
        public TextView tvcategoria;

        public mViewHolder(View itemView) {
            super(itemView);

            ivPen = (ImageView) itemView.findViewById(R.id.iv_pen);
            tvmodelo = (TextView) itemView.findViewById(R.id.tv_modelo);
            tvcategoria = (TextView) itemView.findViewById(R.id.tv_categoria);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {

                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());

            }
        }
    }
}
