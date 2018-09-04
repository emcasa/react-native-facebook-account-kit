package io.underscope.react.fbak;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class RNAccountKitFragment extends Fragment {
    public static final String ARG_COMPONENT_NAME = "componentName";
    public static final String ARG_LAUNCH_OPTIONS = "launchOptions";

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
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostResume(getReactActivity(), (DefaultHardwareBackBtnHandler) getReactActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getReactNativeHost().hasInstance()) {
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
        if (getReactNativeHost().hasInstance()) {
            ReactInstanceManager reactInstanceMgr = getReactNativeHost().getReactInstanceManager();

            // onDestroy may be called on a ReactFragment after another ReactFragment has been
            // created and resumed with the same React Instance Manager. Make sure we only clean up
            // host's React Instance Manager if no other React Fragment is actively using it.
            if (reactInstanceMgr.getLifecycleState() != LifecycleState.RESUMED) {
                reactInstanceMgr.onHostDestroy(getReactActivity());
                getReactNativeHost().clear();
            }
        }
    }

    // endregion

    /**
     * Get the {@link ReactNativeHost} used by this app. By default, assumes
     * {@link Activity#getApplication()} is an instance of {@link ReactApplication} and calls
     * {@link ReactApplication#getReactNativeHost()}. Override this method if your application class
     * does not implement {@code ReactApplication} or you simply have a different mechanism for
     * storing a {@code ReactNativeHost}, e.g. as a static field somewhere.
     */
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
