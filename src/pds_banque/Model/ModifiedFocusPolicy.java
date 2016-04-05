/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Model;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;

/**
 * This class allowed to reorganize the navigation order in the fields with the
 * tabulation key
 *
 * @author Warren
 */
public class ModifiedFocusPolicy extends FocusTraversalPolicy {

    private final ArrayList<Component> fields;

    public ModifiedFocusPolicy(ArrayList<Component> theFields) {
        fields = new ArrayList<>(theFields.size());
        fields.addAll(theFields);
    }

    @Override
    public Component getComponentAfter(Container aContainer, Component aComponent) {
        int index = fields.indexOf(aComponent) + 1;
        if (index == fields.size()) {
            index = 0;
        }
        return fields.get(index);
    }

    @Override
    public Component getComponentBefore(Container aContainer, Component aComponent) {
        int index = fields.indexOf(aComponent) - 1;
        if (index < 0) {
            index = fields.size() - 1;
        }
        return fields.get(index);
    }

    @Override
    public Component getFirstComponent(Container aContainer) {
        return fields.get(0);
    }

    @Override
    public Component getLastComponent(Container aContainer) {
        return fields.get(fields.size() - 1);
    }

    @Override
    public Component getDefaultComponent(Container aContainer) {
        return fields.get(0);
    }

}
