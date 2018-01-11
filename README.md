#### 图片遮罩带进度上传的 ImageView
##### 做个萌哒哒的包子君

###### 这个效果来源于 一个朋友项目的需求，而这个需求恰恰是仿的咸鱼发布任务里面的一个图片上传带进度的功能，而我最近刚好在复习 View 的知识，内心就萌发了要实现这个效果。 废话不多说，先看效果图 ：
![效果图](https://github.com/shuangqingfeng/maskingProgress/raw/master/screen/maskingProgress.gif)

###### 此项目分为两部分：
+ 效果中的图片选择框架用的是 [okhttp-OkGo](https://github.com/jeasonlzy) 网络框架的作者 jeasonlzy,再次感谢作者提供轮子，供我们使用， 点击查看[图片选择框架](https://github.com/jeasonlzy/ImagePicker)
+ 自定义 ImageView
  
###### 如何自定义view
+ 自定义 View 分为三步曲
 + 继承 View
 + 重写 onMeasure
 + 重写 onDraw()<br/>
 三步曲 是自定义View的基础步骤，我们细细说说  onMeasure() 和 onDraw() 方法 ，在下面文字中，如果有理解不对的地方的地方还望指教，共同进步。<br/>
    + onMeasure()<br/>
    字面理解为：测量。即对View进行宽高测量,以便在屏幕显示多大空间<br/>
      + View的测量有三种模式：<br/>
       + EXACTLY<br/>
          字面理解：精确。何为精确模式呢？也就是说是有具体的数值来表示的。比如我们在xml 指定 View的宽高分别给定的是 具体的值，那么系统在调用onMeasure() 方法的时候就使用这种模式测量View.<br/>
       + UNSPECIFIED<br/>
          字面理解:未指定。啥是未指定呢？ （顿时 mmp ） 未指定就是 没有宽高值，View 想多大就多大。 这个通常是在自定义View里面使用的（出自群英传）。<br/>
       + AT_MOST<br/>
       字面理解：最大值。 也就是 我们在不指定具体的宽高，让View根据自身的内容来确定以高宽，只要不超过父控件的宽高即可。<br/>
      测量模式决定了View最终的的宽高。其实在系统中也都是继承自View ，通过重写View 来实现的现有控件的，比如 TextView、ImageView.本项目就是继承自ImageView ，对ImageView现有的控件进行一个扩展。先不说本项目的实现，我们先认识自定义View的结构。<br/>
   + onDraw()<br/>
   字面理解：绘制。”绘制“就是绘制图像。那就是把View 绘制到屏幕上。在 这个方法里面有一个重要的参数 Canvas，理解画布。即把绘制的内容绘制到画布上在呈现到屏幕上。在 canvas 这个对象给我们提供了很多方法。比如  canvas.drawRect()(画矩形)、canvas.drawText()(画文字)。等画几何的一些方法，通过合理利用这些方法来实现一个带特效的View。就不详细说了，有兴趣的可以去源码里面看看提供的所有方法。既然有了画布，那么我们如何画呢？用什么画呢？搜嘎，当然和我们写字一样需要用笔，即 Paint，在代码中通过 Paint 我们构造了一个画笔去画我们想要的图形，把我们的绘制的内容画到 Canvas 上，所以在 canvas 有些方法里面的最后一个参数就是 paint ，即我们构造的 paint。通过 Paint 和 Canvas 等的配合我们就基本可以显示一些基本效果。在这里只是对自定义view的一些基本解说。要想画出更漂亮的效果，还需要我们自己去看大神的博客和android 文档来进行去学习，通过不断的实战来联系。最重要的一点还是需要有好的实现思思路😳。<br/>
   本项目的实现思路就是通过 继承自 ImageView，并对 ImageView 进行了一个扩展。来实现。代码里面也有注释。想学习的可以看看。项目里面的效果也可以用于产品中。在使用当中如有问题请联系我！谢谢。文章中如有不到位的地方，还请海涵。<br/>
   ## License
   
    Copyright 2017 包子君  Email: 315460509@qq.com    扣扣同步<br/>
  
    Licensed under the Apache License, Version 2.0 (the "License");<br/>
    you may not use this file except in compliance with the License.<br/>
    You may obtain a copy of the License at<br/>

     http://www.apache.org/licenses/LICENSE-2.0<br/>

    Unless required by applicable law or agreed to in writing, software<br/>
    distributed under the License is distributed on an "AS IS" BASIS,<br/>
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br/>
    See the License for the specific language governing permissions and<br/>
    limitations under the License.
 
