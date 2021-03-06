/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package com.intellij.struts.facet.ui;

import com.intellij.util.ui.ThreeStateCheckBox;

import javax.swing.*;

/**
 * @author nik
 */
public class StrutsFacetCommonSettingsPanel {
  private JPanel myMainPanel;
  private ThreeStateCheckBox myDisablePropertyKeysValidationCheckBox;

  public JPanel getMainPanel() {
    return myMainPanel;
  }

  public ThreeStateCheckBox getDisablePropertyKeysValidationCheckBox() {
    return myDisablePropertyKeysValidationCheckBox;
  }
}
