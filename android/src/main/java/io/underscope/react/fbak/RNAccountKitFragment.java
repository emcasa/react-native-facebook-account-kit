package io.underscope.react.fbak;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class RNAccountKitFragment extends Fragment {
    public static final String ARG_COMPONENT_NAME = "arg_component_name";
    public static final String ARG_LAUNCH_OPTIONS = "arg_launch_options";

    private String componentName;
    private Bundle launchOptions;

    private ReactRootView reactRootView;
    private ViewGroup layoutContainer;

    public static RNAccountKitFragment create(@NonNull String componentName, Bundle launchOptions) {
        RNAccountKitFragment instance = new RNAccountKitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COMPONENT_NAME, componentName);
        args.putBundle(ARG_LAUNCH_OPTIONS, launchOptions);
        instance.setArguments(args);
        return instance;
    }

    // region Lifecycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            componentName = getArguments().getString(ARG_COMPONENT_NAME);
            launchOptions = getArguments().getBundle(ARG_LAUNCH_OPTIONS);
        }
        if (componentName == null) {
            throw new IllegalStateException("Cannot loadApp if component name is null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ak_container, container, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutContainer = view.findViewById(R.id.ak_container);
        reactRootView = new ReactRootView(getReactContext());
        reactRootView.startReactApplication(
                getReactNativeHost().getReactInstanceManager(),
                componentName,
                launchOptions);
        layoutContainer.addView(reactRootView, layoutParams);
        return layoutContainer;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getReactNativeHost().hasInstance() && getReactActivity() != null) {
            getReactNativeHost().getReactInstanceManager().onHostResume(getReactActivity(), (DefaultHardwareBackBtnHandler) getReactActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getReactNativeHost().hasInstance() && getReactActivity() != null) {
            getReactNativeHost().getReactInstanceManager().onHostPause(getReactActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            reactRootView = null;
        }
    }

    // endregion

    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getActivity().getApplication()).getReactNativeHost();
    }

    protected ReactContext getReactContext() {
        return getReactNativeHost()
                .getReactInstanceManager()
                .getCurrentReactContext();
    }

    protected Activity getReactActivity() {
        return getReactContext().getCurrentActivity();
    }
}
