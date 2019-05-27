package www.birdaaron.com.wanandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;

import java.util.List;

public class KnowledgeBean extends TabBean
{
    private List<KnowledgeChildBean> children;
    public List<KnowledgeChildBean> getChildren()
    {
        return children;
    }
    public void setChildren(List<KnowledgeChildBean> children) {
        this.children = children;
    }

}
