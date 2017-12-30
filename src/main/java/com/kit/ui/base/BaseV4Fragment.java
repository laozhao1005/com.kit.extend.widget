package com.kit.ui.base;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kit.utils.ResWrapper;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseV4Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the newInstance() factory method to
 * create an instance of this fragment.
 */
public abstract class BaseV4Fragment extends Fragment implements IDoFragmentInit {


    public OnFragmentInteractionListener mListener;


    public BaseV4Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getActivity() != null) {
            ResWrapper.getInstance().setContext(getActivity());
        }

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ResWrapper.getInstance().setContext(getActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destory();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return createView(inflater, container,
                savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getExtra(savedInstanceState);
        initWidgetWithExtra();
        loadData();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (getActivity() != null) {
            ResWrapper.getInstance().setContext(getActivity());
        }
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public boolean getExtra(Bundle savedInstanceState) {

        return true;
    }


    /**
     * 去网络或者本地加载数据
     */
    public boolean loadData() {
        return true;
    }

    protected abstract int  layoutResID();

    public void initWidget(@NonNull View layout){}


    /**
     * 初始化界面
     */
    public View createView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

        View layout = inflater.inflate(layoutResID(), container, false);
        initWidget(layout);
        return layout;
    }

    /**
     * 去网络或者本地加载数据
     */
    public void initWidgetWithExtra() {
    }

    public void destory() {
//        onPause();
//        onStop();
//        onDestroyView();
//        onDestroy();
//        onDetach();
    }

}
