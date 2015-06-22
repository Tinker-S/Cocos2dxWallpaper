Cocos2dxWallpaper
=========================
This is an example of creating custom android wallpaper with Cocos2dx.

<img src="https://github.com/Tinker-S/Cocos2dxWallpaper/blob/master/screenshots/wallpaper.png" style="width:180px;height:320px;"/>

## How to do this?
* First, you must download a version of cocos2dx, you'd better use version 2.x. Because 3.x don't support WallPaperService by default(Cocos2dxHelper only support Activity parameter). Unzip it to a dir.

* Configure environment variables in your **~/.bash_profile**

<img src="https://github.com/Tinker-S/Cocos2dxWallpaper/blob/master/screenshots/step1.png"/>

**ANDROID_SDK_ROOT** and **NDK_ROOT** is for cocos2dx.

* Enter your cocos2dx root dir, use the command below
```
cd tools/project-creator
chmod u+x create_project.py
./create_project.py -project Cocos2dxWallpaper -package com.awesomego.wallpaper -language cpp
```

<img src="https://github.com/Tinker-S/Cocos2dxWallpaper/blob/master/screenshots/step2.png"/>

* Add your custom WallPaper code into **proj.android**, you can refer to the source code.

* Enter 'proj.android' dir, compile cpp code
```
sh build_native.sh
```

* Enter 'proj.android' dir, use the command below to compile apk
```
ant -Dsdk.dir=$ANDROID_SDK_ROOT -Dtarget=android-19 debug
```

* Install the apk in 'bin' fodler, then you can change wallpaper on system settings of your phone.

## Attention

If you have found any bugs or need some features, please create an issue at [Github Issue Tracker](https://github.com/Tinker-S/Cocos2dxWallpaper/issues).

## License

    Copyright (C) 2015 Tinker Sun

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.