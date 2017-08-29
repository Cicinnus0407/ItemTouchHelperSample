# ItemTouchHelperSample
ItemTouchHelperDemo
#### RecyclerView的item长按拖拽换位

##### ItemTouchHelper 是support.v7包中提供的一个类,在Api22的时候被加入,目的是为RecyclerView的item添加滑动(Swipe)和拖拽(drag&drop)功能.适用于系统提供的各种LayoutManager(Linear/Grid/StaggeredGrid),同时也可以用于自定义的LayoutManager,只需实现几个方法就能实现长按拖拽和侧滑的效果.

#### Sample地址:

#### 效果图
> #####  示例中只启用拖拽功能,滑动功能可以自行研究,其中官方文档是最好的参考[ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.html)

[![效果](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/SM-G9350_20170829220641.gif)](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/SM-G9350_20170829220641.gif)
### 实现步骤:

#### 1.添加一个RecyclerView和一些假数据,应该都非常熟悉了
[![](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_061.png)](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_061.png)
#### 2.继承ItemTouchHelper.SimpleCallback类,并重写相应方法

> ##### ItemTouchHelper.SimpleCallback是ItemTouchHelper.Callback的一个简单实现,只需传入允许拖拽和滑动的方向就能实现item的拖拽效果
> #### 注: 默认情况下长按拖拽可以对整个RecyclerView的item生效,如果我们只想让用户按着拖拽按钮才能进行拖拽,必须禁用item的长按拖拽功能.

[![](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_062.png)](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_062.png)
#### 3.给拖拽的button设置OnTouchListener
[![](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_064.png)](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_064.png)
#### 3.1在拖拽监听中设置 itemTouchHelper.startDrag(holder);
[![](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_065.png)](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_065.png)

#### 4.初始化ItemTouchHelper,传入CallBack,与RecyclerView绑定,就完成了
[![](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_063.png)](http://cicinnus-blog.oss-cn-shenzhen.aliyuncs.com/2017/08/capture_063.png)

