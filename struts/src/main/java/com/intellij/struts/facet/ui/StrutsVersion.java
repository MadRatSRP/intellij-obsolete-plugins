/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package com.intellij.struts.facet.ui;

import com.intellij.facet.ui.libraries.LibraryDownloadInfo;
import com.intellij.facet.ui.libraries.LibraryInfo;
import com.intellij.facet.ui.libraries.RemoteRepositoryInfo;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dmitry Avdeev
 */
public enum StrutsVersion {

  Struts1_2_9("1.2.x", new LibraryInfo[]{
    createMavenJarInfo("struts", "1.2.9", "org.apache.struts.action.Action"),
    createMavenJarInfo("antlr", "2.7.2", "antlr.Version"),
    createMavenJarInfo("commons-beanutils", "1.7.0", "org.apache.commons.beanutils.BeanUtils"),
    createMavenJarInfo("commons-digester", "1.6", "org.apache.commons.digester.Digester"),
    createMavenJarInfo("commons-fileupload", "1.0", "org.apache.commons.fileupload.FileUpload"),
    createMavenJarInfo("commons-logging", "1.0.4", "org.apache.commons.logging.Log"),
    createMavenJarInfo("commons-validator", "1.1.4", "org.apache.commons.validator.Validator"),
    createMavenJarInfo("oro", "2.0.7", "org.apache.oro.text.MatchAction")
  },
    createSubMavenJarInfo("struts", "struts-el", "1.2.9", "org.apache.strutsel.taglib.html.ELBaseTag")
  ),

  Struts1_3_8("1.3.x", new LibraryInfo[]{
    createSubMavenJarInfo("org/apache/struts", "struts-core", "1.3.10", "org.apache.struts.action.Action"),
    createMavenJarInfo("antlr", "2.7.2", "antlr.Version"),
    createMavenJarInfo("commons-chain", "1.2", "org.apache.commons.chain.Chain"),
    createMavenJarInfo("commons-io", "1.1", "org.apache.commons.io.FileUtils"),
    createMavenJarInfo("commons-beanutils", "1.8.0", "org.apache.commons.beanutils.BeanUtils"),
    createMavenJarInfo("commons-digester", "1.8", "org.apache.commons.digester.Digester"),
    createMavenJarInfo("commons-fileupload", "1.1.1", "org.apache.commons.fileupload.FileUpload"),
    createMavenJarInfo("commons-logging", "1.0.4", "org.apache.commons.logging.Log"),
    createMavenJarInfo("commons-validator", "1.3.1", "org.apache.commons.validator.Validator"),
    createMavenJarInfo("oro", "2.0.8", "org.apache.oro.text.MatchAction")
  },
    createSubMavenJarInfo("org/apache/struts", "struts-taglib", "1.3.10", "org.apache.struts.taglib.html.BaseTag"),
    new LibraryInfo[] {
      createSubMavenJarInfo("org/apache/struts", "struts-el", "1.3.10", "org.apache.strutsel.taglib.html.ELBaseTag"),
      createSubMavenJarInfo("javax/servlet", "jstl", "1.0.2", "javax.servlet.jsp.jstl.core.ConditionalTagSupport"),
      createSubMavenJarInfo("taglibs", "standard", "1.0.2", "org.apache.taglibs.standard.tag.common.core.ChooseTag")
    },
    createSubMavenJarInfo("org/apache/struts", "struts-tiles", "1.3.10", "org.apache.struts.tiles.TilesPlugin"),
    createSubMavenJarInfo("org/apache/struts", "struts-faces", "1.3.10", "org.apache.struts.faces.application.FacesRequestProcessor"),
    new LibraryInfo[] {
      createSubMavenJarInfo("org/apache/struts", "struts-scripting", "1.3.10", "org.apache.struts.scripting.ScriptAction"),
      createSubMavenJarInfo("bsf", "bsf", "2.3.0", "org.apache.bsf.BSFEngine")
    },
    createSubMavenJarInfo("org/apache/struts", "struts-extras", "1.3.10", "org.apache.struts.plugins.ModuleConfigVerifier")
  );

  private final String myName;
  private final LibraryInfo[] myJars;
  private final LibraryInfo myStrutsTaglib;
  private final LibraryInfo[] myStrutsEl;
  private final LibraryInfo myTiles;
  private final LibraryInfo myStrutsFaces;
  private final LibraryInfo[] myScripting;
  private final LibraryInfo myExtras;

  StrutsVersion(@NonNls String name, LibraryInfo[] infos,
                LibraryInfo strutsTaglib,
                LibraryInfo[] strutsEl,
                LibraryInfo tiles,
                LibraryInfo strutsFaces,
                LibraryInfo[] scripting,
                LibraryInfo extras) {
    myName = name;
    myJars = infos;
    myStrutsTaglib = strutsTaglib;
    myStrutsEl = strutsEl;
    myTiles = tiles;
    myStrutsFaces = strutsFaces;
    myScripting = scripting;
    myExtras = extras;
  }

  StrutsVersion(@NonNls String name, LibraryInfo[] infos, LibraryInfo strutsEl) {
    this(name, infos, null, new LibraryInfo[] {strutsEl}, null, null, null, null);
  }

  public LibraryInfo[] getJars() {
    return myJars;
  }

  public LibraryInfo[] getStrutsEl() {
    return myStrutsEl;
  }

  public LibraryInfo getStrutsTaglib() {
    return myStrutsTaglib;
  }

  public LibraryInfo getTiles() {
    return myTiles;
  }

  public LibraryInfo[] getScripting() {
    return myScripting;
  }

  public LibraryInfo getExtras() {
    return myExtras;
  }

  public String toString() {
    return myName;
  }

  public LibraryInfo getStrutsFaces() {
    return myStrutsFaces;
  }

  private static LibraryInfo createSubMavenJarInfo(@NonNls String project, @NonNls String jarName, @NonNls String version, @NonNls String... requiredClasses) {
    return doCreateMavenJarInfo(jarName, version, project, requiredClasses);
  }

  private static LibraryInfo doCreateMavenJarInfo(final String jarName, final String version, final String downloadingPrefix,
                                                final String... requiredClasses) {
    RemoteRepositoryInfo MAVEN = new RemoteRepositoryInfo("maven", "Maven repository", new String[]{
        "https://repo1.maven.org/maven2/",
    });
    LibraryDownloadInfo downloadInfo = new LibraryDownloadInfo(MAVEN, downloadingPrefix + "/" + jarName + "/" + version + "/" + jarName + "-" + version + ".jar", jarName, ".jar");
    return new LibraryInfo(jarName + ".jar", downloadInfo, requiredClasses);
  }

  private static LibraryInfo createMavenJarInfo(@NonNls String jarName, @NonNls String version, @NotNull @NonNls String... requiredClasses) {
    return doCreateMavenJarInfo(jarName, version, jarName, requiredClasses);
  }
}
