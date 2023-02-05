// Generated code from Butter Knife. Do not modify!
package com.appsforkids.pasz.spacelight.Fragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.appsforkids.pasz.spacelight.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageFragment_ViewBinding implements Unbinder {
  private MessageFragment target;

  @UiThread
  public MessageFragment_ViewBinding(MessageFragment target, View source) {
    this.target = target;

    target.no_button = Utils.findRequiredViewAsType(source, R.id.no_button, "field 'no_button'", FrameLayout.class);
    target.yes_button = Utils.findRequiredViewAsType(source, R.id.yes_button, "field 'yes_button'", FrameLayout.class);
    target.dialog_message = Utils.findRequiredViewAsType(source, R.id.dialog_message, "field 'dialog_message'", TextView.class);
    target.frame_constraine = Utils.findRequiredViewAsType(source, R.id.frame_constraine, "field 'frame_constraine'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.no_button = null;
    target.yes_button = null;
    target.dialog_message = null;
    target.frame_constraine = null;
  }
}
