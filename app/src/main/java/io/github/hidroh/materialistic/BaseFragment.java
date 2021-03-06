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

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Base fragment which performs injection using parent's activity object graphs if any
 */
public abstract class BaseFragment extends Fragment {
    protected final MenuTintDelegate mMenuTintDelegate = new MenuTintDelegate();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof Injectable) {
            ((Injectable) getActivity()).inject(this);
        }
        mMenuTintDelegate.onActivityCreated(getActivity());
    }

    @CallSuper
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mMenuTintDelegate.onOptionsMenuCreated(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Application.getRefWatcher(getActivity()).watch(this);
    }
}
