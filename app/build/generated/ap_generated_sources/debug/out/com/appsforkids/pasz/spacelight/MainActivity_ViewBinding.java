// Generated code from Butter Knife. Do not modify!
package com.appsforkids.pasz.spacelight;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.home_b = Utils.findRequiredViewAsType(source, R.id.home_b, "field 'home_b'", ImageView.class);
    target.right_p = Utils.findRequiredViewAsType(source, R.id.right_p, "field 'right_p'", ImageView.class);
    target.random_list = Utils.findRequiredViewAsType(source, R.id.random_list, "field 'random_list'", ImageView.class);
    target.player = Utils.findRequiredViewAsType(source, R.id.player, "field 'player'", LinearLayout.class);
    target.play_p = Utils.findRequiredViewAsType(source, R.id.play_p, "field 'play_p'", ImageView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
    target.melody_list = Utils.findRequiredViewAsType(source, R.id.melody_list, "field 'melody_list'", ImageView.class);
    target.audio_name = Utils.findRequiredViewAsType(source, R.id.audio_name, "field 'audio_name'", TextView.class);
    target.rv = Utils.findRequiredViewAsType(source, R.id.rv, "field 'rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.home_b = null;
    target.right_p = null;
    target.random_list = null;
    target.player = null;
    target.play_p = null;
    target.constrain = null;
    target.melody_list = null;
    target.audio_name = null;
    target.rv = null;
  }
}
