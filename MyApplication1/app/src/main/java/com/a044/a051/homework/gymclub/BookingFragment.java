package com.a044.a051.homework.gymclub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String[] coach_main = {"Charles","Mary","Niki","Gary","William"};
    private String[] coach_sub;
    private int[] coach_img = {R.drawable.coach1,R.drawable.coach2,R.drawable.coach2,R.drawable.coach1,R.drawable.coach1};

    private String[] course_main = {"Badminton","Basketball","Tennis","Boxing","Soccer","Ping-pong","Volleyball"};
    private String[] course_sub;
    private int[] course_img = {R.drawable.sports1,R.drawable.sports2,R.drawable.sports3,R.drawable.sports4,R.drawable.sports5,R.drawable.sports6,R.drawable.sports7};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView bookingList;

    private OnFragmentInteractionListener mListener;

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        bookingList = (ListView) (getView().findViewById(R.id.booking_list));
        course_sub = new String[course_main.length];
        coach_sub = new String[coach_main.length];
        for(int i = 0;i<course_main.length;i++){
            course_sub[i] = "Coach:";
        }
        for(int i = 0;i<coach_main.length;i++){
            coach_sub[i] = "Course:";
            int num = 1+(int)(Math.random()*(course_main.length-1));
            boolean[] chosen = new boolean[num];
            for(int j = 0;j<num;j++){
                chosen[j] = false;
            }
            int k = 0;
            while(k != num){
                int sequence = (int)(Math.random()*num);
                if(chosen[sequence]){
                    continue;
                }
                else{
                    coach_sub[i] += course_main[sequence]+" ";
                    course_sub[sequence] += coach_main[i] + " ";
                    chosen[sequence] = true;
                    k++;
                }
            }
        }
        bookingList.setAdapter(new BookingAdapter(getContext(),true,this));
    }
    static class ViewHolder{
        public TextView main;
        public TextView sub;
        public Button button;
        public ImageView icon;
    }
    class BookingAdapter extends BaseAdapter{
        private LayoutInflater mInflater=null;
        private boolean isCoach;
        private BookingFragment parent;
        private BookingAdapter(Context context,boolean isCoach,BookingFragment fragment){
            this.mInflater=LayoutInflater.from(context);
            this.isCoach = isCoach;
            parent = fragment;
        }
        public int getCount(){
            if (isCoach){
                return coach_main.length;
            }
            else{
                return course_main.length;
            }

        }
        public Object getItem(int position){
            return position;
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position,View convertView,ViewGroup viewGroup){
            final ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                convertView=mInflater.inflate(R.layout.item_booking,null);
                viewHolder.main=(TextView)convertView.findViewById(R.id.booking_main);
                viewHolder.sub=(TextView)convertView.findViewById(R.id.booking_sub);
                viewHolder.button=(Button)convertView.findViewById(R.id.booking_button);
                viewHolder.icon=(ImageView)convertView.findViewById(R.id.booking_img);
                convertView.setTag(viewHolder);
            }else {
                viewHolder=(ViewHolder)convertView.getTag();
            }
            if(isCoach){
                viewHolder.main.setText(coach_main[position]);
                viewHolder.sub.setText(coach_sub[position]);
                viewHolder.icon.setImageResource(coach_img[position]);
            }
            else{
                viewHolder.main.setText(course_main[position]);
                viewHolder.sub.setText(course_sub[position]);
                viewHolder.icon.setImageResource(course_img[position]);
            }
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getActivity(),"Booking succeed!",Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_booking, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final SeekBar seekBar=(SeekBar) getActivity().findViewById(R.id.seekBar);
        final Context context = getContext();
        final BookingFragment self = this;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                BookingAdapter adapter = null;
                if(progress==0) {
                    adapter = new BookingAdapter(context,true,self);
                } else {
                    adapter = new BookingAdapter(context,false,self);
                }
                bookingList.setAdapter(adapter);
            }

            /**
             * 开始滑动时调用该方法
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * 结束滑动时调用该方法
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
