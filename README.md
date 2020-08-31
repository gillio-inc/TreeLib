
[![bintray Download](https://api.bintray.com/packages/wshunli/maven/android-copy-assets/images/download.svg)](https://bintray.com/wshunli/maven/android-copy-assets/_latestVersion)
[![Build Status](https://travis-ci.org/wshunli/android-copy-assets.svg?branch=master)](https://travis-ci.org/)
[![GitHub license](https://img.shields.io/github/license/wshunli/android-copy-assets.svg)](https://github.com/wshunli/android-copy-assets)

Library for copying assets files and folders to the device

## Download

Install android-copy-assets library.

``` groovy
repositories {
    jcenter()
}

dependencies {
    implementation 'com.wshunli.android:android-copy-assets:2.2.0'
}
```

Check out [android-copy-assets releases](https://github.com/wshunli/android-copy-assets/releases) to see more unstable versions.

## How do I use TreeLib?

### 1. Make node class extends Node

``` Java
public class TestNode extends Node {

    TestNode(String name, int id) {
        super(name, id);
        //constructor
    }

    @Override
    public void render() {
      //used for call method Tree.renderKnot(Node knot);
      // your code
    }
}
```

#### 2. Make object from your class 
``` Java
TestNode testNode = new TestNode("nameId", 0);
```

Or the method parameters set to `""`.

``` Java
CopyAssets.with(this)
        .from("")
        .to(externalPath)
        .copy();
```

You can also remove the `to()` method, while it means copy files and folders to Internal Storage.

``` Java
CopyAssets.with(this)
        .from("")
        .copy();
```

#### CopyListener

android-copy-assets support monitoring file copy progress.

``` Java
CopyAssets.with(this)
        .to(innerPath)
        .setListener(new CopyListener() {
                @Override
                public void pending(CopyCreator copyCreator, String oriPath, String desPath, List<String> names) {
                Log.d(TAG, "pending: " + names.toString());
                }

                @Override
                public void progress(CopyCreator copyCreator, File currentFile, int copyProgress) {
                Log.d(TAG, "progress: " + copyProgress + "-->currentFile:" + currentFile.getName());
                }

                @Override
                public void completed(CopyCreator copyCreator, Map<File, Boolean> results) {
                Log.d(TAG, "completed: " + results.toString());
                }

                @Override
                public void error(CopyCreator copyCreator, Throwable e) {
                Log.d(TAG, "error: " + e);
                }
        })
        .copy();
```

Find more details about android-copy-assets in [sample](https://github.com/wshunli/android-copy-assets/tree/master/app).

## License

    Copyright 2017 wshunli

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0
