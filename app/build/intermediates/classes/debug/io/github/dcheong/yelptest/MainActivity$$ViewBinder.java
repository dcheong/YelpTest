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
    view = finder.findRequiredView(source, 2131558546, "field 'results'");
    target.results = finder.castView(view, 2131558546, "field 'results'");
    view = finder.findRequiredView(source, 2131558547, "field 'searchLocation'");
    target.searchLocation = finder.castView(view, 2131558547, "field 'searchLocation'");
  }

  @Override public void unbind(T target) {
    target.tButton = null;
    target.results = null;
    target.searchLocation = null;
  }
}
