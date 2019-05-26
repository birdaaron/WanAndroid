package www.birdaaron.com.wanandroid.module;

import java.util.List;

import www.birdaaron.com.wanandroid.bean.BannerBean;
import www.birdaaron.com.wanandroid.bean.ProjectBean;
import www.birdaaron.com.wanandroid.bean.ProjectTypeBean;

public interface ProjectModule
{
    List<ProjectTypeBean> getProjectType(String response);
    List<ProjectBean> getProjectData(String response);
}
