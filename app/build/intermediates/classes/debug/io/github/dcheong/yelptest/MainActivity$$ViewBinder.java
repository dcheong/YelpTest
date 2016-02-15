// Generated code from Butter Knife. Do not modify!
package io.github.dcheong.yelptest;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends io.github.dcheong.yelptest.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558545, "field 'tButton'");
    target.tButton = finder.castView(view, 2131558545, "field 'tButton'");
    view = finder.findRequiredView(source, 2131558546, "field 'searchLocation'");
    target.searchLocation = finder.castView(view, 2131558546, "field 'searchLocation'");
    view = finder.findRequiredView(source, 2131558549, "field 'next'");
    target.next = finder.castView(view, 2131558549, "field 'next'");
    view = finder.findRequiredView(source, 2131558550, "field 'back'");
    target.back = finder.castView(view, 2131558550, "field 'back'");
    view = finder.findRequiredView(source, 2131558547, "field 'restaurantImage'");
    target.restaurantImage = finder.castView(view, 2131558547, "field 'restaurantImage'");
    view = finder.findRequiredView(source, 2131558548, "field 'restaurantName'");
    target.restaurantName = finder.castView(view, 2131558548, "field 'restaurantName'");
  }

  @Override public void unbind(T target) {
    target.tButton = null;
    target.searchLocation = null;
    target.next = null;
    target.back = null;
    target.restaurantImage = null;
    target.restaurantName = null;
  }
}
