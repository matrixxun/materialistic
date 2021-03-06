package io.github.hidroh.materialistic.test;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.text.Spannable;

import io.github.hidroh.materialistic.data.ItemManager;

public abstract class TestItem implements ItemManager.Item {
    private boolean favorite;
    private boolean viewed;
    private boolean collapsed;
    private int lastKidCount;
    private boolean contentCollapsed;

    @Override
    public String getParent() {
        return "0";
    }

    @Override
    public ItemManager.Item getParentItem() {
        return null;
    }

    @Override
    public void populate(ItemManager.Item info) {

    }

    @Override
    public String getRawType() {
        return null;
    }

    @Override
    public String getRawUrl() {
        return null;
    }

    @Override
    public long[] getKids() {
        return new long[0];
    }

    @Override
    public String getBy() {
        return null;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @NonNull
    @Override
    public String getType() {
        return STORY_TYPE;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public Spannable getDisplayedTime(Context context, boolean abbreviate, boolean authorLink) {
        return null;
    }

    @Override
    public int getKidCount() {
        return 0;
    }

    @Override
    public int getDescendants() {
        return 0;
    }

    @Override
    public ItemManager.Item[] getKidItems() {
        return new ItemManager.Item[0];
    }

    @Override
    public int getLastKidCount() {
        return lastKidCount;
    }

    @Override
    public void setLastKidCount(int lastKidCount) {
        this.lastKidCount = lastKidCount;
    }

    @Override
    public boolean hasNewKids() {
        return false;
    }

    @Override
    public boolean isFavorite() {
        return favorite;
    }

    @Override
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean isViewed() {
        return viewed;
    }

    @Override
    public void setIsViewed(boolean isViewed) {
        viewed = isViewed;
    }

    @Override
    public int getLocalRevision() {
        return 0;
    }

    @Override
    public void setLocalRevision(int localRevision) {

    }

    @Override
    public String getDisplayedTitle() {
        return null;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public boolean isStoryType() {
        return false;
    }

    @Override
    public String getId() {
        return "1";
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isDeleted() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void incrementScore() {

    }

    @Override
    public boolean isCollapsed() {
        return collapsed;
    }

    @Override
    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    @Override
    public boolean isContentExpanded() {
        return contentCollapsed;
    }

    @Override
    public void setContentExpanded(boolean expanded) {
        contentCollapsed = expanded;
    }

    @Override
    public int getRank() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getId() != null && getId().equalsIgnoreCase(((TestItem) o).getId());
    }
}
