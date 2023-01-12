package com.company.jmixpm.screen.jpqltester;

import io.jmix.core.DataManager;
import io.jmix.core.MetadataTools;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.ui.Dialogs;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.SourceCodeEditor;
import io.jmix.ui.component.autocomplete.JpqlUiSuggestionProvider;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@UiController("JpqlTesterScreen")
@UiDescriptor("JPQLtester-screen.xml")
public class JpqlTesterScreen extends Screen {
    @Autowired
    private SourceCodeEditor codeField;
    @Autowired
    private JpqlUiSuggestionProvider jpqlUiSuggestionProvider;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private ComboBox<Class<?>> entityComboBox;
    @Autowired
    private MetadataTools metadataTools;
    @Autowired
    protected Dialogs dialogs;

    @Subscribe
    public void onInit(InitEvent event) {
        codeField.setSuggester((source, text, cursorPosition) -> {
            if (codeField.getValue() == null) {
                return Collections.emptyList();
            }
            return jpqlUiSuggestionProvider.getSuggestions(
                    codeField.getValue(),
                    cursorPosition - 1,
                    codeField.getAutoCompleteSupport()
            );
        });

        Map<String, Class<?>> entities = new TreeMap<>();
        for (MetaClass metaClass : metadataTools.getAllJpaEntityMetaClasses()) {
            entities.put(metaClass.getName(), metaClass.getJavaClass());
        }
        entityComboBox.setOptionsMap(entities);
    }

    @Subscribe("testBtn")
    public void onTestBtnClick(Button.ClickEvent event) {
        if (codeField.getValue() != null) {
            List<?> list;
            if (entityComboBox.getValue() != null) {
                list = dataManager.load(entityComboBox.getValue()).query(codeField.getValue()).list();
            } else {
                list = dataManager.loadValues(codeField.getValue()).list();
            }
            System.out.println("Query results: " + list);

//            dialogs.createMessageDialog()
//                    .withCaption("Confirmation")
//                    .withMessage("Query results: " + list)
//                    .show();
        }
    }
    
    
}