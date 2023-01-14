package com.company.jmixpm.screen.timeentry;

import com.company.jmixpm.entity.TimeEntry;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("TimeEntry.browse")
@UiDescriptor("time-entry-browse.xml")
@LookupComponent("timeEntriesTable")
public class TimeEntryBrowse extends StandardLookup<TimeEntry> {
}