## AlipayDragView
支付宝我的应用编辑 拖拽效果实现
|                  支付宝效果                   |      实现的效果      |
| :--------------------------------------: | :-------------: |
| ![支付宝效果](https://ws3.sinaimg.cn/large/006tNc79ly1fnt8osfestg304r08waw3.gif) | ![ 我实现的效果](待补图) |

## 拖拽实现原理
1. 上古时期的 item 拖拽实现的思路是：当用户长按选择一个item时，将该item隐藏，然后用WindowManager添加一个新的window，该window与所选择item一模一样，并且跟随用户手指滑动而不断改变位置。当window的位置坐标在GridView里面时，使用pointToPosition (int x, int y)方法来判断对应的应该是哪个item，在adapter中作出数据集相应的变化，然后做出平移的动画。当用户手指抬起时，把window移除，使用notifyDataSetChanged()做出GridView更新。实现这种效果在 6.0 之后的手机需要请求权限。

2. 一搜一大把的实现思路：RecyclView 配合 ItemTouchHelper 方便快捷的实现 Item 的拖拽。但是这样实现出来的效果有一个致命的缺点，Item 的移动拖拽只能在RecycleView 内部发生。不能实现类似支付宝那样，可以把 Item 拖拽出 RecycleView 的效果。

3. View的startDrag方法配合setOnDragListener。经过效果对比可以确认支付宝就是用的这种方案实现的拖拽排序效果。[!参考官方文档](https://developer.android.google.cn/guide/topics/ui/drag-drop.html#AboutDragging)