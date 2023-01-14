package com.company.jmixpm.screen.timeentry;

import com.company.jmixpm.entity.TimeEntry;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("TimeEntry.edit")
@UiDescriptor("time-entry-edit.xml")
@EditedEntityContainer("timeEntryDc")
public class TimeEntryEdit extends StandardEditor<TimeEntry> {
}