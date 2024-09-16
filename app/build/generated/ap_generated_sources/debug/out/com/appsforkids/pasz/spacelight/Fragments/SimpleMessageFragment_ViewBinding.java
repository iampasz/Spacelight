// Generated code from Butter Knife. Do not modify!
package com.appsforkids.pasz.spacelight.Fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.appsforkids.pasz.spacelight.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SimpleMessageFragment_ViewBinding implements Unbinder {
  private SimpleMessageFragment target;

  @UiThread
  public SimpleMessageFragment_ViewBinding(SimpleMessageFragment target, View source) {
    this.target = target;

    target.ok_button = Utils.findRequiredViewAsType(source, R.id.ok_button, "field 'ok_button'", ImageView.class);
    target.dialog_message = Utils.findRequiredViewAsType(source, R.id.dialog_message, "field 'dialog_message'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SimpleMessageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ok_button = null;
    target.dialog_message = null;
  }
}
