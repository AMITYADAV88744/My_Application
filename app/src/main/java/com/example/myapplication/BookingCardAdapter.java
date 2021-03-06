package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookingCardAdapter extends RecyclerView.Adapter<BookingCardAdapter.MyViewHolder> {


    ArrayList<YourBookingPOJO> yourlist = new ArrayList<YourBookingPOJO>();
    Context ctx;

    boolean isOpen = false;

    Animation fabOpen, fabClose, fabClockwise, fabAnticlockwise;

    static String key;

    public BookingCardAdapter(ArrayList<YourBookingPOJO> yourlist, Context context) {
        this.yourlist = yourlist;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showbookingcardlayout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, ctx, yourlist);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YourBookingPOJO yourBookingPOJO = yourlist.get(position);
        holder.bookingdate.setText(yourBookingPOJO.getBookingdate());
        holder.bookingid.setText(yourBookingPOJO.getBookingid());
        holder.passengername.setText(yourBookingPOJO.getUsername());
        holder.from.setText(yourBookingPOJO.getFrom());
        holder.to.setText(yourBookingPOJO.getTo());
        holder.traveldate.setText(yourBookingPOJO.getDate());
        holder.traveltime.setText(yourBookingPOJO.getTime());
        holder.vsize.setText(yourBookingPOJO.getVsize());
        holder.vtype.setText(yourBookingPOJO.getVtype());
        holder.bookingpref.setText(yourBookingPOJO.getBookingpref());
        holder.traveltype.setText(yourBookingPOJO.getVtime());
        holder.travelagent.setText(yourBookingPOJO.getAgentname());
    }

    @Override
    public int getItemCount() {
        return yourlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookingdate;
        TextView bookingid;
        TextView passengername;
        TextView from;
        TextView to;
        TextView traveldate;
        TextView traveltime;
        TextView vsize;
        TextView vtype;
        TextView bookingpref;
        TextView traveltype;
        TextView travelagent;
        LinearLayout linearLayout;
        FloatingActionButton fabPlus, fabloc, fabcall, fabcancel;

        Context ctx;
        ArrayList<YourBookingPOJO> list = new ArrayList<YourBookingPOJO>();

        public MyViewHolder(View itemView, final Context ctx, final ArrayList<YourBookingPOJO> list) {
            super(itemView);
            this.list = list;
            this.ctx = ctx;

            bookingdate = (TextView) itemView.findViewById(R.id.bookingdate);
            bookingid = (TextView) itemView.findViewById(R.id.bookingid);
            passengername = (TextView) itemView.findViewById(R.id.passengername);
            from = (TextView) itemView.findViewById(R.id.from);
            to = (TextView) itemView.findViewById(R.id.to);
            traveldate = (TextView) itemView.findViewById(R.id.traveldate);
            traveltime = (TextView) itemView.findViewById(R.id.traveltime);
            vsize = (TextView) itemView.findViewById(R.id.carsize);
            vtype = (TextView) itemView.findViewById(R.id.cartype);
            bookingpref = (TextView) itemView.findViewById(R.id.travelpref);
            traveltype = (TextView) itemView.findViewById(R.id.traveltype);
            travelagent = (TextView) itemView.findViewById(R.id.travelagentname);
            fabPlus = (FloatingActionButton) itemView.findViewById(R.id.floatactionbutton);
            fabloc = (FloatingActionButton) itemView.findViewById(R.id.fab_btn_loc);
            fabcall = (FloatingActionButton) itemView.findViewById(R.id.fab_btn_call);

            fabcancel = (FloatingActionButton) itemView.findViewById(R.id.cancelbooking);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayoutbooking);

            fabOpen = AnimationUtils.loadAnimation(ctx, R.anim.fab_open);
            fabClose = AnimationUtils.loadAnimation(ctx, R.anim.fab_close);
            fabClockwise = AnimationUtils.loadAnimation(ctx, R.anim.rotate_clockwise);
            fabAnticlockwise = AnimationUtils.loadAnimation(ctx, R.anim.rotate_anticlockwise);


            itemView.setClickable(true);
            itemView.setFocusableInTouchMode(true);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final YourBookingPOJO yourBookingPOJO = list.get(position);

                    Toast toast = Toast.makeText(ctx, "Press again to confirm.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();

                    fabcall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + yourBookingPOJO.agentcontact));
                            ctx.startActivity(intent);
                        }
                    });

                    fabloc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast toast = Toast.makeText(ctx, yourBookingPOJO.getAgentaddress(), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                            String map = "http://maps.google.co.in/maps?q=" + yourBookingPOJO.getAgentaddress();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                            ctx.startActivity(intent);
                        }
                    });

                    fabPlus.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            Toast.makeText(ctx,"Press To Call And Locate Travel Agent.",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });

                    fabPlus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (isOpen) {

                                fabloc.startAnimation(fabClose);
                                fabcall.startAnimation(fabClose);
                                fabPlus.startAnimation(fabAnticlockwise);
                                fabloc.setClickable(false);
                                fabcall.setClickable(false);
                                isOpen = false;
                            } else {

                                fabloc.startAnimation(fabOpen);
                                fabcall.startAnimation(fabOpen);
                                fabPlus.startAnimation(fabClockwise);
                                fabloc.setClickable(true);
                                fabcall.setClickable(true);
                                isOpen = true;
                            }
                        }
                    });


                    fabcancel.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            Toast.makeText(ctx,"Press To Cancel Booking.",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });


                    fabcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.v("cancel", "clicked");

                            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                            builder.setTitle("Cancel Booking");
                            builder.setCancelable(false);
                            builder.setMessage("Are you sure you want to cancel booking?");
                            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();

                        }
                    });
                }
            });
        }

    }
}
