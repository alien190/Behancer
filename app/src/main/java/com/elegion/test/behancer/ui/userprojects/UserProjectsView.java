package com.elegion.test.behancer.ui.userprojects;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.project.Project;

import java.util.List;

public interface UserProjectsView extends BaseView {

    void showProjects(List<Project> projects);

}
