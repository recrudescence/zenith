package io.codestellation.zenith;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoalsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoalsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";  //Title
    private static final String ARG_PARAM2 = "param2";  //Category
    private static final String ARG_PARAM3 = "param3";  //Long Desc

    private View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoalsFragment newInstance(String param1, String param2, String param3) {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);     //Title in title
        args.putString(ARG_PARAM2, param2);     //Category in category
        args.putString(ARG_PARAM3, param3);     //Long Desc in Description
        fragment.setArguments(args);
        return fragment;
    }

    public GoalsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1); //Initialize values based on bundled arguments.
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_goals, container, false);     //Inflate layout to a view.
        fillMissionDetails();
        return rootView;
    }

    public void fillMissionDetails()
    {

        //Fill the items in the fragment with the variables from the database.

        TextView Title = (TextView) rootView.findViewById(R.id.Title);
        TextView Category = (TextView) rootView.findViewById(R.id.Category);
        TextView LongDesc = (TextView) rootView.findViewById(R.id.LongDesc);

        TextView expNum = (TextView) rootView.findViewById(R.id.expAmt);
        Button start = (Button) rootView.findViewById(R.id.button);

//        Title.setText(mParam1);
//        Category.setText(mParam2);
//        LongDesc.setText(mParam3);

        Title.setText("Save the Princess!");
        Category.setText("Community");
        LongDesc.setText("Head over to a local charity within the next week, and help out the first person you see!");

        expNum.setText("2");
        start.setText("Start!");

        return;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {      //UI Events - use for start/complete
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener"); //Parent activity needs to be class ___ implements GoalsFragment.OnFragmentInteractionListener
        }
    }

    @Override
    public void onDetach() {        //Fragment detached from Parent and will prob be replaced by something else.
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);         //Interface that lets this fragment talk to activity and other frags related to it.
    }

}
