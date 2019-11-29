package com.veniture.util;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.user.ApplicationUser;
import com.veniture.constants.Constants;

import java.util.List;

public class functions {

    public static void updateCustomFieldValue(MutableIssue issue, Long cfId, Object value, ApplicationUser user) {
        CustomField cf = ComponentAccessor.getCustomFieldManager().getCustomFieldObject(cfId);
        issue.setCustomFieldValue(cf,value);
        ComponentAccessor.getIssueManager().updateIssue(user, issue, EventDispatchOption.ISSUE_UPDATED, false);
    }

    public static void updateCfValueForSelectList(MutableIssue issue, Long cfId, Long optionId, ApplicationUser user) {
        CustomField cf = ComponentAccessor.getCustomFieldManager().getCustomFieldObject(cfId);
        FieldConfig fieldConfig=cf.getRelevantConfig(issue);
        Option option=ComponentAccessor.getOptionsManager().getOptions(fieldConfig).getOptionById(optionId);
        issue.setCustomFieldValue(cf,option);
        ComponentAccessor.getIssueManager().updateIssue(user, issue, EventDispatchOption.ISSUE_UPDATED, false);
    }

    public static List<CustomField> getCustomFieldsInProject(String projectKey){
        Long projectId = ComponentAccessor.getProjectManager().getProjectByCurrentKey(projectKey).getId();
        return ComponentAccessor.getCustomFieldManager().getCustomFieldObjects(projectId, ConstantsManager.ALL_ISSUE_TYPES);
    }
}
