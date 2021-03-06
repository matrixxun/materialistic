/*
 * Copyright (c) 2015 Ha Duy Trung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hidroh.materialistic;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

public class FavoriteActivity extends BaseListActivity implements FavoriteFragment.DataChangedListener {

    private static final String STATE_FILTER = "state:filter";
    private CoordinatorLayout mContentView;
    private View mEmptyView;
    private View mEmptySearchView;
    private String mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mFilter = savedInstanceState.getString(STATE_FILTER);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        FavoriteFragment fragment = (FavoriteFragment) getSupportFragmentManager()
                .findFragmentByTag(LIST_FRAGMENT_TAG);
        if (fragment != null) {
            fragment.filter(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, mFilter);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mContentView = (CoordinatorLayout) findViewById(R.id.content_frame);
        mEmptyView = addContentView(R.layout.empty_favorite);
        mEmptyView.findViewById(R.id.header_card_view).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View bookmark = mEmptyView.findViewById(R.id.bookmarked);
                bookmark.setVisibility(bookmark.getVisibility() == View.VISIBLE ?
                        View.INVISIBLE : View.VISIBLE);
                return true;
            }
        });
        mEmptyView.setVisibility(View.INVISIBLE);
        mEmptySearchView = addContentView(R.layout.empty_favorite_search);
        mEmptySearchView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String getDefaultTitle() {
        return getString(R.string.title_activity_favorite);
    }

    @Override
    protected Fragment instantiateListFragment() {
        Bundle args = new Bundle();
        args.putString(FavoriteFragment.EXTRA_FILTER, mFilter);
        return Fragment.instantiate(this, FavoriteFragment.class.getName(), args);
    }

    @Override
    protected boolean isSearchable() {
        return false;
    }

    @Override
    public void onDataChanged(boolean isEmpty, String filter) {
        mFilter = filter;
        if (isEmpty) {
            if (TextUtils.isEmpty(filter)) {
                mEmptySearchView.setVisibility(View.INVISIBLE);
                mEmptyView.setVisibility(View.VISIBLE);
                mEmptyView.bringToFront();
            } else {
                mEmptyView.setVisibility(View.INVISIBLE);
                mEmptySearchView.setVisibility(View.VISIBLE);
                mEmptySearchView.bringToFront();
            }
        } else {
            mEmptyView.setVisibility(View.INVISIBLE);
            mEmptySearchView.setVisibility(View.INVISIBLE);
        }

        supportInvalidateOptionsMenu();
    }

    private View addContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, mContentView, false);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                view.getLayoutParams();
        params.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        view.setLayoutParams(params);
        mContentView.addView(view);
        return view;
    }
}
