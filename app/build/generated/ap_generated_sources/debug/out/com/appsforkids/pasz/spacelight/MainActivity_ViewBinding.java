// Generated code from Butter Knife. Do not modify!
package com.appsforkids.pasz.spacelight;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
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
    target.suit_button = Utils.findRequiredViewAsType(source, R.id.suit_button, "field 'suit_button'", ImageView.class);
    target.lockButton = Utils.findRequiredViewAsType(source, R.id.lockButton, "field 'lockButton'", LinearLayout.class);
    target.alien_bt = Utils.findRequiredViewAsType(source, R.id.alien_bt, "field 'alien_bt'", LottieAnimationView.class);
    target.rocket = Utils.findRequiredViewAsType(source, R.id.rocket, "field 'rocket'", LottieAnimationView.class);
    target.lock_button = Utils.findRequiredViewAsType(source, R.id.lock_button, "field 'lock_button'", ImageView.class);
    target.lock_frame = Utils.findRequiredViewAsType(source, R.id.lock_frame, "field 'lock_frame'", FrameLayout.class);
    target.right_p = Utils.findRequiredViewAsType(source, R.id.right_p, "field 'right_p'", ImageView.class);
    target.random_list = Utils.findRequiredViewAsType(source, R.id.random_list, "field 'random_list'", ImageView.class);
    target.player = Utils.findRequiredViewAsType(source, R.id.player, "field 'player'", LinearLayout.class);
    target.play_p = Utils.findRequiredViewAsType(source, R.id.play_p, "field 'play_p'", ImageView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
    target.melody_list = Utils.findRequiredViewAsType(source, R.id.melody_list, "field 'melody_list'", ImageView.class);
    target.audio_name = Utils.findRequiredViewAsType(source, R.id.audio_name, "field 'audio_name'", TextView.class);
    target.timer_text = Utils.findRequiredViewAsType(source, R.id.timer_text, "field 'timer_text'", TextView.class);
    target.rv = Utils.findRequiredViewAsType(source, R.id.rv, "field 'rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.home_b = null;
    target.suit_button = null;
    target.lockButton = null;
    target.alien_bt = null;
    target.rocket = null;
    target.lock_button = null;
    target.lock_frame = null;
    target.right_p = null;
    target.random_list = null;
    target.player = null;
    target.play_p = null;
    target.constrain = null;
    target.melody_list = null;
    target.audio_name = null;
    target.timer_text = null;
    target.rv = null;
  }
}
