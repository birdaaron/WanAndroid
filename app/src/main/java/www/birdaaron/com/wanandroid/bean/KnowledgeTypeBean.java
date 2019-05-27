package www.birdaaron.com.wanandroid.bean;

import android.support.design.widget.TabLayout;

import java.util.List;

public class KnowledgeTypeBean extends TabBean
{
    private List<String> children;

    public List<String> getChildren()
    {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

}
