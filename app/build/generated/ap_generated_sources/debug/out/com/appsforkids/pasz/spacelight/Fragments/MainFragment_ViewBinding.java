// Generated code from Butter Knife. Do not modify!
package com.appsforkids.pasz.spacelight.Fragments;

import android.view.View;
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
    target.main_constrain = Utils.findRequiredViewAsType(source, R.id.main_constrain, "field 'main_constrain'", ConstraintLayout.class);
    target.tab_l2 = Utils.findRequiredViewAsType(source, R.id.tab_l2, "field 'tab_l2'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pager = null;
    target.main_constrain = null;
    target.tab_l2 = null;
  }
}
