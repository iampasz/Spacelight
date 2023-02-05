// Generated code from Butter Knife. Do not modify!
package com.appsforkids.pasz.spacelight.Fragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.appsforkids.pasz.spacelight.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding implements Unbinder {
  private MainFragment target;

  @UiThread
  public MainFragment_ViewBinding(MainFragment target, View source) {
    this.target = target;

    target.pager = Utils.findRequiredViewAsType(source, R.id.pager, "field 'pager'", ViewPager.class);
    target.lock_button = Utils.findRequiredViewAsType(source, R.id.lock_button, "field 'lock_button'", ImageView.class);
    target.suit_button = Utils.findRequiredViewAsType(source, R.id.suit_button, "field 'suit_button'", ImageView.class);
    target.rocket = Utils.findRequiredViewAsType(source, R.id.rocket, "field 'rocket'", ImageView.class);
    target.lockButton = Utils.findRequiredViewAsType(source, R.id.lockButton, "field 'lockButton'", LinearLayout.class);
    target.animateBg = Utils.findRequiredViewAsType(source, R.id.animateBg, "field 'animateBg'", ImageView.class);
    target.timerText = Utils.findRequiredViewAsType(source, R.id.timer, "field 'timerText'", TextView.class);
    target.main_constrain = Utils.findRequiredViewAsType(source, R.id.main_constrain, "field 'main_constrain'", ConstraintLayout.class);
    target.lock_frame = Utils.findRequiredViewAsType(source, R.id.lock_frame, "field 'lock_frame'", FrameLayout.class);
    target.tab_l2 = Utils.findRequiredViewAsType(source, R.id.tab_l2, "field 'tab_l2'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pager = null;
    target.lock_button = null;
    target.suit_button = null;
    target.rocket = null;
    target.lockButton = null;
    target.animateBg = null;
    target.timerText = null;
    target.main_constrain = null;
    target.lock_frame = null;
    target.tab_l2 = null;
  }
}
