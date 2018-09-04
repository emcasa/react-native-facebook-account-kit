package io.underscope.react.fbak;

import android.app.Fragment;
import android.os.Parcel;
import android.support.annotation.Nullable;

import com.facebook.accountkit.ui.BaseUIManager;
import com.facebook.accountkit.ui.LoginFlowState;
// import com.facebook.accountkit.ui.TextPosition;

import com.facebook.react.bridge.ReadableMap;

public class RNAccountKitAdvancedUIManager extends BaseUIManager {
  private String bodyComponentId;
  private String headerComponentId;
  private String footerComponentId;

  public RNAccountKitAdvancedUIManager(ReadableMap options) {
    super(BaseUIManager.THEME_ID_NOT_SET);
    bodyComponentId = options.getString("body");
    headerComponentId = options.getString("header");
    footerComponentId = options.getString("footer");
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
    return RNAccountKitFragment.create(bodyComponentId, null);
  }

  @Override
  @Nullable
  public Fragment getHeaderFragment(final LoginFlowState state) {
    if (headerComponentId.isEmpty()) return null;
    return RNAccountKitFragment.create(headerComponentId, null);
  }

  @Override
  @Nullable
  public Fragment getFooterFragment(final LoginFlowState state) {
    if (footerComponentId.isEmpty()) return null;
    return RNAccountKitFragment.create(footerComponentId, null);
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
