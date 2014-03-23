Mouse Recorder
=============
Mouse Recorder is a multi-platform software to record and re-play your mouse and keyboard actions.
![ScreenShot](/docs/images/mouse-recorder.png)
Requirements
------------
Java 6 or above is required to run Mouse Recorder. If you do not have a Java installation please download from
<a href="www.java.com/download/">www.java.com/download/</a>
Download
--------
* Windows -  <a href="https://github.com/eguller/MouseRecorder/releases/download/v1.0.1/MouseRecorder.exe">Mouse.Recorder.exe</a>
* MacOSX - <a href="https://github.com/eguller/MouseRecorder/releases/download/v1.0.1/MouseRecorder.dmg">Mouse.Recorder.dmg</a>
* Other - <a href="https://github.com/eguller/MouseRecorder/releases/download/v1.0.1/mouserecorder.jar">mouserecorder.jar</a>

For other OS run following command
```
java -jar mouserecorder.jar
````
Recording
---------
To start recording simply click **red record button**. If you are a MacOS user **access for assistive devices**
option must be enabled. Mouse Recorder asks for your permission once to enable this option before start recording.
If you prefer to enable this option by yourself go to **Settings -> Accessibility** and click **Enable access for assistive devices**
as shown in below.
![ScreenShot](/docs/images/enable-access-assistive-devices.png)

Minimizing Mouse Recorder when recording helps you to focus on recording your macro. To enable this feature go to **Options**
and click **Minimize on record**

To stop recording click **green stop button** which is in the middle.

Recorded macros can be save to a file. When you complete recording go to **File -> Save** and save your macro.
Playing
-------
You can either play your last record or load a previous record from file. To play your last record click **blue play button**.
To load a previous record go to **File -> Open** and open the file which you saved before and then click **blue play button**.

If you want to minimize Mouse Recorder before start playing as in recording go to **Options** and click **Minimize on play**

Records can be played multiple times with Mouse Recorder. If you want to play records forever go to **Options** and click **Infinite Loop**.
If you want to play records a few times un-check **Infinite Loop** option and enter how many times you want to run record.
To stop playing, hit **CTRL-F2**. This will abort playing immediately.

You can play a record faster or slower than original speed. Go to **Options** and use **Speed** slider to adjust speed.


