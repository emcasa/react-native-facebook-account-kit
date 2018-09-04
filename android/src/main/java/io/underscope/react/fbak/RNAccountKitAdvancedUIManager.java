package io.underscope.react.fbak;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;

import com.facebook.accountkit.ui.BaseUIManager;
import com.facebook.accountkit.ui.LoginFlowState;
import com.facebook.accountkit.ui.TextPosition;

import com.facebook.react.bridge.ReadableMap;

public class RNAccountKitAdvancedUIManager extends BaseUIManager {
  private String bodyComponentId;
  private String headerComponentId;
  private String footerComponentId;
  private TextPosition textPosition;

  public RNAccountKitAdvancedUIManager(ReadableMap options) {
    super(BaseUIManager.THEME_ID_NOT_SET);
    if (options.hasKey("body"))
      bodyComponentId = options.getString("body");
    if (options.hasKey("header"))
      headerComponentId = options.getString("header");
    if (options.hasKey("footer"))
      footerComponentId = options.getString("footer");
    if (options.hasKey("textPosition")) {
      switch(options.getInt("textPosition")) {
        case 0: textPosition = TextPosition.ABOVE_BODY;
        case 1: textPosition = TextPosition.BELOW_BODY;
      }
    }
  }


  private RNAccountKitAdvancedUIManager(final Parcel source) {
    super(source);
    this.bodyComponentId = source.readString();
    this.headerComponentId = source.readString();
    this.footerComponentId = source.readString();
  }

  @Override
  @Nullable
  public Fragment getBodyFragment(final LoginFlowState state) {
    if (bodyComponentId.isEmpty()) return null;
    return RNAccountKitFragment.create(bodyComponentId, launchOptions(state));
  }

  @Override
  @Nullable
  public Fragment getHeaderFragment(final LoginFlowState state) {
    if (headerComponentId.isEmpty()) return null;
    return RNAccountKitFragment.create(headerComponentId, launchOptions(state));
  }

  @Override
  @Nullable
  public Fragment getFooterFragment(final LoginFlowState state) {
    if (footerComponentId.isEmpty()) return null;
    return RNAccountKitFragment.create(footerComponentId, launchOptions(state));
  }

  private Bundle launchOptions(final LoginFlowState state) {
    Bundle args = new Bundle();
    args.putString("loginState", state.name());
    return args;
  }

  @Override
  public void writeToParcel(final Parcel dest, final int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(bodyComponentId);
    dest.writeString(headerComponentId);
    dest.writeString(footerComponentId);
  }

  public static final Creator<RNAccountKitAdvancedUIManager> CREATOR
          = new Creator<RNAccountKitAdvancedUIManager>() {
    @Override
    public RNAccountKitAdvancedUIManager createFromParcel(final Parcel source) {
      return new RNAccountKitAdvancedUIManager(source);
    }

    @Override
    public RNAccountKitAdvancedUIManager[] newArray(final int size) {
      return new RNAccountKitAdvancedUIManager[size];
    }
  };
}
