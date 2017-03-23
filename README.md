## StatefulLayout

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-StatefulLayout-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5325)  [![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)  [![MavenCentral](https://maven-badges.herokuapp.com/maven-central/com.github.gturedi/stateful-layout/badge.svg?style=flat)](https://oss.sonatype.org/content/repositories/releases/com/github/gturedi/stateful-layout/)  [![Build Status](https://travis-ci.org/gturedi/StatefulLayout.svg?branch=master)](https://travis-ci.org/gturedi/StatefulLayout)  [![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Android layout to show most common state templates like loading, empty, error etc. To do that all you need to is wrap 
the target area(view) with StatefulLayout.

![alt text](sample.gif)

## Usage
Add dependecy to app/build.gradle
```groovy
dependencies {
     compile 'com.github.gturedi:stateful-layout:1.2.1'
}
```
Snapshots of the development version are available in [Sonatype's `snapshots` repository][https://oss.sonatype.org/content/repositories/snapshots/com/github/gturedi].

Then wrap a view which target area(view) to show states with StatefulLayout
```xml
  <com.gturedi.views.StatefulLayout
        android:id="@+id/stateful"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:stfAnimationEnabled="true"
        app:stfInAnimation="@android:anim/slide_in_left"
        app:stfOutAnimation="@android:anim/slide_out_right"
        >
    
        <!-- your content here  -->
        <LinearLayout
            android:id="@+id/target"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            >
            ...
        </LinearLayout>

    </com.gturedi.views.StatefulLayout>
```

Finally in your activity/fragment get StatefulLayout reference and call showXXX methods
```java
StatefulLayout stateful = (StatefulLayout) findViewById(R.id.stateful);
stateful.showLoading();
//stateful.showEmpty(getString(R.string.testMessage));
//stateful.showError(getString(R.string.testMessage), clickListener);
//etc.
```

## API
State methods have overloads for customization. If you pass clickListener parameter as null, relevant state button 
will be hided. More customization please look [CustomStateOptions.java](library/src/main/java/com/gturedi/views/CustomStateOptions.java)

- setAnimationEnabled(boolean animationEnabled)
- setInAnimation(@AnimRes int inAnimation)
- setOutAnimation(@AnimRes int outAnimation)
- showContent()
- showLoading(String message)
- showEmpty(String message)
- showError(String message, OnClickListener clickListener)
- showOffline(String message, OnClickListener clickListener)
- showLocationOff(String message, OnClickListener clickListener)
- showCustom(CustomStateOptions options)

## Xml Attributes
| Name | Type | Default | Description |
|:----:|:----:|:-------:|:-----------:|
| stfAnimationEnabled | boolean | true | Indicates whether to place the animation on state changes |
| stfInAnimation | anim | @android:anim/fade_in | Animation started begin of state change |
| stfOutAnimation | anim | @android:anim/fade_out | Animation started end of state change |

## Customization
Just override relevant resource in your app to customize state views appearance

- To Override **strings** [strings.xml variables](library/src/main/res/values/strings.xml)

- To Override **styles** [styles.xml](library/src/main/res/values/styles.xml)

- To Override **layout** [stf_template.xml](library/src/main/res/layout/stf_template.xml)

## License
    Copyright 2017 Gökhan Türedi (turedi.gokhan@gmail.com)
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
