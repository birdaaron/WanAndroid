package www.birdaaron.com.wanandroid.module;

import java.util.List;

import www.birdaaron.com.wanandroid.bean.ProjectBean;
import www.birdaaron.com.wanandroid.bean.TabBean;

public interface ProjectModule
{
    List<TabBean> getProjectType(String response);
    List<ProjectBean> getProjectData(String response);
}
