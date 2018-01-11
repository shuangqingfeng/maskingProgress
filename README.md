#### 图片遮罩带进度上传的 ImageView
##### 做个萌哒哒的包子君

###### 这个效果来源于 一个朋友项目的需求，而这个需求恰恰是仿的咸鱼发布任务里面的一个图片上传带进度的功能，而我最近刚好在复习 View 的知识，内心就萌发了要实现这个效果。 废话不多说，先看效果图 ：
![效果图](https://github.com/shuangqingfeng/maskingProgress/raw/master/screen/maskingProgress.gif)

###### 两部分解说本项目：
+ 效果中的图片选择框架用的是 [okhttp-OkGo](https://github.com/jeasonlzy) 网络框架的作者 jeasonlzy,再次感谢作者提供轮子，供我们使用， 点击查看[图片选择框架](https://github.com/jeasonlzy/ImagePicker)
- V1.0 
 - 待优化:
  - 存在图片加载缓慢，加载不出来的
  - 详情页面加载缓慢的优化，主题背景设计
